package com.mattchowning.todo_mvp

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.mattchowning.todo.repository.Repository
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.tasks_list_fragment.*
import javax.inject.Inject

class TaskListFragment : Fragment() {

  private val taskListAdapter = TaskListAdapter()

  @Inject
  lateinit var repository: Repository

  override fun onAttach(context: Context?) {
    AndroidSupportInjection.inject(this)
    super.onAttach(context)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    repository.allTasks
        .observe(this,
                 Observer { taskList ->
                   if (taskList != null) {
                     taskListAdapter.taskItems = taskList
                     taskListAdapter.notifyDataSetChanged()
                   }
                 })
  }

  override fun onCreateView(inflater: LayoutInflater,
                            container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.tasks_list_fragment, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    recyclerView.apply {
      layoutManager = LinearLayoutManager(context)
      adapter = taskListAdapter
    }

    floatingActionButton.setOnClickListener {
      Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
      MainActivity.replaceFragment(AddTaskFragment(), fragmentManager, true)
    }
  }


}