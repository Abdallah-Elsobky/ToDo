package com.example.todo

import android.app.Application
import com.example.todo.database.TodoDatabase

class MyApplication : Application() {
    @Override
    override fun onCreate() {
        super.onCreate()
        TodoDatabase.init(this)
    }
}