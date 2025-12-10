# Requirements Document

## Introduction

**Swag Collector** is a cross-platform endless runner game built with Kotlin Multiplatform and Compose Multiplatform. The game features a blonde pixel art character that automatically runs from left to right, collecting JetBrains swag items while avoiding obstacles. Players control only the jump action to navigate through an increasingly challenging environment. The game targets Android, iOS, Web, and Desktop platforms with a retro pixel art aesthetic.

---

## Requirements

### 1. Main Menu Screen

**User Story:**
> As a user, I want to see a main menu when I launch the game so that I can start playing or view my progress.

**Acceptance Criteria:**
- WHEN the application launches THEN the system SHALL display the Main Menu screen
- WHEN the Main Menu is displayed THEN the system SHALL show the game title "Swag Collector"
- WHEN the Main Menu is displayed THEN the system SHALL show a Play/Start button
- WHEN the user clicks/taps the Play button THEN the system SHALL transition to the Game Screen
- WHEN the Main Menu is displayed THEN the system SHALL optionally display the high score

---

### 2. Game Screen Layout

**User Story:**
> As a user, I want to see a clear game environment with all necessary elements so that I can play the game effectively.

**Acceptance Criteria:**
- WHEN the Game Screen is active THEN the system SHALL display a blue sky background
- WHEN the Game Screen is active THEN the system SHALL display pixel art clouds
- WHEN the Game Screen is active THEN the system SHALL display a grey road/path at the bottom of the screen
- WHEN the Game Screen is active THEN the system SHALL display a real-time score counter in the top corner
- WHEN the Game Screen is active THEN the system SHALL render all sprites in pixel art style

---

### 3. Player Character - Visual Design

**User Story:**
> As a user, I want to see a visually appealing pixel art character so that the game has a retro aesthetic.

**Acceptance Criteria:**
- WHEN the player character is rendered THEN the system SHALL display a blonde-haired pixel art character
- WHEN the character is on the ground THEN the system SHALL display a running animation with visible leg movement
- WHEN the character is jumping THEN the system SHALL display a jump pose/position animation
- WHEN the game starts THEN the system SHALL immediately display the running animation

---

### 4. Player Character - Positioning

**User Story:**
> As a user, I want the character to appear grounded on the road so that the game looks realistic and polished.

**Acceptance Criteria:**
- WHEN the character is on the ground THEN the system SHALL position the character's bottom edge aligned with the road surface top edge
- WHEN the character is running THEN the system SHALL NOT display the character floating above the road
- WHEN the character lands from a jump THEN the system SHALL return the character to the exact road surface level

---

### 5. Automatic Running

**User Story:**
> As a user, I want the character to run automatically so that I can focus on timing my jumps.

**Acceptance Criteria:**
- WHEN the game is in Playing state THEN the system SHALL automatically move the character from left to right
- WHEN the game is in Playing state THEN the system SHALL continuously display the running animation
- WHEN the game is in Playing state THEN the system SHALL NOT require user input for forward movement

---

### 6. Jump Control - Touch Input

**User Story:**
> As a mobile user, I want to jump by tapping the screen so that I can avoid obstacles and collect items.

**Acceptance Criteria:**
- WHEN the game is running on Android or iOS THEN the system SHALL detect touch input anywhere on the screen
- WHEN the user taps the screen while the character is on the ground THEN the system SHALL make the character jump
- WHEN the user taps the screen while the character is airborne THEN the system SHALL ignore the input
- WHEN the character jumps THEN the system SHALL display the jump animation

---

### 7. Jump Control - Keyboard Input

**User Story:**
> As a desktop/web user, I want to jump using keyboard keys so that I can control the game with familiar input methods.

**Acceptance Criteria:**
- WHEN the game is running on Desktop or Web THEN the system SHALL detect Space bar key presses
- WHEN the game is running on Desktop or Web THEN the system SHALL detect Up arrow key presses
- WHEN the user presses Space or Up arrow while the character is on the ground THEN the system SHALL make the character jump
- WHEN the user presses Space or Up arrow while the character is airborne THEN the system SHALL ignore the input

---

### 8. Jump Control - Mouse Input

**User Story:**
> As a web user, I want to jump by clicking the mouse so that I have an alternative to keyboard controls.

**Acceptance Criteria:**
- WHEN the game is running on Web THEN the system SHALL detect mouse click events
- WHEN the user clicks the mouse while the character is on the ground THEN the system SHALL make the character jump
- WHEN the user clicks the mouse while the character is airborne THEN the system SHALL ignore the input

---

### 9. Regular Collectible Items

**User Story:**
> As a user, I want to collect various JetBrains swag items so that I can increase my score.

**Acceptance Criteria:**
- WHEN regular swag items spawn THEN the system SHALL display them floating in the air at jump height
- WHEN the character collides with a Sticker, Pin, or Pen THEN the system SHALL award 1 point
- WHEN the character collides with a Sock, Tote Bag, Notebook, or Bottle THEN the system SHALL award 2 points
- WHEN a swag item is collected THEN the system SHALL remove the item from the game
- WHEN a swag item is collected THEN the system SHALL update the score display immediately
- WHEN a swag item is collected THEN the system SHALL provide clear visual or audio feedback

