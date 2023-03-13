package com.example.bottomnavigationwork.ui.home.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigationwork.databinding.ItemTaskBinding
import com.example.bottomnavigationwork.model.Task
import com.geektech.taskmanager.databinding.ItemTaskBinding

class TaskAdapter (private val longClickListener:(Task)->Unit):
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private val data = arrayListOf<Task>()
    private var changeColor = true

    fun addTask(tasks: List<Task>) {
        data.clear()
        data.addAll(tasks)
        notifyItemChanged(0)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.count()
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            itemView.setOnLongClickListener {
                longClickListener(task)
                false

            }
            binding.title.text = task.title
            binding.description.text = task.description

            if (changeColor) {
                binding.itemTask.setBackgroundColor(Color.BLACK)
                binding.description.setTextColor(Color.WHITE)
                binding.title.setTextColor(Color.WHITE)
                changeColor = false
            } else {
                binding.itemTask.setBackgroundColor(Color.WHITE)
                binding.description.setTextColor(Color.BLACK)
                binding.title.setTextColor(Color.BLACK)
                changeColor = true
            }
        }
    }
}