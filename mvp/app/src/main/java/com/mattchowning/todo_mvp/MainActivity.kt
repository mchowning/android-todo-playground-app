package com.mattchowning.todo_mvp

import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.mattchowning.todo_mvp.room.AppDatabase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(TaskListFragment(), supportFragmentManager, false)

        repository = Repository(this)
    }

    companion object {
        lateinit var repository: Repository

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
