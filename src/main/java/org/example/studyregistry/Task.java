package org.example.studyregistry;

import java.time.LocalDateTime;

public class Task extends Registry {
    private String title;
    private String description;
    private String author;
    private LocalDateTime date;

    public Task(String title, String description, String author, LocalDateTime date) {
        this.title = title;
        this.name = title; // Assuming name is inherited from Registry
        this.description = description;
        this.author = author;
        this.date = date;
    }

    // Behavior method: Check if the task is due
    public boolean isOverdue(LocalDateTime currentDate) {
        return date.isBefore(currentDate);
    }

    // Behavior method: Update the task's author and log the change
    public void updateAuthor(String newAuthor) {
        System.out.println("Author updated from " + author + " to " + newAuthor);
        this.author = newAuthor;
    }

    // Behavior method: Extend the task deadline
    public void extendDeadline(int days) {
        this.date = this.date.plusDays(days);
        System.out.println("Deadline extended by " + days + " days. New deadline: " + this.date);
    }

    // Behavior method: Provide a summary of the task
    public String getSummary() {
        return "Task: " + title + "\nDescription: " + description +
                "\nAuthor: " + author + "\nDue Date: " + date;
    }

    // Getters and setters retained for external use, if needed
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
}
