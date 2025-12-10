package org.example.collectswag.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.IntSize
import collectswag.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource

/**
 * Represents the rarity level of a swag item.
 */
enum class ItemRarity {
    COMMON,
    RARE
}

/**
 * Sealed class representing different types of collectible swag items.
 * Each item has a point value, rarity, and visual properties.
 */
sealed class SwagItemType(
    val pointValue: Int,
    val rarity: ItemRarity,
    val displayName: String,
    val color: Color
) {
    // 1-point items (Common)
    object Sticker : SwagItemType(1, ItemRarity.COMMON, "Sticker", Color(0xFFFF6B6B))
    object Pin : SwagItemType(1, ItemRarity.COMMON, "Pin", Color(0xFF4ECDC4))
    object Pen : SwagItemType(1, ItemRarity.COMMON, "Pen", Color(0xFF45B7D1))
    
    // 2-point items (Common)
    object Sock : SwagItemType(2, ItemRarity.COMMON, "Sock", Color(0xFFFFA07A))
    object ToteBag : SwagItemType(2, ItemRarity.COMMON, "Tote Bag", Color(0xFF98D8C8))
    object Notebook : SwagItemType(2, ItemRarity.COMMON, "Notebook", Color(0xFFF7DC6F))
    object Bottle : SwagItemType(2, ItemRarity.COMMON, "Bottle", Color(0xFF85C1E2))
    
    // 5-point items (Rare)
    object Hoodie : SwagItemType(5, ItemRarity.RARE, "Hoodie", Color(0xFF9B59B6))
    object TShirt : SwagItemType(5, ItemRarity.RARE, "T-Shirt", Color(0xFFE74C3C))
    
    /**
     * Returns the drawable resource for this item type.
     * Returns null for items without XML drawables (Hoodie, TShirt).
     */
    fun getDrawableResource(): DrawableResource? = when (this) {
        Sticker -> Res.drawable.sticker
        Pin -> Res.drawable.pin
        Pen -> Res.drawable.pen
        Sock -> Res.drawable.sock
        ToteBag -> Res.drawable.tote_bag
        Notebook -> Res.drawable.notebook
        Bottle -> Res.drawable.bottle
        Hoodie, TShirt -> null // No XML drawables yet for rare items
    }
    
    companion object {
        /**
         * Returns all available swag item types.
         */
        fun getAllTypes(): List<SwagItemType> = listOf(
            Sticker, Pin, Pen,
            Sock, ToteBag, Notebook, Bottle,
            Hoodie, TShirt
        )
        
        /**
         * Returns common item types (1 and 2 point items).
         */
        fun getCommonTypes(): List<SwagItemType> = listOf(
            Sticker, Pin, Pen,
            Sock, ToteBag, Notebook, Bottle
        )
        
        /**
         * Returns rare item types (5 point items).
         */
        fun getRareTypes(): List<SwagItemType> = listOf(
            Hoodie, TShirt
        )
    }
}

/**
 * Represents an active swag item instance in the game.
 * Tracks position, lifecycle state, and visual properties.
 */
data class SwagItem(
    val type: SwagItemType,
    val x: Float,
    val y: Float,
    val width: Float = 32f,
    val height: Float = 32f,
    val isActive: Boolean = true
) {
    /**
     * Returns a copy of this item with updated x position (for scrolling).
     */
    fun updatePosition(deltaX: Float): SwagItem {
        return copy(x = x + deltaX)
    }
    
    /**
     * Returns a copy of this item marked as collected (inactive).
     */
    fun collect(): SwagItem {
        return copy(isActive = false)
    }
    
    /**
     * Checks if the item is off-screen to the left.
     */
    fun isOffScreen(): Boolean {
        return x + width < 0
    }
    
    /**
     * Draws the swag item using XML drawable resources for pixel art representation.
     * Falls back to colored rectangles if no painter is provided.
     */
    fun draw(drawScope: DrawScope, painter: Painter?) {
        with(drawScope) {
            if (painter != null) {
                // Draw using the XML vector drawable
                withTransform({
                    translate(left = x, top = y)
                }) {
                    with(painter) {
                        draw(Size(width, height))
                    }
                }
            } else {
                // Fallback: Draw simple colored rectangle for items without XML drawables
                drawRect(
                    color = type.color,
                    topLeft = Offset(x, y),
                    size = Size(width, height)
                )
                
                // Draw border for pixel art effect
                drawRect(
                    color = Color.Black,
                    topLeft = Offset(x, y),
                    size = Size(width, height),
                    style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2f)
                )
            }
        }
    }
    
    companion object {
        /**
         * Calculates the Y position for items at jump height.
         * Items should float at the peak of the character's jump.
         */
        fun getJumpHeightY(screenHeight: Float, itemHeight: Float): Float {
            val groundLevel = PlayerCharacter.getGroundLevel(screenHeight)
            val jumpHeight = PlayerCharacter.JUMP_VELOCITY * PlayerCharacter.JUMP_VELOCITY / (2 * PlayerCharacter.GRAVITY)
            return groundLevel - jumpHeight - itemHeight / 2
        }
    }
}
