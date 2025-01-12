package org.example.studyplanner;

import java.text.MessageFormat;
import java.util.Objects;
import java.time.LocalDateTime;

public class ToDo implements PlannerMaterial, Comparable<ToDo> {
    private Integer id;
    private String title;
    private String description;
    private int priority;
    private boolean completed;
    private LocalDateTime createdAt;
    private LocalDateTime lastModified;

    public ToDo(Integer id, String title, String description, int priority) {
        validateInputs(title, description, priority);
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.completed = false;
        this.createdAt = LocalDateTime.now();
        this.lastModified = this.createdAt;
    }

    private void validateInputs(String title, String description, int priority) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }
        if (priority < 1 || priority > 5) {
            throw new IllegalArgumentException("Priority must be between 1 and 5");
        }
    }

    public void updatePriority(int newPriority) {
        validateInputs(this.title, this.description, newPriority);
        this.priority = newPriority;
        updateLastModified();
    }

    public void toggleCompletion() {
        this.completed = !this.completed;
        updateLastModified();
    }

    @Override
    public String toString() {
        return MessageFormat.format("[(Priority:{3}) ToDo {0}: {1}, {2}]", id, title, description, priority);
    }

    public String toFormattedString() {
        return toString() + "\n";
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        updateLastModified();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        updateLastModified();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        updateLastModified();
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
        updateLastModified();
    }

    public void incrementPriority() {
        if (priority < 5) {
            priority++;
            updateLastModified();
        }
    }

    public void decrementPriority() {
        if (priority > 1) {
            priority--;
            updateLastModified();
        }
    }

    public boolean isHighPriority() {
        return priority >= 4;
    }

    public void markAsComplete() {
        this.completed = true;
        updateLastModified();
    }

    public void markAsIncomplete() {
        this.completed = false;
        updateLastModified();
    }

    public String getSummary() {
        return String.format("[Priority: %d] %s - %s", 
            priority, 
            title, 
            description.length() > 50 ? description.substring(0, 47) + "..." : description);
    }

    public boolean matchesSearch(String searchTerm) {
        return title.toLowerCase().contains(searchTerm.toLowerCase()) ||
               description.toLowerCase().contains(searchTerm.toLowerCase());
    }

    public String getFormattedSummary() {
        String status = completed ? "✓" : "○";
        return MessageFormat.format("{0} [{1}] {2}: {3}", 
            status, getPriorityText(), title, description);
    }

    private String getPriorityText() {
        return switch (priority) {
            case 1 -> "Low";
            case 2, 3 -> "Medium";
            case 4, 5 -> "High";
            default -> "Unknown";
        };
    }

    private void updateLastModified() {
        this.lastModified = LocalDateTime.now();
    }

    @Override
    public int compareTo(ToDo other) {
        return Integer.compare(other.priority, this.priority);
    }
}
