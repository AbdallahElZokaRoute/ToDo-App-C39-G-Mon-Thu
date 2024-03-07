package com.route.todoappc39g_mon_wed.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.route.todoappc39g_mon_wed.database.models.Task

// Tasks Database ->  Dao -> getAlLTasks
@Database(entities = [Task::class], version = 1)            //       1
@TypeConverters(value = [DateTypeConverter::class])
abstract class TasksDatabase : RoomDatabase() {
    abstract fun getTasksDao(): TasksDao

    companion object {
        private var INSTANCE: TasksDatabase? = null
        private val DATABASE_NAME = "Tasks Database"

        // Singleton Pattern
        // Builder Pattern
        fun getInstance(): TasksDatabase {
            return INSTANCE!!
        }

        fun init(context: Context) {
            if (INSTANCE == null)
                INSTANCE = Room.databaseBuilder(context, TasksDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration() // FallbackToDestructive Migration
                    .allowMainThreadQueries() //  allow main thread queries
                    .build()
        }
    }

}
