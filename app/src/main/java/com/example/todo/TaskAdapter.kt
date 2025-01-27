package com.example.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.todo.databinding.TaskItemBinding
import com.example.todo.model.Task

class TaskAdapter(val tasks: ArrayList<Task>, val onTaskClick: (position: Int) -> Unit) :
    Adapter<TaskAdapter.TaskViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }


    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.bind(task, position)
    }

    override fun getItemCount() = tasks.size

    inner class TaskViewHolder(val binding: TaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task, position: Int) {
            binding.taskTitle.text = task.title
            binding.taskTime.text = task.time
            binding.root.setOnClickListener {
                onTaskClick(position)
            }
        }
    }
}