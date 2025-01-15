package org.example.studyregistry;

import java.time.LocalDateTime;

public class Task extends Registry {
    private String title;
    private String description;
    private String author;
    private LocalDateTime date;
    private boolean isCompleted;

    public Task(String title, String description, String author, LocalDateTime date) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.date = date;
        this.name = title; // Inicializa o nome herdado
        this.isCompleted = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.name = title; // Atualiza o nome herdado quando o título muda
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

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    public void markAsUncompleted() {
        this.isCompleted = false;
    }

    public boolean isDueAfter(LocalDateTime otherDate) {
        return this.date.isAfter(otherDate);
    }

    public String getStatus() {
        return isCompleted ? "Concluída" : "Pendente";
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", date=" + date +
                ", isCompleted=" + isCompleted +
                '}';
    }
}