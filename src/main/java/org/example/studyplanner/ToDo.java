package org.example.studyplanner;

import java.text.MessageFormat;

public class ToDo implements PlannerMaterial {
    private int id;
    private String title;
    private String description;
    private int priority;
    private boolean completed;

    public ToDo(int id, String title, String description, int priority) {
        this.id = id;
        this.setTitle(title);
        this.setDescription(description);
        this.setPriority(priority);
        this.completed = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        if (priority < 0 || priority > 10) {
            throw new IllegalArgumentException("Priority must be between 0 and 10.");
        }
        this.priority = priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markAsCompleted() {
        this.completed = true;
    }

    public boolean isHighPriority() {
        return this.priority >= 5;
    }

    public String getSummary() {
        return String.format("Task: %s (Priority: %d)", this.title, this.priority);
    }

    public boolean matchesKeyword(String keyword) {
        return this.title.toLowerCase().contains(keyword.toLowerCase()) ||
                this.description.toLowerCase().contains(keyword.toLowerCase());
    }

    @Override
    public String toString() {
        // 🔹 Restaurando o formato original do `toString()`
        return MessageFormat.format("[(Priority:{3}) ToDo {0}: {1}, {2}]", id, title, description, priority);
    }
}
