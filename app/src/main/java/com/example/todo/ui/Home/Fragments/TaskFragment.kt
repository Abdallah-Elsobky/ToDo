package com.example.todo.ui.Home.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todo.database.TodoDatabase
import com.example.todo.ui.adapters.TaskAdapter
import com.example.todo.databinding.FragmentTaskBinding
class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private lateinit var adapter: TaskAdapter
    private val database = TodoDatabase.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TaskAdapter(emptyList())
        initRecyclerView()
    }

    fun initRecyclerView() {
        val tasks = database.taskDao().getAllTasks()
        adapter = TaskAdapter(tasks)
        binding.taskRv.adapter = adapter
    }

    fun refreshAdapter() {
        val tasks = database.taskDao().getAllTasks()
        adapter.updateTasks(tasks)
    }

}
