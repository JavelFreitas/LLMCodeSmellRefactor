package org.example.studyregistry;

import java.time.LocalDateTime;

public class Task extends Registry {
    private String title;
    private String description;
    private String author;
    private LocalDateTime date;

    public Task(String title, String description, String author, LocalDateTime date) {
        this.title = title;
        this.name = title;
        this.description = description;
        this.author = author;
        this.date = date;
    }

    // Getter and Setter methods remain but can be used internally.

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

    // Behavior methods encapsulating functionality

    /**
     * Checks if the task is overdue.
     * @return true if the current date is past the task's date.
     */
    public boolean isOverdue() {
        return LocalDateTime.now().isAfter(date);
    }

    /**
     * Provides a brief summary of the task.
     * @return A formatted string summarizing the task details.
     */
    public String getSummary() {
        return String.format("Task '%s' by %s due on %s", title, author, date.toString());
    }

    /**
     * Updates task details in a controlled way.
     * @param title       New title of the task.
     * @param description New description of the task.
     * @param date        New due date of the task.
     */
    public void updateDetails(String title, String description, LocalDateTime date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }
}
