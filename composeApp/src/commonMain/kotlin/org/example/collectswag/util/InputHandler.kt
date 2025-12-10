package org.example.collectswag.util

/**
 * Platform-specific input handler for detecting jump actions.
 * Each platform implements its own input detection mechanism.
 */
expect class InputHandler() {
    /**
     * Returns true if a jump action was detected (touch, click, or key press).
     */
    fun isJumpTriggered(): Boolean
    
    /**
     * Resets the input state after processing.
     */
    fun reset()
}
