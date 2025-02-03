package com.example.todo.ui.Home

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.todo.R
import com.example.todo.databinding.ActivityMainBinding
import com.example.todo.ui.Home.Fragments.SettingsFragment
import com.example.todo.ui.Home.Fragments.TaskFragment
import com.example.todo.ui.add_task_fragment.AddTaskFragment

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val taskFragment = TaskFragment()
    val settingsFragment = SettingsFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showFragment(taskFragment)
        initListeners()
    }


    private fun initListeners() {
        binding.fabAddItem.setOnClickListener({
            AddTaskFragment({
                taskFragment.refreshAdapter()
            }).show(supportFragmentManager, null)
        })

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.setting -> {
                    showFragment(settingsFragment)
                }
                R.id.tasks -> {
                    showFragment(taskFragment)
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