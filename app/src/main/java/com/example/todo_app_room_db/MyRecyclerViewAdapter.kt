package com.example.todo_app_room_db

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_app_room_db.databinding.LayoutCustomViewBinding

class MyRecyclerViewAdapter(private var notesList: List<Notes>, private val onItemClickListener: OnItemClickListener):RecyclerView.Adapter<MyRecyclerViewAdapter.CustomViewHolder>(){

    inner class CustomViewHolder(var layoutCustomViewBinding: LayoutCustomViewBinding):
        RecyclerView.ViewHolder(layoutCustomViewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        var layoutCustomViewBinding:LayoutCustomViewBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.layout_custom_view, parent, false)
        return CustomViewHolder(layoutCustomViewBinding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val notes = notesList.get(position)
        holder.layoutCustomViewBinding.tvTitle.text = notes.Title
        holder.layoutCustomViewBinding.tvDescrip.text = notes.Descryption
        holder.layoutCustomViewBinding.tvTime.text = notes.Created_At

        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(notes, position)
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }
}