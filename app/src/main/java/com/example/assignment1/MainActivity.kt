package com.example.assignment1

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var taskEditText: EditText
    private lateinit var prioritySpinner: Spinner
    private lateinit var addButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var taskAdapter: TaskAdapter
    private val tasks = mutableListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskEditText = findViewById(R.id.taskEditText)
        prioritySpinner = findViewById(R.id.prioritySpinner)
        addButton = findViewById(R.id.addButton)
        recyclerView = findViewById(R.id.recyclerView)

        // Spinner setup
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.priority_levels, android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        prioritySpinner.adapter = adapter

        // RecyclerView setup
        taskAdapter = TaskAdapter(tasks, this::onDeleteTask)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = taskAdapter

        // Button Listener
        addButton.setOnClickListener {
            val taskName = taskEditText.text.toString()
            val priority = prioritySpinner.selectedItem.toString()
            if (taskName.isNotEmpty()) {
                tasks.add(Task(taskName, priority))
                taskAdapter.notifyItemInserted(tasks.size - 1)
                taskEditText.text.clear()
            }
        }
    }

    private fun onDeleteTask(position: Int) {
        tasks.removeAt(position)
        taskAdapter.notifyItemRemoved(position)
    }
}
