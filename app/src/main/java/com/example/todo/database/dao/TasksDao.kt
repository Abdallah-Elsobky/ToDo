package com.example.todo.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todo.database.entities.Task

@Dao
interface TasksDao {
    @Insert
    fun addTask(task: Task)

    @Query("SELECT * FROM Task")
    fun getAllTasks(): List<Task>

    @Query("SELECT * FROM Task WHERE date = :date")
    fun getTasksByDate(date: Long): List<Task>

    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)
}