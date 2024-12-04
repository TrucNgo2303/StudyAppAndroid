package com.example.studyapp.ui.student

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studyapp.R

class LessonAdapter(private val lessonList: List<Lesson>) : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    // ViewHolder để ánh xạ các thành phần giao diện trong item
    class LessonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val lessonName: AppCompatTextView = view.findViewById(R.id.tv_lesson_name)
        val lessonDuration: AppCompatTextView = view.findViewById(R.id.tv_lesson_duration)
    }

    // Tạo ViewHolder từ layout item
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LessonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lesson, parent, false)
        return LessonViewHolder(view)
    }

    // Xác định số lượng item trong danh sách
    override fun getItemCount(): Int {
        return lessonList.size
    }

    // Gắn dữ liệu từ danh sách vào ViewHolder
    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val lesson = lessonList[position]
        holder.lessonName.text = lesson.name
        holder.lessonDuration.text = lesson.duration
    }
}