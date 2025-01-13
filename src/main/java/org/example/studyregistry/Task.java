package org.example.studyregistry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task extends Registry {
    private String title;
    private String description;
    private String author;
    private LocalDateTime date;

    public Task(String title, String description, String author, LocalDateTime date) {
        setTitle(title);
        setDescription(description);
        setAuthor(author);
        setDate(date);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        this.title = title;
        setName(title);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be null or empty");
        }
        this.author = author;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        this.date = date;
    }

    public String getSummary() {
        return String.format("Task: %s - %s", title, description);
    }

    public String getFormattedDate() {
        return formatDate(date);
    }

    public boolean isPastDue() {
        return date.isBefore(LocalDateTime.now());
    }

    public boolean isComplete() {
        return description != null && !description.trim().isEmpty();
    }

    private String formatDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return date.format(formatter);
    }

    private void setName(String title) {
        this.name = title;
    }
}