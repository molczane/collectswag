package org.example.collectswag.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

/**
 * Represents an obstacle (cardboard/paper box) in the game.
 * Obstacles are stationary on the ground and end the game when hit.
 * Implements Collidable for collision detection with the player.
 */
data class Obstacle(
    val x: Float,
    val y: Float,
    val width: Float = 48f,  // Box width (12 pixels * 4 pixel size)
    val height: Float = 48f, // Box height (12 pixels * 4 pixel size) - single box height
    val isActive: Boolean = true
) : Collidable {
    
    companion object {
        // Obstacle colors for pixel art rendering
        private val BOX_COLOR = Color(0xFFD2B48C) // Tan/cardboard color
        private val BOX_DARK = Color(0xFF8B7355) // Darker shade for depth
        private val OUTLINE_COLOR = Color(0xFF000000) // Black outline
        
        /**
         * Calculates the Y position for obstacles on the ground.
         * Obstacles sit directly on the road surface.
         */
        fun getGroundY(screenHeight: Float, obstacleHeight: Float): Float {
            val groundLevel = PlayerCharacter.getGroundLevel(screenHeight)
            return groundLevel - obstacleHeight
        }
    }
    
    /**
     * Returns a copy of this obstacle with updated x position (for scrolling).
     */
    fun updatePosition(deltaX: Float): Obstacle {
        return copy(x = x + deltaX)
    }
    
    /**
     * Checks if the obstacle is off-screen to the left.
     */
    fun isOffScreen(): Boolean {
        return x + width < 0
    }
    
    /**
     * Returns the hitbox for collision detection.
     * The hitbox matches the obstacle's position and dimensions.
     */
    override fun getHitbox(): Hitbox {
        return Hitbox(x, y, width, height)
    }
    
    /**
     * Draws the obstacle as a cardboard box in pixel art style.
     */
    fun draw(drawScope: DrawScope) {
        with(drawScope) {
            val pixelSize = 4f
            
            // Main box body
            drawRect(
                color = BOX_COLOR,
                topLeft = Offset(x, y),
                size = Size(width, height)
            )
            
            // Add some depth/shading on the right side
            drawRect(
                color = BOX_DARK,
                topLeft = Offset(x + width - pixelSize * 2, y),
                size = Size(pixelSize * 2, height)
            )
            
            // Add some depth/shading on the bottom
            drawRect(
                color = BOX_DARK,
                topLeft = Offset(x, y + height - pixelSize * 2),
                size = Size(width, pixelSize * 2)
            )
            
            // Draw tape lines (horizontal)
            drawRect(
                color = BOX_DARK,
                topLeft = Offset(x, y + height / 2 - pixelSize),
                size = Size(width, pixelSize)
            )
            
            // Outline
            drawRect(
                color = OUTLINE_COLOR,
                topLeft = Offset(x, y),
                size = Size(width, height),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2f)
            )
        }
    }
}
