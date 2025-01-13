package org.example.studyplanner;

import java.text.MessageFormat;

public class ToDo implements PlannerMaterial {
    private Integer id;
    private String title;
    private String description;
    private int priority;
    private boolean isCompleted;

    public ToDo(Integer id, String title, String description, int priority) {
        setId(id);
        setTitle(title);
        setDescription(description);
        setPriority(priority);
        this.isCompleted = false; // O ToDo inicia como não concluído
    }

    @Override
    public String toString() {
        return MessageFormat.format(
                "[(Priority:{3}) ToDo {0}: {1}, {2}, Status: {4}]",
                getId(),
                getTitle(),
                getDescription(),
                getPriority(),
                isCompleted() ? "Completed" : "Pending"
        );
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("ID must be a positive integer");
        }
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        if (priority < 0) {
            throw new IllegalArgumentException("Priority cannot be negative");
        }
        this.priority = priority;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    public void markAsPending() {
        this.isCompleted = false;
    }

    public String getPriorityLevel() {
        if (priority > 5) {
            return "High Priority";
        } else if (priority > 2) {
            return "Medium Priority";
        } else {
            return "Low Priority";
        }
    }

    public int comparePriority(ToDo other) {
        return Integer.compare(this.priority, other.priority);
    }
}