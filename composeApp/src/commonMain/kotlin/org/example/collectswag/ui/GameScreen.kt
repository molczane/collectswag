package org.example.collectswag.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.collectswag.model.PlayerCharacter

/**
 * Game Screen with pixel art aesthetic.
 * Displays blue sky background, clouds, grey road, score counter, and player character.
 */
@Composable
fun GameScreen(
    score: Int,
    playerCharacter: PlayerCharacter,
    onScreenSizeAvailable: (Float, Float) -> Unit = { _, _ -> },
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF87CEEB)) // Sky blue background
    ) {
        // Cloud sprites using Canvas
        Canvas(modifier = Modifier.fillMaxSize()) {
            // Initialize character position when screen size is available
            if (playerCharacter.screenHeight == 0f) {
                onScreenSizeAvailable(size.width, size.height)
            }
            val cloudColor = Color.White
            val cloudOutline = Color(0xFFE0E0E0)
            
            // Cloud 1 - top left area
            drawPixelArtCloud(
                x = size.width * 0.15f,
                y = size.height * 0.1f,
                cloudColor = cloudColor,
                outlineColor = cloudOutline
            )
            
            // Cloud 2 - top right area
            drawPixelArtCloud(
                x = size.width * 0.65f,
                y = size.height * 0.15f,
                cloudColor = cloudColor,
                outlineColor = cloudOutline
            )
            
            // Cloud 3 - middle left
            drawPixelArtCloud(
                x = size.width * 0.25f,
                y = size.height * 0.35f,
                cloudColor = cloudColor,
                outlineColor = cloudOutline
            )
            
            // Grey road at bottom (20% of screen height)
            val roadHeight = size.height * 0.2f
            val roadY = size.height - roadHeight
            
            // Road surface
            drawRect(
                color = Color(0xFF808080), // Grey
                topLeft = Offset(0f, roadY),
                size = Size(size.width, roadHeight)
            )
            
            // Road center line (dashed)
            val dashWidth = 40f
            val dashGap = 30f
            val lineY = roadY + roadHeight / 2
            var x = 0f
            while (x < size.width) {
                drawRect(
                    color = Color.White,
                    topLeft = Offset(x, lineY - 2f),
                    size = Size(dashWidth, 4f)
                )
                x += dashWidth + dashGap
            }
            
            // Draw player character
            playerCharacter.draw(this)
        }
        
        // Score counter in top-right corner
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .border(3.dp, Color.Black)
                .background(Color(0xFF2F4F4F)) // Dark slate gray
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = "SCORE: $score",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFD700) // Gold color
            )
        }
    }
}

/**
 * Helper function to draw a simple pixel art cloud.
 */
private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawPixelArtCloud(
    x: Float,
    y: Float,
    cloudColor: Color,
    outlineColor: Color
) {
    val pixelSize = 12f
    
    // Simple cloud shape using rectangles (pixel blocks)
    // Bottom row
    drawRect(
        color = cloudColor,
        topLeft = Offset(x + pixelSize, y + pixelSize * 2),
        size = Size(pixelSize * 5, pixelSize)
    )
    // Middle row
    drawRect(
        color = cloudColor,
        topLeft = Offset(x, y + pixelSize),
        size = Size(pixelSize * 7, pixelSize)
    )
    // Top row
    drawRect(
        color = cloudColor,
        topLeft = Offset(x + pixelSize, y),
        size = Size(pixelSize * 5, pixelSize)
    )
    
    // Outline effect (darker pixels on edges)
    drawRect(
        color = outlineColor,
        topLeft = Offset(x + pixelSize, y + pixelSize * 3),
        size = Size(pixelSize * 5, pixelSize * 0.3f)
    )
}
