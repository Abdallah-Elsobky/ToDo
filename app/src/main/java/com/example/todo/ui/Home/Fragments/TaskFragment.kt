package com.example.todo.ui.Home.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todo.database.TodoDatabase
import com.example.todo.database.entities.Task
import com.example.todo.ui.adapters.TaskAdapter
import com.example.todo.databinding.FragmentTaskBinding
import com.example.todo.utils.toEpoch
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private lateinit var adapter: TaskAdapter
    private val database = TodoDatabase.getInstance()
    var selectedDay = CalendarDay.today()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initCalender()
    }

    private fun initCalender() {
        binding.calendarView.selectedDate = selectedDay
        binding.calendarView.setOnDateChangedListener { _, date, selected ->
            if (selected) {
                selectedDay = date
                refreshAdapter()
            }
        }
    }

    fun initRecyclerView() {
        adapter = TaskAdapter(getTasks)
        binding.taskRv.adapter = adapter
    }

    fun refreshAdapter() {
        adapter.updateTasks(getTasks)
    }

    private val getTasks: List<Task>
        get() = database.taskDao().getTasksByDate(selectedDay.toEpoch)

}
