package com.route.todoappc39g_mon_wed.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.route.todoappc39g_mon_wed.database.models.Task
import java.util.Date


@Dao
interface TasksDao {
    // 1- annotate Dao with @Dao annotation
    //              Insertion
    //              Deletion

    @Insert
    fun insertTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Query("SELECT * FROM Todos")
    fun getAllTasks(): MutableList<Task>

    // Where   == if
    @Query("SELECT * FROM Todos WHERE date = :dateTime")
    fun getTasksByDate(dateTime: Date): MutableList<Task>
}
