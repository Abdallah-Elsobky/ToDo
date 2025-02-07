package com.example.todo.ui.Home.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.todo.R
import com.example.todo.database.TodoDatabase
import com.example.todo.database.entities.Task
import com.example.todo.ui.adapters.TaskAdapter
import com.example.todo.databinding.FragmentTaskBinding
import com.example.todo.ui.update_task_fragment.UpdateTaskFragment
import com.example.todo.utils.toEpoch
import com.prolificinteractive.materialcalendarview.CalendarDay

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
        adapter = TaskAdapter(getTasks, object : TaskAdapter.onTaskClickListener {
            override fun onTaskClick(task: Task) {
                UpdateTaskFragment(task, {
                    refreshAdapter()
                }).show(parentFragmentManager, null)
            }

            override fun onDoneClick(task: Task) {
                task.isDone = !task.isDone
                database.taskDao().updateTask(task)
                refreshAdapter()
            }

            override fun onDeleteClick(task: Task) {
                database.taskDao().deleteTask(task)
                refreshAdapter()
                Toast.makeText(
                    requireContext(),
                    getString(R.string.delete_task_successfully), Toast.LENGTH_SHORT
                ).show()
            }

        })
        binding.taskRv.adapter = adapter
    }

    fun refreshAdapter() {
        adapter.updateTasks(getTasks)
    }

    private val getTasks: List<Task>
        get() = database.taskDao().getTasksByDate(selectedDay.toEpoch)

}
