# Technical Task List

This document contains a detailed enumerated task list for implementing the Swag Collector game. Each task is linked to the corresponding plan item in `docs/plan.md` and requirement(s) in `docs/requirements.md`.

---

## Phase 1: Project Foundation & Architecture

### Game State Management System
*Plan: 1.1 | Requirements: #18*

- [x] 1.1.1 Define GameState enum/sealed class with Menu, Playing, and GameOver states
- [x] 1.1.2 Create GameViewModel or StateManager class to hold and manage current game state
- [x] 1.1.3 Implement state transition logic with validation rules
- [x] 1.1.4 Set up state observation mechanism for UI updates (StateFlow/MutableState)
- [x] 1.1.5 Initialize game state to Menu on application start

### Cross-Platform Architecture Setup
*Plan: 1.2 | Requirements: #21*

- [x] 1.2.1 Review and organize commonMain source set structure
- [x] 1.2.2 Set up platform-specific source sets (androidMain, iosMain, jvmMain, jsMain/wasmJsMain)
- [x] 1.2.3 Define expect/actual declarations for platform-specific input handling
- [x] 1.2.4 Verify Compose Multiplatform configuration in build.gradle.kts
- [x] 1.2.5 Create base game architecture following existing composeApp module structure

---

## Phase 2: Core UI Screens

### Main Menu Screen
*Plan: 2.1 | Requirements: #1, #20*

- [x] 2.1.1 Create MainMenuScreen composable function
- [x] 2.1.2 Display "Swag Collector" title with pixel art styling
- [x] 2.1.3 Implement Play/Start button with click handler
- [x] 2.1.4 Add optional high score display area
- [x] 2.1.5 Implement navigation to Game Screen on button click
- [x] 2.1.6 Apply pixel art aesthetic to all UI elements

### Game Screen Layout
*Plan: 2.2 | Requirements: #2, #15*

- [x] 2.2.1 Create GameScreen composable function
- [x] 2.2.2 Implement blue sky background rendering
- [x] 2.2.3 Create and add pixel art cloud sprites
- [x] 2.2.4 Render grey road/path at bottom of screen
- [x] 2.2.5 Position score counter in top corner of screen
- [x] 2.2.6 Set up coordinate system and define screen dimensions
- [x] 2.2.7 Ensure all visual elements use consistent pixel art style

### Game Over Screen
*Plan: 2.3 | Requirements: #17*

- [x] 2.3.1 Create GameOverScreen composable function
- [x] 2.3.2 Display "Game Over" message prominently
- [x] 2.3.3 Show final score with clear formatting
- [x] 2.3.4 Implement Restart/Play Again button with click handler
- [x] 2.3.5 Implement Return to Main Menu button with click handler
- [x] 2.3.6 Wire up button actions to trigger state transitions
- [x] 2.3.7 Ensure game state resets properly on restart (score to 0, speed reset)

---

## Phase 3: Player Character Implementation

### Character Sprite Assets
*Plan: 3.1 | Requirements: #3, #23*

- [x] 3.1.1 Design or obtain blonde pixel art character sprite
- [x] 3.1.2 Create running animation frames (minimum 2-4 frames with visible leg movement)
- [x] 3.1.3 Create jump pose sprite
- [x] 3.1.4 Store sprites in composeApp/src/commonMain/composeResources/drawable
- [x] 3.1.5 Verify consistent pixel art style across all character frames

### Character Rendering & Animation
*Plan: 3.2 | Requirements: #3, #22*

- [x] 3.2.1 Create PlayerCharacter data class or component
- [x] 3.2.2 Implement sprite rendering using Compose Canvas or Image
- [x] 3.2.3 Create animation frame cycling logic for running animation
- [x] 3.2.4 Implement animation state switching between running and jumping
- [x] 3.2.5 Start running animation immediately when game enters Playing state
- [x] 3.2.6 Ensure smooth frame transitions with appropriate timing

### Character Positioning System
*Plan: 3.3 | Requirements: #4*

- [x] 3.3.1 Define ground level constant/reference point in game coordinates
- [x] 3.3.2 Position character bottom edge aligned with road surface top edge
- [x] 3.3.3 Implement Y-coordinate calculation for grounded state
- [x] 3.3.4 Verify character doesn't float above ground during rendering
- [x] 3.3.5 Maintain consistent positioning throughout running animation cycle

### Automatic Running Behavior
*Plan: 3.4 | Requirements: #5*

- [x] 3.4.1 Implement automatic horizontal movement logic in game loop
- [x] 3.4.2 Maintain continuous running animation during movement
- [x] 3.4.3 Ensure movement is independent of user input
- [x] 3.4.4 Integrate movement speed with game speed system

