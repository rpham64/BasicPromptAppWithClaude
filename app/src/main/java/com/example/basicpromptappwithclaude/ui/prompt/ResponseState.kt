package com.example.basicpromptappwithclaude.ui.prompt

data class ResponseState(
    val response: String = "",
    val error: Throwable? = null
)
