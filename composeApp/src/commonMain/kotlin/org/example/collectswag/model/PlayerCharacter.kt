package org.example.collectswag.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

/**
 * Represents the player character with position, animation state, and rendering.
 */
data class PlayerCharacter(
    val x: Float = 0f,
    val y: Float = 0f,
    val width: Float = 64f,  // Character width (16 pixels * 4 pixel size)
    val height: Float = 88f, // Character height (22 pixels * 4 pixel size)
    val isJumping: Boolean = false,
    val velocityY: Float = 0f,
    val animationFrame: Int = 0,
    val animationTimer: Float = 0f,
    val screenHeight: Float = 0f // Store screen height for ground level calculation
) {
    companion object {
        const val ANIMATION_FRAME_DURATION = 0.15f // seconds per frame
        const val RUNNING_FRAME_COUNT = 4
        const val ROAD_HEIGHT_RATIO = 0.2f // Road takes 20% of screen height
        const val RUNNING_SPEED = 200f // pixels per second
        
        // Jump physics constants
        const val JUMP_VELOCITY = -600f // Initial upward velocity (negative = up)
        const val GRAVITY = 1800f // Downward acceleration (positive = down)
        
        /**
         * Calculates the ground level Y coordinate (top of the road).
         */
        fun getGroundLevel(screenHeight: Float): Float {
            return screenHeight * (1f - ROAD_HEIGHT_RATIO)
        }
        
        /**
         * Calculates the character's Y position when grounded.
         */
        fun getGroundedY(screenHeight: Float, characterHeight: Float): Float {
            return getGroundLevel(screenHeight) - characterHeight
        }
        
        // Character colors
        private val SKIN_COLOR = Color(0xFFFFDBB5)
        private val HAIR_COLOR = Color(0xFFFFD700) // Blonde/gold
        private val SHIRT_COLOR = Color(0xFF4169E1) // Royal blue
        private val PANTS_COLOR = Color(0xFF2F4F4F) // Dark slate gray
        private val SHOE_COLOR = Color(0xFF8B4513) // Saddle brown
        private val OUTLINE_COLOR = Color(0xFF000000) // Black
    }
    
    /**
     * Draws the player character at its current position with the appropriate animation frame.
     */
    fun draw(drawScope: DrawScope) {
        with(drawScope) {
            if (isJumping) {
                drawJumpPose()
            } else {
                drawRunningFrame(animationFrame)
            }
        }
    }
    
    /**
     * Draws the running animation frame.
     */
    private fun DrawScope.drawRunningFrame(frame: Int) {
        val pixelSize = 4f
        val baseX = x
        val baseY = y
        
        // Determine leg positions based on frame
        val legOffset = when (frame % RUNNING_FRAME_COUNT) {
            0 -> 0 // Both legs together
            1 -> 1 // Left leg forward
            2 -> 0 // Both legs together
            3 -> -1 // Right leg forward
            else -> 0
        }
        
        // Head (blonde hair)
        drawRect(
            color = HAIR_COLOR,
            topLeft = Offset(baseX + pixelSize * 4, baseY),
            size = Size(pixelSize * 8, pixelSize * 8)
        )
        
        // Face
        drawRect(
            color = SKIN_COLOR,
            topLeft = Offset(baseX + pixelSize * 5, baseY + pixelSize * 2),
            size = Size(pixelSize * 6, pixelSize * 6)
        )
        
        // Eyes
        drawRect(
            color = OUTLINE_COLOR,
            topLeft = Offset(baseX + pixelSize * 6, baseY + pixelSize * 4),
            size = Size(pixelSize, pixelSize)
        )
        drawRect(
            color = OUTLINE_COLOR,
            topLeft = Offset(baseX + pixelSize * 9, baseY + pixelSize * 4),
            size = Size(pixelSize, pixelSize)
        )
        
        // Body (shirt)
        drawRect(
            color = SHIRT_COLOR,
            topLeft = Offset(baseX + pixelSize * 4, baseY + pixelSize * 8),
            size = Size(pixelSize * 8, pixelSize * 6)
        )
        
        // Arms
        drawRect(
            color = SKIN_COLOR,
            topLeft = Offset(baseX + pixelSize * 2, baseY + pixelSize * 9),
            size = Size(pixelSize * 2, pixelSize * 4)
        )
        drawRect(
            color = SKIN_COLOR,
            topLeft = Offset(baseX + pixelSize * 12, baseY + pixelSize * 9),
            size = Size(pixelSize * 2, pixelSize * 4)
        )
        
        // Pants/Legs
        // Left leg
        drawRect(
            color = PANTS_COLOR,
            topLeft = Offset(baseX + pixelSize * 5, baseY + pixelSize * 14 + legOffset * pixelSize),
            size = Size(pixelSize * 3, pixelSize * 6)
        )
        
        // Right leg
        drawRect(
            color = PANTS_COLOR,
            topLeft = Offset(baseX + pixelSize * 8, baseY + pixelSize * 14 - legOffset * pixelSize),
            size = Size(pixelSize * 3, pixelSize * 6)
        )
        
        // Shoes
        // Left shoe
        drawRect(
            color = SHOE_COLOR,
            topLeft = Offset(baseX + pixelSize * 5, baseY + pixelSize * 20 + legOffset * pixelSize),
            size = Size(pixelSize * 3, pixelSize * 2)
        )
        
        // Right shoe
        drawRect(
            color = SHOE_COLOR,
            topLeft = Offset(baseX + pixelSize * 8, baseY + pixelSize * 20 - legOffset * pixelSize),
            size = Size(pixelSize * 3, pixelSize * 2)
        )
    }
    
    /**
     * Draws the jump pose.
     */
    private fun DrawScope.drawJumpPose() {
        val pixelSize = 4f
        val baseX = x
        val baseY = y
        
        // Head (blonde hair)
        drawRect(
            color = HAIR_COLOR,
            topLeft = Offset(baseX + pixelSize * 4, baseY),
            size = Size(pixelSize * 8, pixelSize * 8)
        )
        
        // Face
        drawRect(
            color = SKIN_COLOR,
            topLeft = Offset(baseX + pixelSize * 5, baseY + pixelSize * 2),
            size = Size(pixelSize * 6, pixelSize * 6)
        )
        
        // Eyes
        drawRect(
            color = OUTLINE_COLOR,
            topLeft = Offset(baseX + pixelSize * 6, baseY + pixelSize * 4),
            size = Size(pixelSize, pixelSize)
        )
        drawRect(
            color = OUTLINE_COLOR,
            topLeft = Offset(baseX + pixelSize * 9, baseY + pixelSize * 4),
            size = Size(pixelSize, pixelSize)
        )
        
        // Body (shirt)
        drawRect(
            color = SHIRT_COLOR,
            topLeft = Offset(baseX + pixelSize * 4, baseY + pixelSize * 8),
            size = Size(pixelSize * 8, pixelSize * 6)
        )
        
        // Arms raised
        drawRect(
            color = SKIN_COLOR,
            topLeft = Offset(baseX + pixelSize * 2, baseY + pixelSize * 7),
            size = Size(pixelSize * 2, pixelSize * 4)
        )
        drawRect(
            color = SKIN_COLOR,
            topLeft = Offset(baseX + pixelSize * 12, baseY + pixelSize * 7),
            size = Size(pixelSize * 2, pixelSize * 4)
        )
        
        // Legs bent (jump pose)
        // Left leg
        drawRect(
            color = PANTS_COLOR,
            topLeft = Offset(baseX + pixelSize * 4, baseY + pixelSize * 14),
            size = Size(pixelSize * 3, pixelSize * 5)
        )
        
        // Right leg
        drawRect(
            color = PANTS_COLOR,
            topLeft = Offset(baseX + pixelSize * 9, baseY + pixelSize * 14),
            size = Size(pixelSize * 3, pixelSize * 5)
        )
        
        // Shoes (closer together in jump)
        // Left shoe
        drawRect(
            color = SHOE_COLOR,
            topLeft = Offset(baseX + pixelSize * 4, baseY + pixelSize * 19),
            size = Size(pixelSize * 3, pixelSize * 2)
        )
        
        // Right shoe
        drawRect(
            color = SHOE_COLOR,
            topLeft = Offset(baseX + pixelSize * 9, baseY + pixelSize * 19),
            size = Size(pixelSize * 3, pixelSize * 2)
        )
    }
    
    /**
     * Updates the animation frame based on elapsed time.
     */
    fun updateAnimation(deltaTime: Float): PlayerCharacter {
        if (isJumping) {
            return this // No animation cycling during jump
        }
        
        val newTimer = animationTimer + deltaTime
        return if (newTimer >= ANIMATION_FRAME_DURATION) {
            copy(
                animationFrame = (animationFrame + 1) % RUNNING_FRAME_COUNT,
                animationTimer = 0f
            )
        } else {
            copy(animationTimer = newTimer)
        }
    }
    
    /**
     * Updates the character's horizontal position based on running speed.
     * NOTE: This method is no longer used. The character now stays at a fixed position
     * while items scroll left to create the illusion of movement.
     * @deprecated Character position is now fixed at initialization
     */
    fun updateMovement(deltaTime: Float): PlayerCharacter {
        val newX = x + (RUNNING_SPEED * deltaTime)
        return copy(x = newX)
    }
    
    /**
     * Initiates a jump if the character is currently grounded.
     * Returns a new PlayerCharacter with jump state activated.
     */
    fun initiateJump(): PlayerCharacter {
        return if (!isJumping && screenHeight > 0f) {
            copy(
                isJumping = true,
                velocityY = JUMP_VELOCITY
            )
        } else {
            this // Already jumping or not initialized, no change
        }
    }
    
    /**
     * Updates jump physics including vertical velocity and position.
     * Applies gravity and handles landing detection.
     */
    fun updateJump(deltaTime: Float): PlayerCharacter {
        if (!isJumping) {
            return this // Not jumping, no physics to update
        }
        
        // Apply gravity to velocity
        val newVelocityY = velocityY + (GRAVITY * deltaTime)
        
        // Update vertical position
        val newY = y + (velocityY * deltaTime)
        
        // Calculate ground level
        val groundY = getGroundedY(screenHeight, height)
        
        // Check if character has landed
        return if (newY >= groundY) {
            // Character has landed
            copy(
                y = groundY,
                isJumping = false,
                velocityY = 0f
            )
        } else {
            // Still in the air
            copy(
                y = newY,
                velocityY = newVelocityY
            )
        }
    }
    
    /**
     * Checks if the character is currently grounded (not jumping).
     */
    fun isGrounded(): Boolean = !isJumping
}
