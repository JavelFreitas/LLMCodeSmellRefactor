package org.example.studyplanner;

import java.text.MessageFormat;
import java.util.Objects;

public class ToDo implements PlannerMaterial {
    private Integer id;
    private String title;
    private String description;
    private int priority;
    private boolean isCompleted; // Added field to track completion

    public ToDo(Integer id, String title, String description, int priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.isCompleted = false; // Initialize as not completed
    }

    @Override
    public String toString() {
        return MessageFormat.format("[(Priority:{3}) ToDo {0}: {1}, {2}, {4}]", id, title, description, priority, isCompleted ? "Completed" : "Not Completed");
    }

    // Add behavior: mark as completed
    public void markAsCompleted() {
        this.isCompleted = true;
    }

    // Check if the ToDo is completed
    public boolean isCompleted() {
        return this.isCompleted;
    }

    // Getter methods (unchanged for compatibility)
    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
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
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDo toDo = (ToDo) o;
        return Objects.equals(id, toDo.id) &&
                Objects.equals(title, toDo.title) &&
                Objects.equals(description, toDo.description) &&
                priority == toDo.priority &&
                isCompleted == toDo.isCompleted;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, priority, isCompleted);
    }
}