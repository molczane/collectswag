package org.example.collectswag.model

/**
 * Represents the different states of the game.
 */
sealed class GameState {
    /**
     * The main menu state where the player can start the game.
     */
    data object Menu : GameState()
    
    /**
     * The active playing state where the game is running.
     */
    data object Playing : GameState()
    
    /**
     * The game over state showing the final score and restart options.
     */
    data class GameOver(val finalScore: Int) : GameState()
}
