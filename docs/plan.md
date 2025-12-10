# Implementation Plan

## Overview

This document outlines the detailed implementation plan for the Swag Collector game. Each plan item is linked to specific requirements from `docs/requirements.md` and assigned a priority level to guide development sequencing.

---

## Phase 1: Project Foundation & Architecture

### 1.1 Game State Management System
**Priority:** High  
**Requirements:** Req #18  
**Description:** Implement core game state management to handle Menu, Playing, and GameOver states with proper transitions and resource management.

**Tasks:**
- Define GameState enum/sealed class (Menu, Playing, GameOver)
- Create GameViewModel/StateManager to hold and manage current state
- Implement state transition logic with validation
- Set up state observation mechanism for UI updates
- Initialize game state to Menu on application start

---

### 1.2 Cross-Platform Architecture Setup
**Priority:** High  
**Requirements:** Req #21  
**Description:** Establish the proper KMP architecture with shared and platform-specific code organization.

**Tasks:**
- Review and organize commonMain source set structure
- Set up platform-specific source sets (androidMain, iosMain, jvmMain, jsMain/wasmJsMain)
- Define expect/actual declarations for platform-specific input handling
- Ensure Compose Multiplatform is properly configured
- Create base game architecture following existing project structure

---

## Phase 2: Core UI Screens

### 2.1 Main Menu Screen
**Priority:** High  
**Requirements:** Req #1, Req #20  
**Description:** Implement the main menu screen with title, play button, and optional high score display.

**Tasks:**
- Create MainMenuScreen composable
- Display "Swag Collector" title with appropriate styling
- Implement Play/Start button with click handler
- Add optional high score display area
- Implement navigation to Game Screen on button click
- Apply pixel art aesthetic to UI elements

---

### 2.2 Game Screen Layout
**Priority:** High  
**Requirements:** Req #2, Req #15  
**Description:** Create the main game screen with all visual elements including background, ground, and score display.

**Tasks:**
- Create GameScreen composable
- Implement blue sky background
- Add pixel art cloud sprites
- Render grey road/path at bottom of screen
- Position score counter in top corner
- Set up coordinate system and screen dimensions
- Ensure all elements use pixel art style

---

### 2.3 Game Over Screen
**Priority:** High  
**Requirements:** Req #17  
**Description:** Implement the game over screen with score display and navigation options.

**Tasks:**
- Create GameOverScreen composable
- Display "Game Over" message
- Show final score prominently
- Implement Restart/Play Again button
- Implement Return to Main Menu button
- Wire up button actions to state transitions
- Reset game state properly on restart

---

## Phase 3: Player Character Implementation

### 3.1 Character Sprite Assets
**Priority:** High  
**Requirements:** Req #3, Req #23  
**Description:** Create or integrate pixel art sprites for the player character.

**Tasks:**
- Design/obtain blonde pixel art character sprite
- Create running animation frames (minimum 2-4 frames showing leg movement)
- Create jump pose sprite
- Store sprites in appropriate resource directories
- Ensure consistent pixel art style across all frames

---

### 3.2 Character Rendering & Animation
**Priority:** High  
**Requirements:** Req #3, Req #22  
**Description:** Implement character rendering with proper animation state management.

**Tasks:**
- Create PlayerCharacter component/class
- Implement sprite rendering in Compose
- Create animation frame cycling logic for running
- Implement animation state switching (running/jumping)
- Start running animation immediately when game enters Playing state
- Ensure smooth frame transitions

---

### 3.3 Character Positioning System
**Priority:** High  
**Requirements:** Req #4  
**Description:** Implement precise character positioning aligned with the road surface.

**Tasks:**
- Define ground level constant/reference point
- Position character bottom edge at road surface top edge
- Implement Y-coordinate calculation for grounded state
- Ensure character doesn't float above ground
- Maintain consistent positioning during running animation

---

### 3.4 Automatic Running Behavior
**Priority:** High  
**Requirements:** Req #5  
**Description:** Implement automatic left-to-right character movement.

**Tasks:**
- Implement automatic horizontal movement logic
- Maintain continuous running animation during movement
- Ensure movement is independent of user input
- Integrate movement with game speed system

