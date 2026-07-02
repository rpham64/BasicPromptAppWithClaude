package com.example.basicpromptappwithclaude.ui.prompt

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PromptScreen(
    modifier: Modifier = Modifier,
    viewModel: PromptViewModel = viewModel()
) {
    val prompt by viewModel.promptStateFlow.collectAsStateWithLifecycle()
    val responseState by viewModel.responseStateFlow.collectAsStateWithLifecycle()

    PromptScreen(
        prompt = prompt,
        onPromptChanged = { newPrompt -> viewModel.updatePrompt(newPrompt) },
        onSubmit = { viewModel.submitPrompt() },
        responseState = responseState,
        modifier = modifier
    )
}

@Composable
fun PromptScreen(
    prompt: String,
    onPromptChanged: (String) -> Unit,
    onSubmit: (String) -> Unit,
    responseState: ResponseState,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        TextField(
            value = prompt,
            onValueChange = { newPrompt -> onPromptChanged(newPrompt) },
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
        )
        Button(
            onClick = { onSubmit(prompt) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Submit")
        }
        Text(
            text = if (responseState.error != null) {
                responseState.error.toString()
            } else {
                responseState.response
            },
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                .wrapContentHeight(Alignment.CenterVertically)
        )
    }
}

@Preview
@Composable
fun PromptScreenPreview() {
    PromptScreen(
        prompt = "What is the capital of the state of California?",
        onPromptChanged = { },
        onSubmit = { },
        responseState = ResponseState(
            response = "Sacramento",
            error = null
        )
    )
}