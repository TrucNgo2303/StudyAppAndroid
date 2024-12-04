package com.example.studyapp.ui.student

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studyapp.R

class CourseAdapter(private val courseList: List<Course>) :
    RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    private lateinit var mListener : onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position : Int)

    }
    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    // ViewHolder cho mỗi phần tử
    class CourseViewHolder(view: View, listener: onItemClickListener) : RecyclerView.ViewHolder(view) {
        val courseImage: ImageView = view.findViewById(R.id.img_course)
        val courseName: TextView = view.findViewById(R.id.tv_course_name)
        val coursePrice: TextView = view.findViewById(R.id.tv_course_price)

        init {
            view.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_course, parent, false)
        return CourseViewHolder(view,mListener)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = courseList[position]
        holder.courseImage.setImageResource(course.resourceImage)
        holder.courseName.text = course.courseName
        holder.coursePrice.text = course.coursePrice
    }

    override fun getItemCount(): Int {
        return courseList.size
    }
}