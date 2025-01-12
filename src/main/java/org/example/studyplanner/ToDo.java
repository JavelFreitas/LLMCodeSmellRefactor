package org.example.studyplanner;

import java.text.MessageFormat;

public class ToDo implements PlannerMaterial {
    private Integer id;
    private String title;
    private String description;
    private int priority;

    // Constructor with validation
    public ToDo(Integer id, String title, String description, int priority) {
        setId(id);
        setTitle(title);
        setDescription(description);
        setPriority(priority);
    }

    // Encapsulated Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive integer.");
        }
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        this.title = title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = (description != null) ? description.trim() : "";
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        if (priority < 1 || priority > 5) {
            throw new IllegalArgumentException("Priority must be between 1 and 5.");
        }
        this.priority = priority;
    }

    // Behavior method to safely modify task data
    public void updateTask(String newTitle, String newDescription, int newPriority) {
        setTitle(newTitle);
        setDescription(newDescription);
        setPriority(newPriority);
    }

    // Behavior method to check if the task is high-priority
    public boolean isHighPriority() {
        return this.priority == 5;
    }

    // Behavior method to check if the task has a description
    public boolean hasDescription() {
        return !this.description.isEmpty();
    }

    // Overridden toString method with a custom message format
    @Override
    public String toString() {
        return MessageFormat.format(
                "[(Priority:{3}) ToDo {0}: {1}, {2}]",
                id, title, description, priority
        );
    }
}



