package com.mattchowning.todo_mvp

import com.mattchowning.todo_mvp.dagger.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class TodoApplication : DaggerApplication() {

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
      DaggerAppComponent.builder()
          .todoApplication(this)
          .build()
}