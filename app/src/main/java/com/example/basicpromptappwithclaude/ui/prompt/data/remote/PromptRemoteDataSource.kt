package com.example.basicpromptappwithclaude.ui.prompt.data.remote

import com.example.basicpromptappwithclaude.ui.prompt.data.remote.APIService.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

interface PromptRemoteDataSource {
    suspend fun sendMessage(message: String): Result<String>
}

class PromptRemoteDataSourceImpl(
    private val apiService: APIService =
        Retrofit
            .Builder()
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .baseUrl(BASE_URL)
            .build()
            .create<APIService>()
) : PromptRemoteDataSource {

    override suspend fun sendMessage(message: String): Result<String> {
        return runCatching { apiService.send(message) }
    }
}