package com.route.todoappc39g_mon_wed.fragments.addTask

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.route.todoappc39g_mon_wed.OnTaskAddedListener
import com.route.todoappc39g_mon_wed.R
import com.route.todoappc39g_mon_wed.clearTime
import com.route.todoappc39g_mon_wed.database.TasksDatabase
import com.route.todoappc39g_mon_wed.database.models.Task
import com.route.todoappc39g_mon_wed.databinding.FragmentAddTaskBinding
import java.util.Calendar

class AddTaskBottomSheet : BottomSheetDialogFragment() {
    lateinit var binding: FragmentAddTaskBinding
    lateinit var viewModel: AddTaskViewModel

    //2-
    var onTaskAddedListener: OnTaskAddedListener? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTaskBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel = ViewModelProvider(this).get(AddTaskViewModel::class.java)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.calendar = Calendar.getInstance()
        binding.selectTimeTv.setOnClickListener {
            val picker =
                TimePickerDialog(
                    requireContext(),
                    object : TimePickerDialog.OnTimeSetListener {
                        override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                            // Calendar object <->  Dates , Time
                            viewModel.calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                            viewModel.calendar.set(Calendar.MINUTE, minute)
//                            calendar.get(Calendar.AM_PM)
                            binding.selectTimeTv.text = "$hourOfDay:$minute"
                        }
                    },
                    viewModel.calendar.get(Calendar.HOUR_OF_DAY),
                    viewModel.calendar.get(Calendar.MINUTE),
                    false
                )
            picker.show()
        }
        binding.selectDateTv.setOnClickListener {
            val picker =
                DatePickerDialog(
                    requireContext(),
                    object : DatePickerDialog.OnDateSetListener {
                        override fun onDateSet(
                            view: DatePicker?,
                            year: Int,
                            month: Int,
                            dayOfMonth: Int
                        ) {
                            viewModel.calendar.set(Calendar.YEAR, year)
                            viewModel.calendar.set(Calendar.MONTH, month)
                            viewModel.calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                            binding.selectDateTv.text = "$dayOfMonth / ${month + 1} / $year"
                        }
                    },
                    viewModel.calendar.get(Calendar.YEAR),
                    viewModel.calendar.get(Calendar.MONTH),
                    viewModel.calendar.get(Calendar.DAY_OF_MONTH),


                    )
            picker.datePicker.minDate = System.currentTimeMillis()
            picker.show()
        }
        subscribeToLiveData()
    }

    fun subscribeToLiveData() {
        viewModel.isDoneLiveData.observe(viewLifecycleOwner) {
            if (it) {
                onTaskAddedListener?.onTaskAdded()
                dismiss()
            }
        }
    }
}

