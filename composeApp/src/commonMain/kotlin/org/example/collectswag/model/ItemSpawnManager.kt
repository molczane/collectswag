package org.example.collectswag.model

import kotlin.random.Random

/**
 * Manages the spawning of collectible swag items during gameplay.
 * Handles spawn timing, rarity-based probability, and active items collection.
 */
class ItemSpawnManager(
    private val screenWidth: Float,
    private val screenHeight: Float
) {
    private val activeItems = mutableListOf<SwagItem>()
    private var timeSinceLastSpawn = 0f
    private var gameTime = 0f
    
    companion object {
        // Spawn timing constants
        private const val MIN_SPAWN_INTERVAL = 1.5f // Minimum seconds between spawns
        private const val MAX_SPAWN_INTERVAL = 3.0f // Maximum seconds between spawns
        
        // Rarity probability (0.0 to 1.0)
        private const val RARE_ITEM_PROBABILITY = 0.15f // 15% chance for rare items
        
        // Item spawn position
        private const val SPAWN_OFFSET_X = 50f // Spawn items this many pixels off-screen to the right
        
        // Scrolling speed (should match game speed)
        private const val SCROLL_SPEED = 200f // pixels per second
    }
    
    /**
     * Updates the spawn manager each frame.
     * Handles spawning new items and updating positions of active items.
     */
    fun update(deltaTime: Float) {
        gameTime += deltaTime
        timeSinceLastSpawn += deltaTime
        
        // Check if it's time to spawn a new item
        if (shouldSpawnItem()) {
            spawnItem()
            timeSinceLastSpawn = 0f
        }
        
        // Update positions of all active items (scroll left)
        updateItemPositions(deltaTime)
        
        // Remove items that have moved off-screen
        removeOffScreenItems()
    }
    
    /**
     * Determines if a new item should be spawned based on timing.
     */
    private fun shouldSpawnItem(): Boolean {
        val nextSpawnInterval = Random.nextFloat() * (MAX_SPAWN_INTERVAL - MIN_SPAWN_INTERVAL) + MIN_SPAWN_INTERVAL
        return timeSinceLastSpawn >= nextSpawnInterval
    }
    
    /**
     * Spawns a new item at the right edge of the screen.
     */
    private fun spawnItem() {
        val itemType = selectRandomItemType()
        val itemWidth = 32f
        val itemHeight = 32f
        
        // Position item off-screen to the right
        val spawnX = screenWidth + SPAWN_OFFSET_X
        
        // Position item at jump height
        val spawnY = SwagItem.getJumpHeightY(screenHeight, itemHeight)
        
        val newItem = SwagItem(
            type = itemType,
            x = spawnX,
            y = spawnY,
            width = itemWidth,
            height = itemHeight
        )
        
        activeItems.add(newItem)
    }
    
    /**
     * Selects a random item type based on rarity probability.
     */
    private fun selectRandomItemType(): SwagItemType {
        val isRare = Random.nextFloat() < RARE_ITEM_PROBABILITY
        
        return if (isRare) {
            // Select from rare items
            val rareTypes = SwagItemType.getRareTypes()
            rareTypes[Random.nextInt(rareTypes.size)]
        } else {
            // Select from common items
            val commonTypes = SwagItemType.getCommonTypes()
            commonTypes[Random.nextInt(commonTypes.size)]
        }
    }
    
    /**
     * Updates the positions of all active items (scrolls them left).
     */
    private fun updateItemPositions(deltaTime: Float) {
        val deltaX = -SCROLL_SPEED * deltaTime
        
        for (i in activeItems.indices) {
            activeItems[i] = activeItems[i].updatePosition(deltaX)
        }
    }
    
    /**
     * Removes items that have moved off-screen to the left.
     */
    private fun removeOffScreenItems() {
        activeItems.removeAll { it.isOffScreen() }
    }
    
    /**
     * Returns the list of currently active items.
     */
    fun getActiveItems(): List<SwagItem> {
        return activeItems.toList()
    }
    
    /**
     * Collects an item (marks it as inactive and removes it from active list).
     */
    fun collectItem(item: SwagItem) {
        activeItems.remove(item)
    }
    
    /**
     * Removes multiple items from the active list (for batch collection).
     */
    fun removeItems(items: List<SwagItem>) {
        activeItems.removeAll(items.toSet())
    }
    
    /**
     * Resets the spawn manager (clears all items and resets timers).
     */
    fun reset() {
        activeItems.clear()
        timeSinceLastSpawn = 0f
        gameTime = 0f
    }
    
    /**
     * Returns the current game time.
     */
    fun getGameTime(): Float {
        return gameTime
    }
    
    /**
     * Returns the number of active items currently on screen.
     */
    fun getActiveItemCount(): Int {
        return activeItems.size
    }
}
