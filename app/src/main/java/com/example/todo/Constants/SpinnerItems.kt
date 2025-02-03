package com.example.todo.Constants

import com.example.todo.R
import com.example.todo.model.SpinnerItem

object SpinnerItems {
    val languages: List<SpinnerItem> = listOf(
        SpinnerItem(R.drawable.unitedkingdom, "English"),
        SpinnerItem(R.drawable.egypt, "Arabic")
    )

    val Modes: List<SpinnerItem> = listOf(
        SpinnerItem(R.drawable.sun, "Light Mode"),
        SpinnerItem(R.drawable.moon, "Dark Mode")
    )
}