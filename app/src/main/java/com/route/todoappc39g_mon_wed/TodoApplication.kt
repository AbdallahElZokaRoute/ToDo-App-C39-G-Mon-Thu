package com.route.todoappc39g_mon_wed

import android.app.Application
import com.route.todoappc39g_mon_wed.database.TasksDatabase

class TodoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        TasksDatabase.init(this)
    }
}
