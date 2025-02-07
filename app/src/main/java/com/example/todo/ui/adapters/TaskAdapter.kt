package com.example.todo.ui.adapters

import TaskDiffCallback
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.todo.R
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
        if (task.isDone) {
            holder.markAsDone()
        } else {
            holder.markAsNotDone()
        }
        holder.bind(task)
    }

    override fun getItemCount() = tasks.size

    inner class TaskViewHolder(val binding: TaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            Log.e("Tago", tasks.size.toString())
            binding.taskTitle.text = task.title
            binding.taskTime.text = task.time
//            if (task.isDone) {
//                markAsDone()
//            }
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

        fun markAsDone() {
            binding.taskTitle.setTextColor(Color.GREEN)
            binding.taskTime.setTextColor(Color.GREEN)
            binding.line1.setBackgroundColor(Color.GREEN)
            binding.doneBtn.visibility = View.GONE
            binding.timeIc.setColorFilter(Color.GREEN)
        }

        fun markAsNotDone() {
            val primary = ContextCompat.getColor(binding.root.context, R.color.primary)
            binding.taskTitle.setTextColor(primary)
            binding.taskTime.setTextColor(primary)
            binding.line1.setBackgroundColor(primary)
            binding.doneBtn.visibility = View.VISIBLE
            binding.timeIc.setColorFilter(primary)
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