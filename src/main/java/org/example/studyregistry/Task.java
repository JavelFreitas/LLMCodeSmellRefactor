package org.example.studyregistry;

import java.time.LocalDateTime;

public class Task extends Registry {
    private String title;
    private String description;
    private String author;
    private LocalDateTime date;

    public Task(String title, String description, String author, LocalDateTime date) {
        validate(title, "Title cannot be null or empty");
        validate(author, "Author cannot be null or empty");
        validate(description, "Description cannot be null or empty");
        validateDate(date);

        this.title = title;
        this.description = description;
        this.author = author;
        this.date = date;
        this.name = title; // Registry inheritance
    }
    public void updateTitle(String newTitle) {
        validate(newTitle, "Title cannot be null or empty");
        this.title = newTitle;
        this.name = newTitle;
    }

    // Encapsula comportamento: Atualiza descrição
    public void updateDescription(String newDescription) {
        validate(newDescription, "Description cannot be null or empty");
        this.description = newDescription;
    }

    // Encapsula comportamento: Atualiza autor
    public void updateAuthor(String newAuthor) {
        validate(newAuthor, "Author cannot be null or empty");
        this.author = newAuthor;
    }

    // Encapsula comportamento: Atualiza data
    public void reschedule(LocalDateTime newDate) {
        validateDate(newDate);
        this.date = newDate;
    }

    // Método de resumo encapsulado
    public String getSummary() {
        return String.format("Task: %s\nAuthor: %s\nDate: %s\nDescription: %s",
                title, author, date.toString(), description);
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
    private void validate(String value, String errorMessage) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private void validateDate(LocalDateTime date) {
        if (date == null || date.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Date must be in the past or present");
        }
    }
}
