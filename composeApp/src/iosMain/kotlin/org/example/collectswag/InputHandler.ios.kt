package org.example.collectswag.util

/**
 * iOS implementation of InputHandler.
 * Handles touch input for jump actions.
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
     * Call this method when a touch event is detected.
     */
    fun onTouchDetected() {
        jumpTriggered = true
    }
}
