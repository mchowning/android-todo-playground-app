package com.mattchowning.todo_mvp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.mattchowning.todo.repository.Repository
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

  @Inject lateinit var repository: Repository

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    checkNotNull(repository)

    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_main)
    replaceFragment(TaskListFragment(), supportFragmentManager, false)

  }

  companion object {
    fun replaceFragment(fragment: Fragment,
                        fragmentManager: FragmentManager?,
                        toBackstack: Boolean) {
      checkNotNull(fragmentManager)
      checkNotNull(fragment)
      val transaction = fragmentManager?.beginTransaction()
                                       ?.replace(R.id.contentFrame, fragment)
      if (toBackstack) transaction?.addToBackStack(fragment::class.toString())
      transaction?.commit()
    }
  }

}