---

## Phase 4: Jump Mechanics

### 4.1 Jump Physics
**Priority:** High  
**Requirements:** Req #6, Req #7, Req #8  
**Description:** Implement jump physics including gravity, velocity, and arc.

**Tasks:**
- Define jump velocity and gravity constants
- Implement vertical velocity calculation
- Create jump arc physics (parabolic motion)
- Implement ground collision detection for landing
- Prevent double-jumping (only jump when grounded)
- Switch to jump animation when airborne
- Return to running animation on landing

---

### 4.2 Touch Input Handler (Mobile)
**Priority:** High  
**Requirements:** Req #6  
**Description:** Implement touch input detection for Android and iOS platforms.

**Tasks:**
- Create platform-specific touch input handler for Android
- Create platform-specific touch input handler for iOS
- Detect tap anywhere on screen
- Trigger jump action when character is grounded
- Ignore input when character is airborne

---

### 4.3 Keyboard Input Handler (Desktop/Web)
**Priority:** High  
**Requirements:** Req #7  
**Description:** Implement keyboard input detection for desktop and web platforms.

**Tasks:**
- Create keyboard input handler for Desktop (JVM)
- Create keyboard input handler for Web (JS/WasmJS)
- Detect Space bar key press
- Detect Up arrow key press
- Trigger jump action when character is grounded
- Ignore input when character is airborne

---

### 4.4 Mouse Input Handler (Web)
**Priority:** Medium  
**Requirements:** Req #8  
**Description:** Implement mouse click detection as alternative input for web platform.

**Tasks:**
- Create mouse click handler for Web platform
- Detect mouse click events on game area
- Trigger jump action when character is grounded
- Ignore input when character is airborne

---

## Phase 5: Collectible Items System

### 5.1 Swag Item Sprites
**Priority:** High  
**Requirements:** Req #9, Req #10, Req #23  
**Description:** Create or integrate pixel art sprites for all collectible items.

**Tasks:**
- Create/obtain sprites for regular items: Stickers, Pins, Pens, Socks, Tote Bags, Notebooks, Bottles
- Create/obtain sprites for special items: Hoodie, T-Shirt
- Ensure all sprites follow pixel art style
- Store sprites in appropriate resource directories
- Define sprite dimensions and visual properties

---

### 5.2 Item Data Model
**Priority:** High  
**Requirements:** Req #9, Req #10  
**Description:** Define data structures for collectible items with types and point values.

**Tasks:**
- Create SwagItem sealed class/enum with all item types
- Define point values: 1-point items (Sticker, Pin, Pen), 2-point items (Sock, Tote Bag, Notebook, Bottle), 5-point items (Hoodie, T-Shirt)
- Define rarity levels (Common, Rare)
- Create item position and state tracking
- Implement item lifecycle management

---

### 5.3 Item Spawning System
**Priority:** High  
**Requirements:** Req #9, Req #10  
**Description:** Implement item spawning logic with proper positioning and rarity.

**Tasks:**
- Create item spawn manager
- Implement spawn timing algorithm
- Position items floating at jump height
- Implement rarity-based spawn probability (rare items spawn less frequently)
- Ensure items spawn off-screen and scroll into view
- Manage active items collection

---

### 5.4 Item Rendering
**Priority:** High  
**Requirements:** Req #9, Req #10  
**Description:** Render collectible items on screen with proper positioning.

**Tasks:**
- Implement item rendering in game loop
- Position items at correct Y-coordinate (jump height)
- Scroll items with game speed
- Remove items when they move off-screen
- Ensure pixel art rendering style

---

## Phase 6: Collision Detection & Scoring

### 6.1 Hitbox System
**Priority:** High  
**Requirements:** Req #11, Req #14  
**Description:** Implement hitbox collision detection for player, items, and obstacles.

**Tasks:**
- Define hitbox dimensions for player character
- Define hitbox dimensions for each swag item type
- Define hitbox dimensions for obstacles
- Implement rectangle-based collision detection algorithm
- Optimize collision checks for performance

---

### 6.2 Swag Collection Logic
**Priority:** High  
**Requirements:** Req #11  
**Description:** Implement collision detection and scoring for collectible items.

