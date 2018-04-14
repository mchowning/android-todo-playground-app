package com.mattchowning.todo_mvp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(TaskListFragment(), supportFragmentManager, false)
    }

    companion object {
        val repository = Repository()

        fun replaceFragment(fragment: Fragment, fragmentManager: FragmentManager?, toBackstack: Boolean) {
            checkNotNull(fragmentManager)
            checkNotNull(fragment)
            val transaction = fragmentManager?.beginTransaction()
                           ?.replace(R.id.contentFrame, fragment)
            if (toBackstack) transaction?.addToBackStack(fragment::class.toString())
            transaction?.commit()
        }
    }
}