---

## Phase 4: Jump Mechanics

### Jump Physics
*Plan: 4.1 | Requirements: #6, #7, #8*

- [x] 4.1.1 Define jump velocity constant (initial upward velocity)
- [x] 4.1.2 Define gravity constant (downward acceleration)
- [x] 4.1.3 Implement vertical velocity calculation per frame
- [x] 4.1.4 Create jump arc physics using parabolic motion
- [x] 4.1.5 Implement ground collision detection for landing
- [x] 4.1.6 Prevent double-jumping (only allow jump when character is grounded)
- [x] 4.1.7 Switch to jump animation when character becomes airborne
- [x] 4.1.8 Return to running animation immediately upon landing

### Touch Input Handler (Mobile)
*Plan: 4.2 | Requirements: #6*

- [x] 4.2.1 Create expect declaration for touch input handler in commonMain
- [x] 4.2.2 Implement actual touch input handler for Android in androidMain
- [x] 4.2.3 Implement actual touch input handler for iOS in iosMain
- [x] 4.2.4 Detect tap anywhere on screen during gameplay
- [x] 4.2.5 Trigger jump action only when character is grounded
- [x] 4.2.6 Ignore touch input when character is airborne

### Keyboard Input Handler (Desktop/Web)
*Plan: 4.3 | Requirements: #7*

- [x] 4.3.1 Create expect declaration for keyboard input handler in commonMain
- [x] 4.3.2 Implement actual keyboard input handler for Desktop (JVM) in jvmMain
- [x] 4.3.3 Implement actual keyboard input handler for Web in jsMain/wasmJsMain
- [x] 4.3.4 Detect Space bar key press events
- [x] 4.3.5 Detect Up arrow key press events
- [x] 4.3.6 Trigger jump action only when character is grounded
- [x] 4.3.7 Ignore keyboard input when character is airborne

### Mouse Input Handler (Web)
*Plan: 4.4 | Requirements: #8*

- [x] 4.4.1 Create mouse click handler for Web platform in jsMain/wasmJsMain
- [x] 4.4.2 Detect mouse click events on game area
- [x] 4.4.3 Trigger jump action only when character is grounded
- [x] 4.4.4 Ignore mouse input when character is airborne

---

## Phase 5: Collectible Items System

### Swag Item Sprites
*Plan: 5.1 | Requirements: #9, #10, #23*

- [x] 5.1.1 Create/obtain pixel art sprites for 1-point items (Stickers, Pins, Pens)
- [x] 5.1.2 Create/obtain pixel art sprites for 2-point items (Socks, Tote Bags, Notebooks, Bottles)
- [x] 5.1.3 Create/obtain pixel art sprites for 5-point items (Hoodie, T-Shirt)
- [x] 5.1.4 Ensure all sprites follow consistent pixel art style
- [x] 5.1.5 Store sprites in composeApp/src/commonMain/composeResources/drawable
- [x] 5.1.6 Define sprite dimensions and visual properties for each item type

### Item Data Model
*Plan: 5.2 | Requirements: #9, #10*

- [x] 5.2.1 Create SwagItem sealed class/enum with all item types
- [x] 5.2.2 Define point values: Sticker=1, Pin=1, Pen=1
- [x] 5.2.3 Define point values: Sock=2, ToteBag=2, Notebook=2, Bottle=2
- [x] 5.2.4 Define point values: Hoodie=5, TShirt=5
- [x] 5.2.5 Define rarity levels (Common for regular items, Rare for special items)
- [x] 5.2.6 Create item position tracking (x, y coordinates)
- [x] 5.2.7 Implement item lifecycle management (active, collected, off-screen)

### Item Spawning System
*Plan: 5.3 | Requirements: #9, #10*

- [x] 5.3.1 Create ItemSpawnManager class
- [x] 5.3.2 Implement spawn timing algorithm based on game time
- [x] 5.3.3 Position items floating at jump height (Y-coordinate)
- [x] 5.3.4 Implement rarity-based spawn probability (rare items spawn less frequently)
- [x] 5.3.5 Ensure items spawn off-screen to the right and scroll into view
- [x] 5.3.6 Manage collection of active items on screen

### Item Rendering
*Plan: 5.4 | Requirements: #9, #10*

- [x] 5.4.1 Implement item rendering in game loop/composable
- [x] 5.4.2 Position items at correct Y-coordinate (jump height)
- [x] 5.4.3 Scroll items horizontally with game speed
- [x] 5.4.4 Remove items from active collection when they move off-screen (left side)
- [x] 5.4.5 Ensure pixel art rendering style for all items

