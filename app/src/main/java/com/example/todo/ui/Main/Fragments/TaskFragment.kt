package com.example.todo.ui.Main.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.TaskAdapter
import com.example.todo.databinding.FragmentTaskBinding
import com.example.todo.model.Task

class TaskFragment : Fragment() {
    private val tasks: ArrayList<Task> = ArrayList()
    private lateinit var binding: FragmentTaskBinding
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Populate tasks (can be moved to another method)
        tasks.add(Task("Task 1", "bla bla bla bla bla", "10:30"))
        tasks.add(Task("Task 2", "bla bla bla bla bla", "11:30"))
        tasks.add(Task("Task 3", "bla bla bla bla bla", "12:30"))
        tasks.add(Task("Task 4", "bla bla bla bla bla", "01:30"))
        tasks.add(Task("Task 4", "bla bla bla bla bla", "01:30"))
        tasks.add(Task("Task 4", "bla bla bla bla bla", "01:30"))
        tasks.add(Task("Task 4", "bla bla bla bla bla", "01:30"))
        tasks.add(Task("Task 4", "bla bla bla bla bla", "01:30"))
        tasks.add(Task("Task 4", "bla bla bla bla bla", "01:30"))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        adapter = TaskAdapter(tasks) { task ->

        }

        binding.taskRv.layoutManager = LinearLayoutManager(requireContext()) // Set layout manager
        binding.taskRv.adapter = adapter

        return binding.root
    }
}
