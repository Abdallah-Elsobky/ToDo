package com.example.todo.ui.Main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.todo.CalenderAdapter
import com.example.todo.model.Calender
import com.example.todo.R
import com.example.todo.model.Task
import com.example.todo.TaskAdapter
import com.example.todo.databinding.ActivityMainBinding
import com.example.todo.ui.Main.Fragments.SettingsFragment
import com.example.todo.ui.Main.Fragments.TaskFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val calender: ArrayList<Calender> = ArrayList()
        calender.add(Calender("Mon", "12"))
        calender.add(Calender("Mon", "13"))
        calender.add(Calender("Mon", "13"))
        calender.add(Calender("Mon", "13"))
        calender.add(Calender("Mon", "13"))
        calender.add(Calender("Fry", "14", true))
        calender.add(Calender("Fry", "14", true))
        calender.add(Calender("Fry", "14", true))
        val adapter2 = CalenderAdapter(calender)
        binding.dayRv.adapter = adapter2
        initListeners()
    }

    private fun initListeners() {
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.setting -> {
                    showFragment(SettingsFragment())
                }

                R.id.tasks -> {
                    showFragment(TaskFragment())
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(
            R.id.fragmentContainer,
            fragment
        ).commit()
    }

}