---

## Phase 6: Collision Detection & Scoring

### Hitbox System
*Plan: 6.1 | Requirements: #11, #14*

- [ ] 6.1.1 Define hitbox dimensions for player character (width, height)
- [ ] 6.1.2 Define hitbox dimensions for each swag item type
- [ ] 6.1.3 Define hitbox dimensions for obstacles
- [ ] 6.1.4 Implement rectangle-based collision detection algorithm (AABB)
- [ ] 6.1.5 Optimize collision checks for performance (spatial partitioning if needed)

### Swag Collection Logic
*Plan: 6.2 | Requirements: #11*

- [ ] 6.2.1 Detect collision between player hitbox and swag item hitboxes
- [ ] 6.2.2 Award correct point value based on item type (1, 2, or 5 points)
- [ ] 6.2.3 Remove collected item from active items list immediately
- [ ] 6.2.4 Update score display immediately upon collection
- [ ] 6.2.5 Provide visual feedback on collection (item disappears)
- [ ] 6.2.6 Optionally provide audio feedback on collection
- [ ] 6.2.7 Verify each item type awards correct points through testing

### Score Management
*Plan: 6.3 | Requirements: #15*

- [ ] 6.3.1 Initialize score to 0 when game starts
- [ ] 6.3.2 Implement score increment function
- [ ] 6.3.3 Update score display in real-time when score changes
- [ ] 6.3.4 Format score for clear readability (e.g., "Score: 42")
- [ ] 6.3.5 Maintain score state throughout Playing state
- [ ] 6.3.6 Pass final score to Game Over screen when game ends

---

## Phase 7: Obstacles System

### Obstacle Sprites
*Plan: 7.1 | Requirements: #12, #23*

- [ ] 7.1.1 Create/obtain cardboard/paper box sprite in pixel art style
- [ ] 7.1.2 Ensure obstacle is single box height only (no stacked boxes)
- [ ] 7.1.3 Store sprite in composeApp/src/commonMain/composeResources/drawable
- [ ] 7.1.4 Define obstacle dimensions (width, height)

### Obstacle Spawning & Positioning
*Plan: 7.2 | Requirements: #12, #13*

- [ ] 7.2.1 Create ObstacleSpawnManager class
- [ ] 7.2.2 Implement spawn timing algorithm with safe spacing between obstacles
- [ ] 7.2.3 Position obstacles directly on road surface (same ground plane as character)
- [ ] 7.2.4 Keep obstacles stationary relative to ground (no vertical/horizontal movement)
- [ ] 7.2.5 Scroll obstacles with game environment (move with scrolling)
- [ ] 7.2.6 Manage collection of active obstacles on screen
- [ ] 7.2.7 Remove obstacles when they move off-screen (left side)

### Obstacle Collision & Game Over
*Plan: 7.3 | Requirements: #14*

- [ ] 7.3.1 Detect collision between player hitbox and obstacle hitboxes
- [ ] 7.3.2 Trigger game over immediately when collision is detected
- [ ] 7.3.3 Stop all gameplay (movement, spawning, animations)
- [ ] 7.3.4 Transition to GameOver state
- [ ] 7.3.5 Pass final score to Game Over screen

---

## Phase 8: Game Progression & Polish

### Difficulty Progression System
*Plan: 8.1 | Requirements: #16*

- [ ] 8.1.1 Define initial game speed constant
- [ ] 8.1.2 Implement time-based speed increase algorithm (gradual acceleration)
- [ ] 8.1.3 Apply speed to scrolling (background, ground, items, obstacles)
- [ ] 8.1.4 Apply speed to spawning frequency
- [ ] 8.1.5 Ensure smooth gameplay at all speed levels
- [ ] 8.1.6 Balance difficulty curve for engaging gameplay experience

### Parallax Scrolling Background
*Plan: 8.2 | Requirements: #19*

- [ ] 8.2.1 Implement background layer scrolling system
- [ ] 8.2.2 Set cloud scroll speed different from ground speed (slower)
- [ ] 8.2.3 Create seamless cloud looping (wrap around when off-screen)
- [ ] 8.2.4 Ensure smooth parallax effect
- [ ] 8.2.5 Optimize rendering performance for multiple layers

### High Score Persistence
*Plan: 8.3 | Requirements: #20*

