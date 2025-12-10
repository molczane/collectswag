package org.example.collectswag

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.collectswag.model.GameState
import org.example.collectswag.model.GameViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val viewModel: GameViewModel = viewModel { GameViewModel() }
        val gameState by viewModel.gameState.collectAsState()
        val score by viewModel.score.collectAsState()
        
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (gameState) {
                is GameState.Menu -> {
                    Text("Main Menu - Game State Management Active")
                }
                is GameState.Playing -> {
                    Text("Playing - Score: $score")
                }
                is GameState.GameOver -> {
                    val finalScore = (gameState as GameState.GameOver).finalScore
                    Text("Game Over - Final Score: $finalScore")
                }
            }
        }
    }
}