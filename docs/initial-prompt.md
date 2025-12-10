# Spec-Driven Development Prompt

Transform the provided high-level requirements into a complete set of project planning artifacts for spec-driven development.

## Instructions:

You must produce **four files** inside the `docs/` and `.junie/` directories:

- `docs/requirements.md`
- `docs/plan.md`
- `docs/tasks.md`
- `.junie/guidelines.md`

Follow the methodology below step by step:

---

### Step 1: Create `docs/requirements.md`

- Title: **Requirements Document**
- Introduction: Summarize the application purpose and key functionality.
- Requirements section:

    - Use sequential numbering (1, 2, 3, …).
    - Each requirement must include:
        - **User Story** in the format:

          > As a user, I want [goal] so that [benefit/reason]

        - **Acceptance Criteria** in the format:

          > WHEN [condition] THEN the system SHALL [expected behavior]

- Guidelines:

    - Focus on user goals and benefits.
    - Make acceptance criteria specific, testable, and precise.
    - Cover normal flows, edge cases, error handling, persistence, and UI/UX.
    - Group related requirements logically.

---

### Step 2: Create `docs/plan.md`

- Analyze `docs/requirements.md`.
- Develop a **detailed implementation plan**:
    - Link each plan item explicitly to the corresponding requirements.
    - Assign priorities (e.g., High, Medium, Low).
    - Group related plan items logically.
- Ensure comprehensive coverage of all requirements.

---

### Step 3: Create `docs/tasks.md`

- Based on the implementation plan in `docs/plan.md`, produce a **detailed enumerated technical task list**:

    - Each task must have a placeholder `[ ]` to mark completion.
    - Link each task both to:
        - the development plan item in `docs/plan.md`
        - the related requirement(s) in `docs/requirements.md`
- Group tasks into **development phases**.
- Organize phases logically (e.g., Setup → Core Features → Advanced Features → Testing & QA).

---

### Step 4: Update `.junie/guidelines.md`

- Add **concise technical instructions** on how to work with the `docs/tasks.md` checklist.
- Instructions should include:
    - Mark tasks as `[x]` when completed.
    - Keep phases intact but add new tasks if needed.
    - Ensure every new or modified task stays linked to a requirement and plan item.
    - Keep formatting consistent with the existing style.

---

## Input:

# Swag Collector - Game Specification

## Project Overview
**Game Name:** Swag Collector  
**Technology Stack:** Kotlin Multiplatform (KMP) with Compose Multiplatform  
**Target Platforms:** Android, iOS, Web, Desktop  
**Visual Style:** Pixel Art (simple, aesthetic, retro game aesthetic)  
**Project Structure:** Pre-existing KMP project in `composeApp/` module

---

## Game Mechanics

### Core Gameplay
- **Gameplay Type:** Endless runner / side-scroller
- **Player Character:** Blonde pixel art character that runs automatically from left to right
- **Player Actions:** Jump (single action) to avoid obstacles and collect items
- **Objective:** Collect JetBrains swag items while avoiding obstacles
- **Difficulty Progression:** Game speed increases gradually over time

### Player Character Specifications
Blonde hair, pixel art style
Animations required:
Running animation (legs moving) - must be visibly animated immediately when game starts
Jumping animation (character in jump pose/position)
Character runs automatically; player controls only jumping
Ground Positioning: Character must be positioned at road level, appearing to run directly on the road surface (not floating above it)

---

## Collectible Items (Swag)

### Regular Items (floating in air, must jump to collect)
| Item | Point Value | Rarity |
|------|-------------|--------|
| Stickers | 1 | Common |
| Pins | 1 | Common |
| Pens | 1 | Common |
| Socks | 2 | Common |
| Tote Bags | 2 | Common |
| Notebooks | 2 | Common |
| Bottles | 2 | Common |

### Special Items (rare spawns)
| Item | Point Value | Rarity |
|------|-------------|--------|
| Hoodie | 5 | Rare |
| T-Shirt | 5 | Rare |

---

## Obstacles

Type: Cardboard/paper boxes
Behavior: Static obstacles on the ground that player must jump over
Size: Single box height only (no stacked boxes)
Placement: Obstacles must be positioned directly on the road surface
Collision: Hitting an obstacle ends the game

---

## Visual Design

### Color Palette & Elements
| Element | Description |
|---------|-------------|
| Background | Blue sky color |
| Clouds | Pixel art clouds (parallax scrolling optional) |
| Ground | Grey road/path |
| All sprites | Pixel art style, simple and clean |

### Screen Layout
- Score display visible during gameplay (top corner)
- Ground at bottom of screen
- Sky/clouds as scrolling background
- Items floating at jump height
- Obstacles on ground level

---

## Game Screens (3 total)

### 1. Main Menu Screen
- Game title "Swag Collector"
- Play/Start button
- Optional: High score display

### 2. Game Screen
- Active gameplay area
- Real-time score counter
- Player character, obstacles, and collectibles
- Background with clouds and ground

### 3. Game Over Screen
- "Game Over" message
- Final score display
- Restart/Play Again button
- Return to Main Menu button

---

## Technical Requirements

### Platform-Specific Considerations
- **Android/iOS:** Touch input for jump (tap anywhere)
- **Desktop:** Keyboard input for jump (Space bar or Up arrow)
- **Web:** Keyboard or mouse click for jump

### Architecture
- Use Compose Multiplatform for shared UI
- Follow existing project structure in `composeApp/` module
- Shared game logic in `commonMain`
- Platform-specific implementations in respective source sets

### State Management
- Game states: Menu, Playing, GameOver
- Track: current score, game speed, player position, obstacle/item positions

---

## Scoring System
Score increments based on collected item values
Collision Detection: Player must properly detect collision with swag items and increment score accordingly
Visual/Audio Feedback: When collecting swag, provide clear feedback that points were awarded
Score persists and displays during gameplay
Final score shown on Game Over screen
Optional: Persist high score locally

## Additional Technical Requirements
### Character Animation
Running Animation: The character's running animation (leg movement) MUST start immediately when the game transitions to the Playing state
Animation frames should cycle continuously while the character is on the ground
Animation should switch to jump pose only during airborne state
### Positioning Requirements
Character Y-Position: Character sprite bottom edge must align with road surface top edge
Obstacle Y-Position: Obstacle sprites must sit directly on the road surface
Consistent Ground Plane: Both character and obstacles share the same ground reference point
### Collision & Scoring
* Implement proper hitbox collision detection between player and swag items
* When collision with swag item is detected:
  * Remove the swag item from the game
  * Add the item's point value to the current score
  * Update the score display immediately
* Verify scoring works: Test that collecting each swag type awards the correct points
* 
## Output:

1. `docs/requirements.md` – structured requirements document
2. `docs/plan.md` – implementation plan with priorities and links
3. `docs/tasks.md` – detailed enumerated task list grouped into phases
4. `.junie/guidelines.md` – updated concise instructions for working with the task list