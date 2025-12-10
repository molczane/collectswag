package org.example.collectswag.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel that manages the game state and handles state transitions.
 */
class GameViewModel : ViewModel() {
    
    private val _gameState = MutableStateFlow<GameState>(GameState.Menu)
    val gameState: StateFlow<GameState> = _gameState.asStateFlow()
    
    private val _score = MutableStateFlow(0)
    val score: StateFlow<Int> = _score.asStateFlow()
    
    /**
     * Transitions to the Playing state and resets the score.
     */
    fun startGame() {
        if (_gameState.value is GameState.Menu || _gameState.value is GameState.GameOver) {
            _score.value = 0
            _gameState.value = GameState.Playing
        }
    }
    
    /**
     * Transitions to the GameOver state with the current score.
     */
    fun endGame() {
        if (_gameState.value is GameState.Playing) {
            _gameState.value = GameState.GameOver(_score.value)
        }
    }
    
    /**
     * Transitions back to the Menu state.
     */
    fun returnToMenu() {
        if (_gameState.value is GameState.GameOver) {
            _gameState.value = GameState.Menu
        }
    }
    
    /**
     * Restarts the game from GameOver state.
     */
    fun restartGame() {
        if (_gameState.value is GameState.GameOver) {
            _score.value = 0
            _gameState.value = GameState.Playing
        }
    }
    
    /**
     * Increments the score by the specified amount.
     */
    fun addScore(points: Int) {
        if (_gameState.value is GameState.Playing) {
            _score.value += points
        }
    }
    
    /**
     * Resets the score to zero.
     */
    fun resetScore() {
        _score.value = 0
    }
}
