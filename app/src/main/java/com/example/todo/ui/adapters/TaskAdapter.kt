package com.example.todo.ui.adapters

import TaskDiffCallback
import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.todo.databinding.TaskItemBinding
import com.example.todo.database.entities.Task

class TaskAdapter(var tasks: List<Task>, var onClick: onTaskClickListener) :
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
            Log.e("a7a", task.toString())
            if (task.isDone) {
                markAsDone()
            }
            binding.deleteBtn.setOnClickListener({
                onClick.onDeleteClick(task)
            })
            binding.doneBtn.setOnClickListener({
                markAsDone()
                onClick.onDoneClick(task)
            })
            binding.cardView.setOnClickListener({
                onClick.onTaskClick(task)
            })
        }
        private fun markAsDone() {
            binding.taskTitle.setTextColor(Color.GREEN)
            binding.taskTime.setTextColor(Color.GREEN)
            binding.line1.setBackgroundColor(Color.GREEN)
            binding.doneBtn.visibility = View.GONE
            binding.timeIc.setColorFilter(Color.GREEN)
        }
    }

    fun updateTasks(newTasks: List<Task>) {
        val diffCallback = TaskDiffCallback(tasks, newTasks)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        tasks = newTasks
        diffResult.dispatchUpdatesTo(this)
    }

    interface onTaskClickListener {
        fun onTaskClick(task: Task)
        fun onDoneClick(task: Task)
        fun onDeleteClick(task: Task)
    }
}