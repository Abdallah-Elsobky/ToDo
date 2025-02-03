package com.example.todo.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val date: Long,
    val time: String,
    var isDone: Boolean = false
)
