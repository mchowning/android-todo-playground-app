package com.mattchowning.todo.repository

import com.mattchowning.todo.repository.room.TaskItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

internal interface LocalServerService {

  companion object {
    private const val emulatorLocalHost = "10.0.2.2"
    const val baseUrl = "http://${emulatorLocalHost}:3000/"
  }

  @GET("get")
  fun getItems(): Call<List<TaskItem>>

  @POST("post")
  fun insertItem(@Body taskItem: TaskItem): Call<List<TaskItem>>
}