- [ ] 8.3.1 Create expect declaration for storage interface in commonMain
- [ ] 8.3.2 Implement actual storage for Android using SharedPreferences
- [ ] 8.3.3 Implement actual storage for iOS using UserDefaults
- [ ] 8.3.4 Implement actual storage for Desktop using file-based storage
- [ ] 8.3.5 Implement actual storage for Web using LocalStorage
- [ ] 8.3.6 Save high score when game ends if final score exceeds previous high score
- [ ] 8.3.7 Load high score on application start
- [ ] 8.3.8 Display high score on Main Menu screen
- [ ] 8.3.9 Handle first-time launch gracefully (no saved score)

---

## Phase 9: Testing & Quality Assurance

### Unit Testing
*Plan: 9.1 | Requirements: All*

- [ ] 9.1.1 Write unit tests for game state transitions (Menu → Playing → GameOver)
- [ ] 9.1.2 Write unit tests for collision detection algorithms
- [ ] 9.1.3 Write unit tests for scoring logic (verify each item type awards correct points)
- [ ] 9.1.4 Write unit tests for jump physics calculations
- [ ] 9.1.5 Write unit tests for spawn timing and positioning logic
- [ ] 9.1.6 Ensure all unit tests pass

### Platform-Specific Testing
*Plan: 9.2 | Requirements: #6, #7, #8, #21*

- [ ] 9.2.1 Test on Android device/emulator with touch input
- [ ] 9.2.2 Test on iOS device/simulator with touch input
- [ ] 9.2.3 Test on Desktop with keyboard input (Space and Up arrow)
- [ ] 9.2.4 Test on Web with keyboard input (Space and Up arrow)
- [ ] 9.2.5 Test on Web with mouse click input
- [ ] 9.2.6 Verify consistent behavior across all platforms
- [ ] 9.2.7 Test performance on each platform (frame rate, responsiveness)

### Gameplay Testing
*Plan: 9.3 | Requirements: All*

- [ ] 9.3.1 Test complete game flow: Main Menu → Game → Game Over → Main Menu
- [ ] 9.3.2 Test complete game flow: Main Menu → Game → Game Over → Restart
- [ ] 9.3.3 Verify all collectible items award correct points (1, 2, or 5)
- [ ] 9.3.4 Verify obstacle collision ends game properly
- [ ] 9.3.5 Test difficulty progression feels balanced and engaging
- [ ] 9.3.6 Verify running animation plays correctly and starts immediately
- [ ] 9.3.7 Verify jump animation plays correctly
- [ ] 9.3.8 Test edge cases: rapid input, boundary conditions, simultaneous collisions
- [ ] 9.3.9 Verify character positioning on ground (not floating)
- [ ] 9.3.10 Verify obstacle positioning on ground (same plane as character)
- [ ] 9.3.11 Test score display updates correctly and immediately
- [ ] 9.3.12 Test that items spawn at correct height (jump height)
- [ ] 9.3.13 Test that rare items spawn less frequently than common items

---

## Phase 10: Documentation & Deployment

### Code Documentation
*Plan: 10.1 | Requirements: #21*

- [ ] 10.1.1 Add KDoc comments to all public APIs and classes
- [ ] 10.1.2 Document game architecture and design decisions
- [ ] 10.1.3 Create or update README with project overview
- [ ] 10.1.4 Add build instructions for all platforms to README
- [ ] 10.1.5 Document platform-specific implementations and expect/actual patterns

### Build & Deployment Setup
*Plan: 10.2 | Requirements: #21*

- [ ] 10.2.1 Configure Android build with proper signing configuration
- [ ] 10.2.2 Configure iOS build with provisioning profiles
- [ ] 10.2.3 Configure Desktop distribution (JAR or native package)
- [ ] 10.2.4 Configure Web deployment (build and hosting setup)
- [ ] 10.2.5 Test release builds on all platforms
- [ ] 10.2.6 Verify release builds run correctly on target devices/browsers

---

## Task Summary

**Total Tasks:** 186

**By Phase:**
- Phase 1 (Foundation): 10 tasks
- Phase 2 (UI Screens): 20 tasks
- Phase 3 (Player Character): 18 tasks
- Phase 4 (Jump Mechanics): 22 tasks
- Phase 5 (Collectible Items): 23 tasks
- Phase 6 (Collision & Scoring): 17 tasks
- Phase 7 (Obstacles): 15 tasks
- Phase 8 (Progression & Polish): 17 tasks
- Phase 9 (Testing & QA): 25 tasks
- Phase 10 (Documentation & Deployment): 11 tasks

**By Priority (from plan.md):**
- High Priority: ~130 tasks (Phases 1-7, 9.2, 9.3)
- Medium Priority: ~30 tasks (Phase 8.1, 4.4, 9.1, 10.2)
- Low Priority: ~26 tasks (Phase 8.2, 8.3, 10.1)
