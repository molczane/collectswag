package org.example.collectswag.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel that manages the game state and handles state transitions.
 */
class GameViewModel : ViewModel() {
    
    private val _gameState = MutableStateFlow<GameState>(GameState.Menu)
    val gameState: StateFlow<GameState> = _gameState.asStateFlow()
    
    private val _score = MutableStateFlow(0)
    val score: StateFlow<Int> = _score.asStateFlow()
    
    private val _playerCharacter = MutableStateFlow(PlayerCharacter())
    val playerCharacter: StateFlow<PlayerCharacter> = _playerCharacter.asStateFlow()
    
    private var gameLoopJob: Job? = null
    
    companion object {
        private const val TARGET_FPS = 60
        private const val FRAME_TIME_MS = 1000L / TARGET_FPS
        private const val DELTA_TIME = 1f / TARGET_FPS // Fixed delta time in seconds
    }
    
    /**
     * Transitions to the Playing state and resets the score.
     */
    fun startGame() {
        if (_gameState.value is GameState.Menu || _gameState.value is GameState.GameOver) {
            _score.value = 0
            _gameState.value = GameState.Playing
            resetPlayerCharacter()
            startGameLoop()
        }
    }
    
    /**
     * Transitions to the GameOver state with the current score.
     */
    fun endGame() {
        if (_gameState.value is GameState.Playing) {
            stopGameLoop()
            _gameState.value = GameState.GameOver(_score.value)
        }
    }
    
    /**
     * Starts the game loop for updating game state.
     */
    private fun startGameLoop() {
        stopGameLoop()
        gameLoopJob = viewModelScope.launch {
            while (true) {
                if (_gameState.value is GameState.Playing) {
                    updateGame(DELTA_TIME)
                }
                
                delay(FRAME_TIME_MS)
            }
        }
    }
    
    /**
     * Stops the game loop.
     */
    private fun stopGameLoop() {
        gameLoopJob?.cancel()
        gameLoopJob = null
    }
    
    /**
     * Updates game state each frame.
     */
    private fun updateGame(deltaTime: Float) {
        // Update character animation and movement
        _playerCharacter.value = _playerCharacter.value
            .updateAnimation(deltaTime)
            .updateMovement(deltaTime)
    }
    
    /**
     * Resets the player character to initial state.
     */
    private fun resetPlayerCharacter() {
        _playerCharacter.value = PlayerCharacter()
    }
    
    /**
     * Initializes the player character with screen dimensions for proper positioning.
     */
    fun initializeCharacter(screenWidth: Float, screenHeight: Float) {
        val startX = screenWidth * 0.15f // Position character at 15% from left
        val groundedY = PlayerCharacter.getGroundedY(screenHeight, _playerCharacter.value.height)
        
        _playerCharacter.value = _playerCharacter.value.copy(
            x = startX,
            y = groundedY,
            screenHeight = screenHeight
        )
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
