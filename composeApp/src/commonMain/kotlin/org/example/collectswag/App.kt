package org.example.collectswag

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.collectswag.model.GameState
import org.example.collectswag.model.GameViewModel
import org.example.collectswag.ui.GameOverScreen
import org.example.collectswag.ui.GameScreen
import org.example.collectswag.ui.MainMenuScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val viewModel: GameViewModel = viewModel { GameViewModel() }
        val gameState by viewModel.gameState.collectAsState()
        val score by viewModel.score.collectAsState()
        val playerCharacter by viewModel.playerCharacter.collectAsState()
        
        when (gameState) {
            is GameState.Menu -> {
                MainMenuScreen(
                    highScore = 0, // TODO: Implement high score persistence in future phase
                    onPlayClick = { viewModel.startGame() }
                )
            }
            is GameState.Playing -> {
                GameScreen(
                    score = score,
                    playerCharacter = playerCharacter,
                    onScreenSizeAvailable = { width, height ->
                        viewModel.initializeCharacter(width, height)
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
            is GameState.GameOver -> {
                val finalScore = (gameState as GameState.GameOver).finalScore
                GameOverScreen(
                    finalScore = finalScore,
                    onRestartClick = { viewModel.restartGame() },
                    onMenuClick = { viewModel.returnToMenu() }
                )
            }
        }
    }
}