package com.mattchowning.todo_mvp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.Toast
import com.mattchowning.todo.repository.Repository
import com.mattchowning.todo.repository.room.TaskItem
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.add_task_fragment.*
import javax.inject.Inject

class AddTaskFragment : Fragment() {

  @Inject
  lateinit var repository: Repository

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidSupportInjection.inject(this)
    super.onCreate(savedInstanceState)
    setHasOptionsMenu(true)
  }

  override fun onCreateView(inflater: LayoutInflater,
                            container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.add_task_fragment, container, false)
  }

  override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
    inflater?.inflate(R.menu.add_fragment_menu, menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    if (item?.itemId == R.id.menu_save) {
      Toast.makeText(context, "save button clicked", Toast.LENGTH_SHORT).show()
      val taskItem = TaskItem(task_title_edittext.text.toString(),
                              task_description_edittext.text.toString())
      repository.insertItems(taskItem)
      fragmentManager?.popBackStack()
      return true
    }
    return super.onOptionsItemSelected(item)
  }
}