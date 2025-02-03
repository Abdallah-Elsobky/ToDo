package com.example.todo.ui.update_task_fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todo.R
import com.example.todo.database.TodoDatabase
import com.example.todo.database.entities.Task
import com.example.todo.databinding.FragmentAddTaskBinding
import com.example.todo.databinding.FragmentUpdateTaskBinding
import com.example.todo.utils.day
import com.example.todo.utils.month
import com.example.todo.utils.toEpoch
import com.example.todo.utils.toTime
import com.example.todo.utils.year
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar


class UpdateTaskFragment(val updatedTask: Task, val onTaskUpdated: () -> Unit) :
    BottomSheetDialogFragment() {
    private lateinit var binding: FragmentUpdateTaskBinding
    var selectedDate = Calendar.getInstance()
    var database = TodoDatabase.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateTaskBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindData()
        bindDateTv()
        initLesteners()
    }

    private fun initLesteners() {
        binding.updateBtn.setOnClickListener {
            val title = binding.titleET.text.toString()
            val description = binding.descriptionET.text.toString()
            if (title.trim().isEmpty() || description.trim().isEmpty()) return@setOnClickListener
            else {
                updatedTask.title = title
                updatedTask.description = description
                updatedTask.date = selectedDate.toEpoch
                updatedTask.time = selectedDate.toTime
                updatedTask.isDone = false
                database.taskDao().updateTask(updatedTask)
                onTaskUpdated()
                dismiss()
            }
        }
        binding.selectDateTV.setOnClickListener({

            val onDateSelected = DatePickerDialog.OnDateSetListener { _, year, month, day ->
                selectedDate.set(year, month, day)
                bindDateTv()
            }

            val picker = DatePickerDialog(
                requireContext(),
                onDateSelected,
                selectedDate.year,
                selectedDate.month,
                selectedDate.day
            )

            picker.datePicker.minDate = System.currentTimeMillis() - 1000
            picker.show()
        })
    }

    private fun bindDateTv() {
        binding.selectDateTV.text =
            "${selectedDate.year} : ${selectedDate.month + 1} : ${selectedDate.day}"
    }
    private fun bindData() {
        binding.titleET.setText(updatedTask.title)
        binding.descriptionET.setText(updatedTask.description)
    }
}
