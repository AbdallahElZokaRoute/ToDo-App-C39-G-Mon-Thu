package com.route.todoappc39g_mon_wed.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.route.todoappc39g_mon_wed.R.drawable
import com.route.todoappc39g_mon_wed.database.TasksDatabase
import com.route.todoappc39g_mon_wed.database.models.Task
import com.route.todoappc39g_mon_wed.databinding.ItemTaskBinding
import java.text.SimpleDateFormat
import java.util.Locale

class TasksAdapter(private var tasksList: MutableList<Task>?) :
    Adapter<TasksAdapter.TasksViewHolder>() {

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
        holder.binding.rightView.setOnClickListener {
            TasksDatabase.getInstance(holder.binding.root.context).getTasksDao().deleteTask(item)
            tasksList!!.remove(item)
            notifyItemRemoved(position)
        }
    }

    fun updateData(tasksList: MutableList<Task>) {
        this.tasksList = tasksList
        notifyDataSetChanged()
    }

    class TasksViewHolder(val binding: ItemTaskBinding) : ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.title.text = task.title
            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val dateAsString = simpleDateFormat.format(task.date!!)
            binding.time.text = dateAsString
            binding.rightView.setImageResource(drawable.ic_delete)
        }

    }

}
