
package org.example.studyregistry;

import java.time.LocalDateTime;

import static org.example.controllers.MainController.getInput;

public class Task extends Registry {
    private String title;
    private String description;
    private String author;
    private LocalDateTime date;

    public Task(String title, String description, String author, LocalDateTime date) {
        validateInputs(title, description, author);
        this.title = title;
        this.name = title; // inherited from Registry
        this.description = description;
        this.author = author;
        this.date = date;
    }

    public static Task createTask(String title, String description, String author) {
        validateInputs(title, description, author);
        return new Task(title, description, author, LocalDateTime.now());
    }

    public static Task createFromUserInput() {
        System.out.println("Type the following info: title, description, author \n");
        String title = getInput();
        String description = getInput();
        String author = getInput();
        return createTask(title, description, author);
    }

    private static void validateInputs(String title, String description, String author) {
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

    public Task updateTask(String newTitle, String newDescription, String newAuthor) {
        return new Task(newTitle, newDescription, newAuthor, LocalDateTime.now());
    }

    public boolean isOverdue() {
        return date.isBefore(LocalDateTime.now());
    }

    public boolean isCreatedBy(String authorName) {
        return author.equalsIgnoreCase(authorName);
    }

    public String getFormattedTaskInfo() {
        return String.format("Task: %s\nDescription: %s\nAuthor: %s\nDate: %s",
                title, description, author, date);
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
}