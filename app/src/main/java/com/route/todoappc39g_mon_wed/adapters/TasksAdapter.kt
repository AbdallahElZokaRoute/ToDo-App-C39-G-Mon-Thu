package com.route.todoappc39g_mon_wed.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.route.todoappc39g_mon_wed.database.models.Task
import com.route.todoappc39g_mon_wed.databinding.ItemTaskBinding
import java.text.SimpleDateFormat
import java.util.Locale

class TasksAdapter(private var tasksList: List<Task>?) : Adapter<TasksAdapter.TasksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TasksViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tasksList?.size ?: 0
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        val item = tasksList?.get(position) ?: return
        holder.bind(item)
    }

    fun updateData(tasksList: List<Task>?) {
        this.tasksList = tasksList
        notifyDataSetChanged()
    }

    class TasksViewHolder(val binding: ItemTaskBinding) : ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.task = task
            binding.executePendingBindings()
            binding.invalidateAll()
        }
    }

}