---

### 10. Special Collectible Items

**User Story:**
> As a user, I want to encounter rare high-value items so that I have exciting moments and scoring opportunities.

**Acceptance Criteria:**
- WHEN special swag items spawn THEN the system SHALL display them floating in the air at jump height
- WHEN special swag items spawn THEN the system SHALL spawn them less frequently than regular items
- WHEN the character collides with a Hoodie or T-Shirt THEN the system SHALL award 5 points
- WHEN a special item is collected THEN the system SHALL remove the item from the game
- WHEN a special item is collected THEN the system SHALL update the score display immediately
- WHEN a special item is collected THEN the system SHALL provide clear visual or audio feedback

---

### 11. Collision Detection - Swag Items

**User Story:**
> As a user, I want accurate collision detection so that collecting items feels fair and responsive.

**Acceptance Criteria:**
- WHEN the player character overlaps with a swag item hitbox THEN the system SHALL detect the collision
- WHEN a collision with a swag item is detected THEN the system SHALL immediately add the item's point value to the current score
- WHEN a collision with a swag item is detected THEN the system SHALL remove the swag item from the screen
- WHEN testing each swag type THEN the system SHALL award the correct point value for that item type

---

### 12. Obstacles - Visual Design

**User Story:**
> As a user, I want to see clear obstacles so that I know what to avoid.

**Acceptance Criteria:**
- WHEN obstacles spawn THEN the system SHALL display them as cardboard/paper boxes in pixel art style
- WHEN obstacles spawn THEN the system SHALL display them as single box height only
- WHEN obstacles are rendered THEN the system SHALL position them directly on the road surface
- WHEN obstacles are rendered THEN the system SHALL use the same ground plane as the player character

---

### 13. Obstacle Behavior

**User Story:**
> As a user, I want obstacles to remain stationary so that I can time my jumps predictably.

**Acceptance Criteria:**
- WHEN an obstacle spawns THEN the system SHALL keep it at a fixed position on the ground
- WHEN an obstacle is on screen THEN the system SHALL NOT move it vertically or horizontally relative to the ground
- WHEN the game scrolls THEN the system SHALL move obstacles with the scrolling environment

---

### 14. Collision Detection - Obstacles

**User Story:**
> As a user, I want the game to end when I hit an obstacle so that there are consequences for mistakes.

**Acceptance Criteria:**
- WHEN the player character collides with an obstacle hitbox THEN the system SHALL detect the collision
- WHEN a collision with an obstacle is detected THEN the system SHALL immediately end the game
- WHEN a collision with an obstacle is detected THEN the system SHALL transition to the Game Over screen
- WHEN a collision with an obstacle is detected THEN the system SHALL stop all gameplay

---

### 15. Score Display During Gameplay

**User Story:**
> As a user, I want to see my current score while playing so that I can track my progress.

**Acceptance Criteria:**
- WHEN the game is in Playing state THEN the system SHALL display the current score in the top corner
- WHEN the score increases THEN the system SHALL update the displayed score immediately
- WHEN the score is displayed THEN the system SHALL show it in a clear, readable format
- WHEN the game starts THEN the system SHALL initialize the score to 0

---

### 16. Difficulty Progression

**User Story:**
> As a user, I want the game to become progressively harder so that it remains challenging and engaging.

**Acceptance Criteria:**
- WHEN the game starts THEN the system SHALL set an initial game speed
- WHEN time passes during gameplay THEN the system SHALL gradually increase the game speed
- WHEN the game speed increases THEN the system SHALL maintain smooth gameplay
- WHEN the game speed increases THEN the system SHALL increase the challenge of avoiding obstacles and collecting items

---

### 17. Game Over Screen

**User Story:**
> As a user, I want to see my final score and have options to continue so that I can review my performance and play again.

**Acceptance Criteria:**
- WHEN the game ends THEN the system SHALL display the Game Over screen
- WHEN the Game Over screen is displayed THEN the system SHALL show a "Game Over" message
- WHEN the Game Over screen is displayed THEN the system SHALL show the final score
- WHEN the Game Over screen is displayed THEN the system SHALL show a Restart/Play Again button
- WHEN the Game Over screen is displayed THEN the system SHALL show a Return to Main Menu button
- WHEN the user clicks Restart THEN the system SHALL start a new game with score reset to 0
- WHEN the user clicks Return to Main Menu THEN the system SHALL transition to the Main Menu screen

---

### 18. Game State Management

**User Story:**
> As a user, I want the game to properly manage different states so that transitions are smooth and predictable.

**Acceptance Criteria:**
- WHEN the application starts THEN the system SHALL initialize in Menu state
- WHEN the user starts playing THEN the system SHALL transition to Playing state
- WHEN the game ends THEN the system SHALL transition to GameOver state
- WHEN in Playing state THEN the system SHALL track current score, game speed, player position, and all entity positions
- WHEN transitioning between states THEN the system SHALL properly initialize and clean up resources

