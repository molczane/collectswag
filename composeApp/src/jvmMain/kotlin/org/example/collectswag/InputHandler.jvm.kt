package org.example.collectswag.util

/**
 * JVM (Desktop) implementation of InputHandler.
 * Handles keyboard input (Space, Up arrow) for jump actions.
 */
actual class InputHandler {
    private var jumpTriggered = false
    
    actual fun isJumpTriggered(): Boolean {
        return jumpTriggered
    }
    
    actual fun reset() {
        jumpTriggered = false
    }
    
    /**
     * Call this method when a keyboard key press is detected.
     */
    fun onKeyPressed() {
        jumpTriggered = true
    }
}
