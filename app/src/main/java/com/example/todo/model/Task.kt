package com.example.todo.model

data class Task(val title: String, val content: String, val time: String, var isDone: Boolean = false)
