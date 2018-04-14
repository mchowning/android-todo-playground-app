package com.mattchowning.todo_mvp

import java.util.*

data class TaskItem(val title: String,
                    val description : String,
                    val uniqueId : String = UUID.randomUUID().toString())