**Tasks:**
- Detect collision between player and swag items
- Award correct point value based on item type (1, 2, or 5 points)
- Remove collected item from active items list
- Update score immediately upon collection
- Provide visual/audio feedback on collection
- Verify each item type awards correct points

---

### 6.3 Score Management
**Priority:** High  
**Requirements:** Req #15  
**Description:** Implement score tracking and display system.

**Tasks:**
- Initialize score to 0 at game start
- Implement score increment function
- Update score display in real-time
- Format score for clear readability
- Maintain score throughout Playing state
- Pass final score to Game Over screen

---

## Phase 7: Obstacles System

### 7.1 Obstacle Sprites
**Priority:** High  
**Requirements:** Req #12, Req #23  
**Description:** Create or integrate pixel art sprites for obstacles.

**Tasks:**
- Create/obtain cardboard/paper box sprite in pixel art style
- Ensure single box height only
- Store sprite in appropriate resource directory
- Define obstacle dimensions

---

### 7.2 Obstacle Spawning & Positioning
**Priority:** High  
**Requirements:** Req #12, Req #13  
**Description:** Implement obstacle spawning and positioning system.

**Tasks:**
- Create obstacle spawn manager
- Implement spawn timing algorithm with safe spacing
- Position obstacles directly on road surface (same ground plane as character)
- Keep obstacles stationary relative to ground
- Scroll obstacles with game environment
- Manage active obstacles collection

---

### 7.3 Obstacle Collision & Game Over
**Priority:** High  
**Requirements:** Req #14  
**Description:** Implement obstacle collision detection and game over logic.

**Tasks:**
- Detect collision between player and obstacles
- Trigger game over immediately on collision
- Stop all gameplay (movement, spawning, animations)
- Transition to GameOver state
- Pass final score to Game Over screen

---

## Phase 8: Game Progression & Polish

### 8.1 Difficulty Progression System
**Priority:** Medium  
**Requirements:** Req #16  
**Description:** Implement gradual speed increase to make game progressively harder.

**Tasks:**
- Define initial game speed
- Implement time-based speed increase algorithm
- Apply speed to scrolling, spawning, and movement
- Ensure smooth gameplay at all speeds
- Balance difficulty curve for engaging gameplay

---

### 8.2 Parallax Scrolling Background
**Priority:** Low  
**Requirements:** Req #19  
**Description:** Implement parallax scrolling for clouds to add depth.

**Tasks:**
- Implement background layer scrolling
- Set cloud scroll speed different from ground speed
- Create seamless cloud looping
- Ensure smooth parallax effect
- Optimize rendering performance

---

### 8.3 High Score Persistence
**Priority:** Low  
**Requirements:** Req #20  
**Description:** Implement local storage for high score tracking.

**Tasks:**
- Create platform-specific storage implementations (SharedPreferences, UserDefaults, LocalStorage)
- Save high score when game ends if score exceeds previous high
- Load high score on application start
- Display high score on Main Menu
- Handle first-time launch (no saved score)

---

## Phase 9: Testing & Quality Assurance

### 9.1 Unit Testing
**Priority:** Medium  
**Requirements:** All  
**Description:** Create unit tests for core game logic.

**Tasks:**
- Test game state transitions
- Test collision detection algorithms
- Test scoring logic for all item types
- Test jump physics calculations
- Test spawn timing and positioning logic

---

### 9.2 Platform-Specific Testing
**Priority:** High  
**Requirements:** Req #6, Req #7, Req #8, Req #21  
**Description:** Test game on all target platforms.

**Tasks:**
- Test on Android device/emulator (touch input)
- Test on iOS device/simulator (touch input)
- Test on Desktop (keyboard input)
- Test on Web (keyboard and mouse input)
- Verify consistent behavior across platforms
- Test performance on each platform

---

### 9.3 Gameplay Testing
**Priority:** High  
**Requirements:** All  
**Description:** Comprehensive gameplay testing to ensure quality experience.

