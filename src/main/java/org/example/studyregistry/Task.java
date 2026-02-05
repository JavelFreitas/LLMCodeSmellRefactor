package org.example.studyregistry;

import java.time.LocalDateTime;

public class Task extends Registry {
    private String title;
    private String description;
    private String author;
    private LocalDateTime date;

    public Task(String title, String description, String author, LocalDateTime date) {
        validateTitle(title);
        validateDescription(description);
        validateAuthor(author);
        validateDate(date);

        this.title = title;
        this.name = title;
        this.description = description;
        this.author = author;
        this.date = date;
    }

    private void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
    }

    private void validateDescription(String description) {
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }
    }

    private void validateAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be null or empty");
        }
    }

    private void validateDate(LocalDateTime date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        validateTitle(title);
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        validateDescription(description);
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        validateAuthor(author);
        this.author = author;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        validateDate(date);
        this.date = date;
    }

    // Domain-specific behavior methods
    public boolean isOverdue() {
        return LocalDateTime.now().isAfter(date);
    }

    public boolean isDueSoon(long days) {
        return LocalDateTime.now().plusDays(days).isAfter(date) && !isOverdue();
    }

    public void markAsUrgent() {
        this.description = "[URGENT] " + this.description;
    }

    public boolean isUrgent() {
        return this.description.startsWith("[URGENT]");
    }
}
