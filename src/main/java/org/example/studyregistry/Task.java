package org.example.studyregistry;

import java.time.LocalDateTime;

public class Task extends Registry {
    private String title;
    private String description; 
    private String author;
    private LocalDateTime date;
    private boolean isCompleted;

    public Task(String title, String description, String author, LocalDateTime date) {
        validateTaskInput(title, description, author);
        this.title = title;
        this.name = title;
        this.description = description;
        this.author = author;
        this.date = date;
        this.isCompleted = false;
    }

    private void validateTaskInput(String title, String description, String author) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be empty");
        }
    }

    public void updateTask(String newTitle, String newDescription) {
        validateTaskInput(newTitle, newDescription, this.author);
        this.title = newTitle;
        this.name = newTitle;
        this.description = newDescription;
        this.date = LocalDateTime.now();
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    public boolean isOverdue() {
        return !isCompleted && date.isBefore(LocalDateTime.now());
    }

    public String getTaskSummary() {
        String status = isCompleted ? "Completed" : "Pending";
        return String.format("Task: %s (%s) - Created by: %s - Status: %s", 
            title, description, author, status);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setTitle(String title) {
        validateTaskInput(title, this.description, this.author);
        this.title = title;
        this.name = title;
    }

    public void setDescription(String description) {
        validateTaskInput(this.title, description, this.author);
        this.description = description;
    }
}
