# Development Guidelines

## Working with the Task Checklist

This document provides instructions for managing the technical task list in `docs/tasks.md` during development.

---

### Marking Task Completion

- **Mark completed tasks** by changing `[ ]` to `[x]`
- Example:
  ```markdown
  - [x] 1.1.1 Define GameState enum/sealed class with Menu, Playing, and GameOver states
  ```
- Mark tasks as complete only after implementation and verification

---

### Adding New Tasks

- **Add new tasks** within the appropriate phase and subsection
- **Maintain numbering format**: Use the pattern `[Phase].[Subsection].[Task]` (e.g., `3.2.7`)
- **Include required metadata** for each new task:
  - Link to plan item: `*Plan: X.Y*`
  - Link to requirement(s): `*Requirements: #N*`
- **Example**:
  ```markdown
  ### Character Rendering & Animation
  *Plan: 3.2 | Requirements: #3, #22*
  
  - [ ] 3.2.1 Create PlayerCharacter data class or component
  - [ ] 3.2.7 Add new animation state for special effects
  ```

---

### Modifying Existing Tasks

- **Update task descriptions** if requirements change, but keep the task number
- **Keep the checkbox** and metadata intact when editing
- **Document significant changes** in commit messages or comments

---

### Phase Management

- **Keep all phases intact** - do not remove or reorder phases
- **Complete phases sequentially** when possible, following the priority guidance in `docs/plan.md`
- **High-priority phases** (1-7, 9.2, 9.3) should be completed before medium and low-priority phases

---

### Linking Requirements

- **Every task must link** to at least one requirement from `docs/requirements.md`
- **Every task must link** to a plan item from `docs/plan.md`
- **Format**: Use `*Plan: X.Y | Requirements: #N, #M*` at the subsection level
- When adding tasks, verify the requirement and plan item exist

---

### Formatting Consistency

- **Use markdown checkboxes**: `- [ ]` for incomplete, `- [x]` for complete
- **Maintain indentation**: Tasks use standard list indentation (no extra spaces)
- **Keep numbering sequential**: If adding tasks between existing ones, renumber subsequent tasks
- **Preserve structure**: Keep phase headers, subsection headers, and metadata lines unchanged

---

### Task Granularity

- **Keep tasks atomic**: Each task should represent a single, testable unit of work
- **Split large tasks**: If a task takes more than a few hours, consider breaking it into subtasks
- **Be specific**: Task descriptions should be clear and actionable

---

### Progress Tracking

- **Review regularly**: Check `docs/tasks.md` at the start and end of each work session
- **Update immediately**: Mark tasks complete as soon as they're verified
- **Track blockers**: Add comments (using `<!-- -->`) if a task is blocked
- **Example**:
  ```markdown
  - [ ] 5.1.1 Create/obtain pixel art sprites for 1-point items
  <!-- Blocked: Waiting for asset designer -->
  ```

---

### Integration with Plan and Requirements

- **Refer to `docs/plan.md`** for detailed implementation guidance on each plan item
- **Refer to `docs/requirements.md`** for acceptance criteria and user stories
- **Keep documents synchronized**: If requirements or plan change, update tasks accordingly

---

### Quality Checklist

Before marking a task as complete, verify:

- [ ] Implementation matches the requirement acceptance criteria
- [ ] Code follows project style and conventions
- [ ] Unit tests written (if applicable)
- [ ] Manual testing performed (if applicable)
- [ ] Code reviewed (if working in a team)
- [ ] Documentation updated (if applicable)

---

## Quick Reference

| Action | Format |
|--------|--------|
| Mark complete | `- [x] Task description` |
| Mark incomplete | `- [ ] Task description` |
| Add task | `- [ ] X.Y.Z Task description` |
| Link metadata | `*Plan: X.Y \| Requirements: #N*` |
| Add comment | `<!-- Comment text -->` |

---

## Example Workflow

1. **Start work session**: Review `docs/tasks.md` to identify next task
2. **Check requirements**: Read linked requirement in `docs/requirements.md`
3. **Check plan**: Read linked plan item in `docs/plan.md` for implementation details
4. **Implement**: Write code following the task description
5. **Verify**: Test implementation against acceptance criteria
6. **Mark complete**: Change `[ ]` to `[x]` in `docs/tasks.md`
7. **Commit**: Commit changes with reference to task number (e.g., "Implement task 3.2.1")

---

## Notes

- The task list is a living document - update it as the project evolves
- Completed tasks provide a historical record of development progress
- Use task numbers in commit messages for traceability
- If you discover missing tasks during development, add them immediately