**Tasks:**
- Test complete game flow (Menu → Playing → GameOver → Menu)
- Verify all collectibles award correct points
- Verify obstacle collision ends game properly
- Test difficulty progression feels balanced
- Verify animations play correctly
- Test edge cases (rapid input, boundary conditions)
- Verify character positioning on ground
- Test score display updates correctly

---

## Phase 10: Documentation & Deployment

### 10.1 Code Documentation
**Priority:** Low  
**Requirements:** Req #21  
**Description:** Document code for maintainability.

**Tasks:**
- Add KDoc comments to public APIs
- Document game architecture and design decisions
- Create README with build instructions
- Document platform-specific implementations

---

### 10.2 Build & Deployment Setup
**Priority:** Medium  
**Requirements:** Req #21  
**Description:** Prepare build configurations for all platforms.

**Tasks:**
- Configure Android build and signing
- Configure iOS build and provisioning
- Configure Desktop distribution
- Configure Web deployment
- Test release builds on all platforms

---

## Phase 11: Visual Fixes & Bug Corrections

### 11.1 Swag Item XML Drawable Implementation
**Priority:** High  
**Requirements:** Req #24  
**Description:** Replace programmatic swag item rendering with proper XML drawable resources for better visual quality and recognizability.

**Tasks:**
- Create XML drawable resources for each swag item type (Stickers, Pins, Pens, Socks, Tote Bags, Notebooks, Bottles)
- Store XML drawables in composeApp/src/commonMain/composeResources/drawable directory
- Update SwagItem.kt to load and render items from XML drawable resources instead of using DrawScope patterns
- Ensure all items maintain pixel art aesthetic and are visually recognizable
- Test rendering of all item types to verify proper appearance
- Remove old draw pattern methods (drawStickerPattern, drawPinPattern, etc.)

---

### 11.2 Character Fixed Position Implementation
**Priority:** High  
**Requirements:** Req #25  
**Description:** Fix character positioning to keep it at a fixed x-coordinate while scrolling the world, preventing the character from running off-screen.

**Tasks:**
- Remove updateMovement call from GameViewModel game loop
- Set character x position to fixed value (15-20% from left edge) during initialization
- Ensure character x position never changes during gameplay
- Verify items scroll left correctly while character stays in place
- Update PlayerCharacter.kt to remove or deprecate updateMovement method
- Test that character remains visible and stationary on x-axis throughout gameplay
- Verify background scrolling creates proper illusion of movement

---

### 11.3 UI Border Removal
**Priority:** High  
**Requirements:** Req #26  
**Description:** Remove thick black borders from UI elements for cleaner, more polished appearance.

**Tasks:**
- Remove .border(4.dp, Color.Black) from game title in MainMenuScreen.kt
- Remove .border(4.dp, Color.Black) from Play button in MainMenuScreen.kt
- Remove .border(2.dp, Color.Black) from high score display in MainMenuScreen.kt
- Remove .border(3.dp, Color.Black) from score counter in GameScreen.kt
- Optionally add subtle borders (1.dp or less) if needed for visual separation
- Test all UI elements to ensure they remain readable without thick borders
- Verify pixel art aesthetic is maintained with cleaner styling

---

## Priority Summary

**High Priority (Must Have):**
- All Phase 1-7 items (core gameplay functionality)
- Platform-specific testing (9.2)
- Gameplay testing (9.3)
- All Phase 11 items (visual fixes and bug corrections)

**Medium Priority (Should Have):**
- Difficulty progression (8.1)
- Mouse input for web (4.4)
- Unit testing (9.1)
- Build & deployment setup (10.2)

**Low Priority (Nice to Have):**
- Parallax scrolling (8.2)
- High score persistence (8.3)
- Code documentation (10.1)

---

## Dependencies & Sequencing

1. **Foundation First:** Complete Phase 1 before starting other phases
2. **UI Screens:** Phase 2 can be developed in parallel with Phase 3-4
3. **Character Before Mechanics:** Complete Phase 3 before Phase 4
4. **Core Gameplay:** Phases 3-7 form the core gameplay loop and should be prioritized
5. **Polish Last:** Phase 8 items can be added after core gameplay is functional
6. **Testing Throughout:** Testing (Phase 9) should be integrated throughout development, not just at the end
