package com.mattchowning.todo_mvp.dagger

import com.mattchowning.todo.repository.Repository
import com.mattchowning.todo_mvp.AddTaskFragment
import com.mattchowning.todo_mvp.MainActivity
import com.mattchowning.todo_mvp.TaskListFragment
import com.mattchowning.todo_mvp.TodoApplication
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import javax.inject.Scope

@Component(modules = [
  AndroidInjectionModule::class,
  AppModule::class,
  BindingModule::class
])
@AppScope
interface AppComponent : AndroidInjector<TodoApplication> {

  @Component.Builder
  interface Builder {
    fun build(): AppComponent
    @BindsInstance fun todoApplication(application: TodoApplication): Builder
  }
}

@Module
class AppModule {

  @Provides
  @AppScope
  fun getRepository(application: TodoApplication): Repository = Repository.get(application)
}

@Module
abstract class BindingModule {

  @ContributesAndroidInjector
  abstract fun mainActivity() : MainActivity

  @ContributesAndroidInjector
  abstract fun taskListFragment() : TaskListFragment

  @ContributesAndroidInjector
  abstract fun addTaskFragment() : AddTaskFragment
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope
