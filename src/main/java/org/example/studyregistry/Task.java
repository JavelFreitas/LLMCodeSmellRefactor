package org.example.studyregistry;

import java.time.LocalDateTime;

public class Task extends Registry {
    private String title;
    private String description;
    private String author;
    private LocalDateTime deadline;
    private LocalDateTime date;

    public Task(String title, String description, String author, LocalDateTime date) {
        this.title = title;
        this.name = title;
        this.description = description;
        this.author = author;
        this.deadline = date;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public boolean isOverdue() {
        return LocalDateTime.now().isAfter(date);
    }

    public String getFormattedDeadline() {
        return date.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public String getUrgency() {
        if (isOverdue()) {
            return "High";
        } else {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime twentyFourHoursFromNow = now.plusHours(24);
            return date.isBefore(twentyFourHoursFromNow) ? "Medium" : "Low";
        }
    }

    public boolean isAssignedTo(String author) {
        return this.author.equals(author);
    }
}