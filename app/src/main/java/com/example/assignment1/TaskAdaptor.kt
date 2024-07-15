package com.example.assignment1

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(
    private val tasks: MutableList<Task>,
    private val onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position], position)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val priorityDot: View = itemView.findViewById(R.id.priorityDot)
        private val taskNameTextView: TextView = itemView.findViewById(R.id.taskNameTextView)
        private val deleteButton: Button = itemView.findViewById(R.id.deleteButton)

        fun bind(task: Task, position: Int) {
            taskNameTextView.text = "${task.name} (Priority: ${task.priority})"

            // Set dot color based on priority
            val drawable = priorityDot.background as GradientDrawable
            when (task.priority) {
                "High" -> drawable.setColor(Color.RED)
                "Medium" -> drawable.setColor(Color.YELLOW)
                "Low" -> drawable.setColor(Color.GREEN)
            }

            deleteButton.setOnClickListener {
                onDeleteClick(position)
            }
        }
    }
}