---

### 19. Parallax Scrolling Background

**User Story:**
> As a user, I want to see a dynamic scrolling background so that the game feels more immersive.

**Acceptance Criteria:**
- WHEN the game is in Playing state THEN the system SHALL scroll the background elements
- WHEN the background scrolls THEN the system SHALL optionally implement parallax scrolling for clouds
- WHEN parallax scrolling is implemented THEN the system SHALL move clouds at a different speed than the ground
- WHEN the game is in Playing state THEN the system SHALL create a sense of depth and movement

---

### 20. High Score Persistence (Optional)

**User Story:**
> As a user, I want my high score to be saved so that I can track my best performance across sessions.

**Acceptance Criteria:**
- WHEN the game ends THEN the system SHALL compare the final score with the stored high score
- WHEN the final score exceeds the high score THEN the system SHALL update the stored high score
- WHEN the high score is updated THEN the system SHALL persist it locally on the device
- WHEN the Main Menu is displayed THEN the system SHALL optionally display the persisted high score
- WHEN the application restarts THEN the system SHALL load the previously saved high score

---

### 21. Cross-Platform Architecture

**User Story:**
> As a developer, I want the game to use shared code across platforms so that maintenance is efficient and consistent.

**Acceptance Criteria:**
- WHEN implementing game logic THEN the system SHALL place shared code in commonMain source set
- WHEN implementing UI THEN the system SHALL use Compose Multiplatform for shared UI components
- WHEN implementing platform-specific features THEN the system SHALL place code in respective platform source sets
- WHEN building for any platform THEN the system SHALL follow the existing project structure in composeApp module

---

### 22. Running Animation Timing

**User Story:**
> As a user, I want to see the character running immediately when the game starts so that the game feels responsive and alive.

**Acceptance Criteria:**
- WHEN the game transitions to Playing state THEN the system SHALL immediately start the running animation
- WHEN the character is on the ground THEN the system SHALL continuously cycle through running animation frames
- WHEN the character jumps THEN the system SHALL switch to jump pose
- WHEN the character lands THEN the system SHALL immediately resume the running animation

---

### 23. Sprite Rendering

**User Story:**
> As a user, I want all game elements to be rendered in consistent pixel art style so that the game has a cohesive visual identity.

**Acceptance Criteria:**
- WHEN rendering any sprite THEN the system SHALL use pixel art style
- WHEN rendering sprites THEN the system SHALL maintain simple and clean visual design
- WHEN rendering sprites THEN the system SHALL ensure consistency across all game elements
- WHEN rendering sprites THEN the system SHALL maintain the retro game aesthetic

---

### 24. Swag Item Visual Rendering

**User Story:**
> As a user, I want collectible swag items to be visually recognizable and properly rendered so that I can identify what I'm collecting.

**Acceptance Criteria:**
- WHEN swag items are rendered THEN the system SHALL use XML drawable resources for proper vector-based rendering
- WHEN displaying Stickers THEN the system SHALL render them with recognizable sticker appearance
- WHEN displaying Pins THEN the system SHALL render them with recognizable pin appearance
- WHEN displaying Pens THEN the system SHALL render them with recognizable pen appearance
- WHEN displaying Socks THEN the system SHALL render them with recognizable sock appearance
- WHEN displaying Tote Bags THEN the system SHALL render them with recognizable tote bag appearance
- WHEN displaying Notebooks THEN the system SHALL render them with recognizable notebook appearance
- WHEN displaying Bottles THEN the system SHALL render them with recognizable bottle appearance
- WHEN rendering swag items THEN the system SHALL maintain pixel art aesthetic consistency

---

### 25. Character Fixed Position

**User Story:**
> As a user, I want the character to remain visible at a fixed position on screen so that I can focus on timing jumps without losing sight of my character.

**Acceptance Criteria:**
- WHEN the game is in Playing state THEN the system SHALL keep the character at a fixed horizontal position on screen
- WHEN the game is running THEN the system SHALL NOT move the character's x-coordinate
- WHEN the game is running THEN the system SHALL scroll the background and items to create the illusion of movement
- WHEN the character is displayed THEN the system SHALL position it at approximately 15-20% from the left edge of the screen
- WHEN the game is running THEN the system SHALL ensure the character remains fully visible at all times

---

### 26. UI Element Styling

**User Story:**
> As a user, I want UI elements to have clean, polished appearance without distracting visual artifacts so that the interface looks professional.

**Acceptance Criteria:**
- WHEN displaying the game title THEN the system SHALL NOT render thick black borders around it
- WHEN displaying the Play button THEN the system SHALL NOT render thick black borders around it
- WHEN displaying the score counter THEN the system SHALL NOT render thick black borders around it
- WHEN displaying UI elements THEN the system SHALL use subtle borders or no borders for a clean appearance
- WHEN displaying UI text boxes THEN the system SHALL maintain pixel art aesthetic without excessive border thickness
