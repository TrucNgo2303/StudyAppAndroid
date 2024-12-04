package com.example.studyapp.ui.student

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studyapp.R

class ForumAdapter(private val forumList: List<Forum>) : RecyclerView.Adapter<ForumAdapter.ForumViewHolder>() {

    class ForumViewHolder (view : View) : RecyclerView.ViewHolder(view) {
        val forumTitle : AppCompatTextView = view.findViewById(R.id.tv_forum)
        val forumPosterName : AppCompatTextView = view.findViewById(R.id.tv_poster_name)
        val forumViewNumber : AppCompatTextView = view.findViewById(R.id.tv_view_number)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_forum,parent,false)
        return ForumViewHolder(view)
    }

    override fun getItemCount(): Int {
        return forumList.size
    }

    override fun onBindViewHolder(holder: ForumViewHolder, position: Int) {
        val forum = forumList[position]
        holder.forumTitle.text = forum.title
        holder.forumPosterName.text = forum.posterName
        holder.forumViewNumber.text = forum.viewNumber
    }
}