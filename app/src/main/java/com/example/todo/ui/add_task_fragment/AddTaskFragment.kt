package com.example.todo.ui.add_task_fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todo.R
import com.example.todo.database.TodoDatabase
import com.example.todo.database.entities.Task
import com.example.todo.databinding.FragmentAddTaskBinding
import com.example.todo.utils.day
import com.example.todo.utils.month
import com.example.todo.utils.toEpoch
import com.example.todo.utils.toTime
import com.example.todo.utils.year
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar


class AddTaskFragment(val onTaskAdd: () -> Unit) : BottomSheetDialogFragment() {
    lateinit var binding: FragmentAddTaskBinding
    var selectedDate = Calendar.getInstance()
    var database = TodoDatabase.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTaskBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindDateTv()
        initListeners()
    }

    private fun initListeners() {
        binding.addTaskBtn.setOnClickListener({
            val title = binding.titleET.text.toString()
            val description = binding.descriptionET.text.toString()
            if (!isValidInput(title, description)) return@setOnClickListener
            else {
                val task =
                    Task(
                        title = title,
                        description = description,
                        date = selectedDate.toEpoch,
                        time = selectedDate.toTime,
                        isDone = false
                    )
                database.taskDao().addTask(task)
                onTaskAdd()
                dismiss()
            }
        })

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

    private fun isValidInput(title: String, description: String): Boolean {
        var valid = true
        if (title.trim().isEmpty()) {
            binding.titleInputLayout.error = getString(R.string.title_must_be_at_least_3_characters)
            valid = false
        } else {
            binding.titleInputLayout.error = null
        }
        if (description.trim().isEmpty()) {
            binding.descriptionInputLayout.error =
                getString(R.string.description_must_be_at_least_5_characters)
            valid = false
        } else {
            binding.descriptionInputLayout.error = null
        }
        return valid
    }
}