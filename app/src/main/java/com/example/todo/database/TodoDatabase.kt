package com.example.todo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todo.database.dao.TasksDao
import com.example.todo.database.entities.Task

@Database(entities = [Task::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun taskDao(): TasksDao

    companion object {
        const val DATABASE_NAME = "todo-db"
        var database: TodoDatabase? = null
        fun init(context: Context) {
            if (database == null) {
                database =
                    Room.databaseBuilder(context, TodoDatabase::class.java, DATABASE_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
            }
        }

        fun getInstance(): TodoDatabase {
            return database!!
        }
    }
}