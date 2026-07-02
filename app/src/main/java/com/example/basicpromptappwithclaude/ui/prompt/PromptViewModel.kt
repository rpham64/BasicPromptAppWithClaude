package com.example.basicpromptappwithclaude.ui.prompt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basicpromptappwithclaude.ui.prompt.data.PromptRepository
import com.example.basicpromptappwithclaude.ui.prompt.data.PromptRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PromptViewModel(
    private val repository: PromptRepository = PromptRepositoryImpl()
) : ViewModel() {

    private val _promptStateFlow: MutableStateFlow<String> = MutableStateFlow("")
    val promptStateFlow: StateFlow<String> = _promptStateFlow.asStateFlow()

    private val _responseStateFlow: MutableStateFlow<ResponseState> = MutableStateFlow(
        ResponseState("", null)
    )
    val responseStateFlow: StateFlow<ResponseState> = _responseStateFlow.asStateFlow()

    fun updatePrompt(prompt: String) {
        _promptStateFlow.value = prompt
    }

    fun submitPrompt() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.sendMessage(promptStateFlow.value)
            }

            result.fold(
                onSuccess = { response ->
                    _responseStateFlow.value = ResponseState(
                        response = response,
                        error = null
                    )
                },
                onFailure = { error ->
                    _responseStateFlow.value = ResponseState(
                        response = responseStateFlow.value.response,
                        error = error
                    )
                }
            )
        }
    }
}