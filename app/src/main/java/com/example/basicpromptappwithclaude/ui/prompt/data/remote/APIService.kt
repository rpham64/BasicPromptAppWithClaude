package com.example.basicpromptappwithclaude.ui.prompt.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("/prompt")
    suspend fun send(@Query("prompt") prompt: String): String

    companion object {
        const val BASE_URL = "http://10.0.2.2:8000/"
    }
}