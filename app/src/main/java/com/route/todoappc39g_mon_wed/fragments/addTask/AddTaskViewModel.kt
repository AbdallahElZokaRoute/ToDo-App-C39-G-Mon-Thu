package com.route.todoappc39g_mon_wed.fragments.addTask

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.route.todoappc39g_mon_wed.R
import com.route.todoappc39g_mon_wed.clearTime
import com.route.todoappc39g_mon_wed.database.TasksDatabase
import com.route.todoappc39g_mon_wed.database.models.Task
import java.util.Calendar

class AddTaskViewModel : ViewModel() {
    val titleLiveData = MutableLiveData("")
    val descriptionLiveData = MutableLiveData("")
    val isDoneLiveData = MutableLiveData(false)
    val titleErrorLiveData = MutableLiveData<String?>()
    val descriptionErrorLiveData = MutableLiveData<String?>()
    lateinit var calendar: Calendar
    fun addTask() {
        if (validateFields()) {
            calendar.clearTime()
            val task = Task(
                title = titleLiveData.value.toString(),
                description = descriptionLiveData.value.toString(),
                date = calendar.time,
                isDone = false
            )

            TasksDatabase
                .getInstance()
                .getTasksDao()
                .insertTask(task)
            //3-
            isDoneLiveData.value = true
        }
    }

    private fun validateFields(): Boolean {
        // ""                         // "              "    "     Hello   "
        if (titleLiveData.value?.isEmpty() == true || titleLiveData.value?.isBlank() == true) {
            titleErrorLiveData.value = "Required"
            return false
        } else
            titleErrorLiveData.value = null
        if (descriptionLiveData.value?.isEmpty() == true || descriptionLiveData.value?.isBlank() == true) {
            descriptionErrorLiveData.value = "Required"
            return false
        } else
            descriptionErrorLiveData.value = null
        return true
    }
}
