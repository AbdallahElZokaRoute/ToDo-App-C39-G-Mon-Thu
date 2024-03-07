package com.route.todoappc39g_mon_wed.fragments.taskList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.route.todoappc39g_mon_wed.adapters.TasksAdapter
import com.route.todoappc39g_mon_wed.clearTime
import com.route.todoappc39g_mon_wed.database.TasksDatabase
import com.route.todoappc39g_mon_wed.database.models.Task
import com.route.todoappc39g_mon_wed.databinding.FragmentTasksBinding
import java.util.Calendar
import java.util.Date

class TasksListFragment : Fragment() {
    lateinit var binding: FragmentTasksBinding
    lateinit var adapter: TasksAdapter
    lateinit var viewModel: TasksViewModel
    // Data Binding & Binding Adapters

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTasksBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(TasksViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TasksAdapter(null)
        viewModel.calendar = Calendar.getInstance()
        // Practice - Assignment -> Todo 
        binding.rvTasks.adapter = adapter
        val list = TasksDatabase.getInstance().getTasksDao().getAllTasks()

        binding.calendarView.setOnDateChangedListener { widget, date, selected ->
            val year = date.year
            val month = date.month - 1
            // 9:50:20 PM  X 9:50:21 PM
            val dayOfMonth = date.day
            viewModel.calendar.clearTime()

            Log.e("TAG", "onViewCreated: Library  $month")
            Log.e("TAG", "onViewCreated: Calendar  ${viewModel.calendar.get(Calendar.MONTH)}")
            viewModel.calendar.set(Calendar.YEAR, year)
            viewModel.calendar.set(Calendar.MONTH, month) // index = 0 -> January
            //              January -> 1
            viewModel.calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            viewModel.getTasks()
        }
        val today = CalendarDay.today()
        binding.calendarView.invalidate()
        // 1- Swipe-to-Delete
        // 2- Edit Activity
        // 3- Settings Fragment - (Language - Mode)
        val minDate: Date? = null
        val maxDate: Date? = null
        list.forEach {
            minDate

        }
        subscribeToLiveData()
    }

    fun subscribeToLiveData() {
        viewModel.tasksLiveData.observe(viewLifecycleOwner) { tasks ->
            adapter.updateData(tasks)
        }
    }

    fun getTasks() {
        viewModel.getTasks()
    }
}
