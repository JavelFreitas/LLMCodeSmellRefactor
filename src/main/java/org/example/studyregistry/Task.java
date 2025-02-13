package org.example.studyregistry;

import java.time.LocalDateTime;

public class Task extends Registry {
    private String title;
    private String description;
    private final String author;
    private LocalDateTime date;
    private boolean completed;

    public Task(String title, String description, String author, LocalDateTime date) {
        this.setTitle(title);
        this.setDescription(description);
        this.author = author;
        this.date = date;
        this.completed = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }
        this.title = title;
        this.name = title; // Mantendo compatibilidade com Registry
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be empty.");
        }
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void reschedule(LocalDateTime newDate) {
        if (newDate.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("New date must be in the future.");
        }
        this.date = newDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markAsCompleted() {
        this.completed = true;
    }

    public boolean isOverdue() {
        return !completed && date.isBefore(LocalDateTime.now());
    }

    public void postponeByDays(int days) {
        if (days < 1) {
            throw new IllegalArgumentException("Postpone days must be at least 1.");
        }
        this.date = this.date.plusDays(days);
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", date=" + date +
                ", completed=" + completed +
                '}';
    }
}
