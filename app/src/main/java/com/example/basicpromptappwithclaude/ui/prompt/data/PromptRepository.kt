package com.example.basicpromptappwithclaude.ui.prompt.data

import com.example.basicpromptappwithclaude.ui.prompt.data.remote.PromptRemoteDataSource
import com.example.basicpromptappwithclaude.ui.prompt.data.remote.PromptRemoteDataSourceImpl

interface PromptRepository {
    suspend fun sendMessage(message: String): Result<String>
}

class PromptRepositoryImpl(
    private val remoteDataSource: PromptRemoteDataSource = PromptRemoteDataSourceImpl()
) : PromptRepository {
    override suspend fun sendMessage(message: String): Result<String> {
        return remoteDataSource.sendMessage(message)
    }
}