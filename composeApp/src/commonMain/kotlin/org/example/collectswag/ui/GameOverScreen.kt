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
 * Game Over Screen with pixel art aesthetic.
 * Displays game over message, final score, and restart/menu options.
 */
@Composable
fun GameOverScreen(
    finalScore: Int,
    onRestartClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2F4F4F)), // Dark slate gray background
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Game Over Title
            Text(
                text = "GAME OVER",
                fontSize = 56.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFF4500), // Orange red
                modifier = Modifier
                    .border(4.dp, Color.Black)
                    .background(Color(0xFF8B0000)) // Dark red
                    .padding(16.dp)
            )
            
            // Final Score Display
            Text(
                text = "FINAL SCORE",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(top = 16.dp)
            )
            
            Text(
                text = "$finalScore",
                fontSize = 64.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFD700), // Gold color
                modifier = Modifier
                    .border(4.dp, Color.Black)
                    .background(Color(0xFF4169E1)) // Royal blue
                    .padding(horizontal = 32.dp, vertical = 16.dp)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Restart Button
            Button(
                onClick = onRestartClick,
                modifier = Modifier
                    .size(width = 240.dp, height = 60.dp)
                    .border(4.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF32CD32), // Lime green
                    contentColor = Color.White
                ),
                border = BorderStroke(2.dp, Color(0xFF228B22)) // Forest green
            ) {
                Text(
                    text = "PLAY AGAIN",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            
            // Return to Menu Button
            Button(
                onClick = onMenuClick,
                modifier = Modifier
                    .size(width = 240.dp, height = 60.dp)
                    .border(4.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4169E1), // Royal blue
                    contentColor = Color.White
                ),
                border = BorderStroke(2.dp, Color(0xFF1E3A8A)) // Dark blue
            ) {
                Text(
                    text = "MAIN MENU",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
