package com.route.todoappc39g_mon_wed.fragments.taskList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.route.todoappc39g_mon_wed.database.TasksDatabase
import com.route.todoappc39g_mon_wed.database.models.Task
import java.util.Calendar

class TasksViewModel : ViewModel() {
    lateinit var calendar: Calendar // Calendar View X
    val tasksLiveData = MutableLiveData<List<Task>>()

    fun getTasks() {
        val updatedList = TasksDatabase
            .getInstance() // View Model
            .getTasksDao()
            .getTasksByDate(calendar.time)
        tasksLiveData.value = updatedList
    }

}
