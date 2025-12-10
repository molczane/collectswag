package org.example.collectswag.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Main Menu Screen with pixel art aesthetic.
 * Displays the game title, play button, and optional high score.
 */
@Composable
fun MainMenuScreen(
    highScore: Int = 0,
    onPlayClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF87CEEB)), // Sky blue background
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            // Game Title with pixel art styling
            Text(
                text = "SWAG COLLECTOR",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .background(Color(0xFF4169E1)) // Royal blue
                    .padding(16.dp)
            )
            
            // High Score Display (if greater than 0)
            if (highScore > 0) {
                Text(
                    text = "HIGH SCORE: $highScore",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFFD700), // Gold color
                    modifier = Modifier
                        .background(Color(0xFF2F4F4F)) // Dark slate gray
                        .padding(12.dp)
                )
            }
            
            // Play Button with pixel art styling
            Button(
                onClick = onPlayClick,
                modifier = Modifier
                    .size(width = 200.dp, height = 60.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF32CD32), // Lime green
                    contentColor = Color.White
                ),
                border = BorderStroke(2.dp, Color(0xFF228B22)) // Forest green
            ) {
                Text(
                    text = "PLAY",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
