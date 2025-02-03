package com.example.todo.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.todo.databinding.TaskItemBinding
import com.example.todo.database.entities.Task

class TaskAdapter(var tasks: List<Task>) :
    Adapter<TaskAdapter.TaskViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }


    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.bind(task)
    }

    override fun getItemCount() = tasks.size

    inner class TaskViewHolder(val binding: TaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.taskTitle.text = task.title
            binding.taskTime.text = task.time
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateTasks(newTasks: List<Task>) {
        tasks = newTasks
        notifyDataSetChanged()
    }

    interface onTaskClickListener {
        fun onTaskClick(task: Task)
        fun onDoneClick(task: Task)
        fun onDeleteClick(task: Task)
    }
}