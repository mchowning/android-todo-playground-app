package com.mattchowning.todo_mvp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mattchowning.todo.repository.room.TaskItem
import kotlinx.android.synthetic.main.task_list_row.view.*
import java.util.*

class TaskListAdapter : RecyclerView.Adapter<TaskListAdapter.TaskItemViewHolder>() {

  var taskItems: List<TaskItem> = Collections.emptyList()

  class TaskItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: TaskItem) = with(itemView) {
      titleTextView.text = item.title
      descriptionTextView.text = item.description
      uniqueIdTextView.text = item.uniqueId.toString()
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
    val v = LayoutInflater.from(parent.context).inflate(R.layout.task_list_row, parent, false)
    return TaskItemViewHolder(v)
  }

  override fun getItemCount(): Int = taskItems.size

  override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
    holder.bind(taskItems[position])
  }
}
