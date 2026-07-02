package com.example.basicpromptappwithclaude.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.basicpromptappwithclaude.ui.prompt.PromptScreen
import com.example.basicpromptappwithclaude.ui.theme.BasicPromptAppWithClaudeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicPromptAppWithClaudeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PromptScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}