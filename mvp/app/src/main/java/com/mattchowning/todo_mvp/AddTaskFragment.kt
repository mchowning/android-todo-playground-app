package com.mattchowning.todo_mvp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.Toast
import kotlinx.android.synthetic.main.add_task_fragment.*

class AddTaskFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.add_task_fragment, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.add_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.menu_save) {
            Toast.makeText(context, "save button clicked", Toast.LENGTH_SHORT).show()
            val taskItem = TaskItem(task_title_edittext.text.toString(), task_description_edittext.text.toString())
            MainActivity.repository.addItem(taskItem)
            fragmentManager?.popBackStack()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}