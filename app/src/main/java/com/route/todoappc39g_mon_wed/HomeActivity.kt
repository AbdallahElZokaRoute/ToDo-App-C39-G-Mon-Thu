package com.route.todoappc39g_mon_wed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.route.todoappc39g_mon_wed.databinding.ActivityHomeBinding
import com.route.todoappc39g_mon_wed.fragments.addTask.AddTaskBottomSheet
import com.route.todoappc39g_mon_wed.fragments.SettingsFragment
import com.route.todoappc39g_mon_wed.fragments.taskList.TasksListFragment

class HomeActivity : AppCompatActivity() {
    // To Do App <-> Eng / Nadia
    // Room Data base - Database ,
    // SQLite -> Room
    // Contact App

    // Contact 1 (RAM -> Random Access Memory )
    // Contact 2 (RAM -> Random Access Memory )

    // X

    // Mobile -> Storage Android Device
    //             Files
    // Files ->
    // 1.txt -> 114.txt
    // Read From File
    // Update - Delete - Insert
    // Files Manipulation ->
    // Database  -> Files Manipulation
    // SQLite <-> MySql - SQL Server
    // Sequential Query Language <-> SQL

    // Room Database         // Table Name
    lateinit var binding: ActivityHomeBinding
    lateinit var taskListFragment: TasksListFragment
    // Validation when typing in compile time

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.fabAddTask.setOnClickListener {
            val addTaskBottomSheet = AddTaskBottomSheet()
            addTaskBottomSheet.onTaskAddedListener = object : OnTaskAddedListener {
                override fun onTaskAdded() {
                    // Reload Fragment ->  TaskListFragment
                    if (taskListFragment.isVisible)
                        taskListFragment.getTasks()
                }
            }
            addTaskBottomSheet.show(supportFragmentManager, null)
        }
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.tasks -> {
                    taskListFragment = TasksListFragment()
                    pushFragment(taskListFragment)
                }

                R.id.settings -> {
                    pushFragment(SettingsFragment())
                }
            }
            return@setOnItemSelectedListener true
        }
        binding.bottomNavigation.selectedItemId = R.id.tasks


        // Todo App
        // Save Notes on Local Database Storage
        // Date
        // insert
        // Create
        // Update
        // Delete

    }

    private fun pushFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.content.fragmentContainer.id, fragment)
            .commit()
    }
}