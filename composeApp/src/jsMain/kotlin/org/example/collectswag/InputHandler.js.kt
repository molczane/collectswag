package org.example.collectswag.util

/**
 * JS (Web) implementation of InputHandler.
 * Handles keyboard (Space, Up arrow) and mouse click input for jump actions.
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
     * Call this method when a keyboard key press or mouse click is detected.
     */
    fun onInputDetected() {
        jumpTriggered = true
    }
}
