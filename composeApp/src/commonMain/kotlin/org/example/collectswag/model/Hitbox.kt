package org.example.collectswag.model

import androidx.compose.ui.geometry.Rect

/**
 * Represents a rectangular hitbox for collision detection.
 * Uses Axis-Aligned Bounding Box (AABB) collision detection.
 */
data class Hitbox(
    val x: Float,
    val y: Float,
    val width: Float,
    val height: Float
) {
    /**
     * Returns the left edge of the hitbox.
     */
    val left: Float get() = x
    
    /**
     * Returns the right edge of the hitbox.
     */
    val right: Float get() = x + width
    
    /**
     * Returns the top edge of the hitbox.
     */
    val top: Float get() = y
    
    /**
     * Returns the bottom edge of the hitbox.
     */
    val bottom: Float get() = y + height
    
    /**
     * Converts this hitbox to a Compose Rect for rendering/debugging.
     */
    fun toRect(): Rect = Rect(left, top, right, bottom)
    
    /**
     * Checks if this hitbox intersects with another hitbox using AABB collision detection.
     * 
     * @param other The other hitbox to check collision with
     * @return true if the hitboxes overlap, false otherwise
     */
    fun intersects(other: Hitbox): Boolean {
        return this.left < other.right &&
               this.right > other.left &&
               this.top < other.bottom &&
               this.bottom > other.top
    }
    
    companion object {
        /**
         * Creates a hitbox from a position and size.
         */
        fun fromPositionAndSize(x: Float, y: Float, width: Float, height: Float): Hitbox {
            return Hitbox(x, y, width, height)
        }
    }
}

/**
 * Interface for objects that have a hitbox for collision detection.
 */
interface Collidable {
    /**
     * Returns the hitbox for this object.
     */
    fun getHitbox(): Hitbox
}

/**
 * Utility object for collision detection operations.
 */
object CollisionDetector {
    /**
     * Checks if two collidable objects are colliding.
     * 
     * @param a First collidable object
     * @param b Second collidable object
     * @return true if the objects are colliding, false otherwise
     */
    fun checkCollision(a: Collidable, b: Collidable): Boolean {
        return a.getHitbox().intersects(b.getHitbox())
    }
    
    /**
     * Checks if a collidable object collides with any item in a list.
     * Returns the first colliding item, or null if no collision.
     * 
     * @param target The target collidable object
     * @param items List of items to check against
     * @return The first colliding item, or null if no collision
     */
    fun <T : Collidable> findFirstCollision(target: Collidable, items: List<T>): T? {
        val targetHitbox = target.getHitbox()
        return items.firstOrNull { item ->
            targetHitbox.intersects(item.getHitbox())
        }
    }
    
    /**
     * Checks if a collidable object collides with any item in a list.
     * Returns all colliding items.
     * 
     * @param target The target collidable object
     * @param items List of items to check against
     * @return List of all colliding items
     */
    fun <T : Collidable> findAllCollisions(target: Collidable, items: List<T>): List<T> {
        val targetHitbox = target.getHitbox()
        return items.filter { item ->
            targetHitbox.intersects(item.getHitbox())
        }
    }
    
    /**
     * Optimized collision check using spatial partitioning.
     * For small numbers of objects (< 100), simple iteration is faster.
     * This method uses a simple optimization: early exit on first collision.
     * 
     * @param target The target collidable object
     * @param items List of items to check against
     * @return true if any collision is detected, false otherwise
     */
    fun hasAnyCollision(target: Collidable, items: List<Collidable>): Boolean {
        val targetHitbox = target.getHitbox()
        return items.any { item ->
            targetHitbox.intersects(item.getHitbox())
        }
    }
}
