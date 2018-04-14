package com.mattchowning.todo_mvp

import retrofit2.Call
import retrofit2.http.GET

interface LocalServerService {

    companion object {
        private const val emulatorLocalHost = "10.0.2.2"
        const val baseUrl = "http://$emulatorLocalHost:3000/"
    }

    @GET("get")
    fun getItems(): Call<List<TaskItem>>
}