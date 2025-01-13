package org.example.studyregistry;

import java.time.LocalDateTime;

public class Task extends Registry {
    private String title;
    private String description;
    private String author;
    private LocalDateTime date;

    public Task(String title, String description, String author, LocalDateTime date) {
        this.title = title;
        this.name = title; // Compatibilidade com a superclasse (Registry)
        this.description = description;
        this.author = author;
        this.date = date;
    }

    // Métodos de acesso para compatibilidade com testes
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

    // Adicionando lógica relevante para encapsular comportamentos
    public boolean isOverdue() {
        return date.isBefore(LocalDateTime.now());
    }

    public void postponeTask(int days) {
        this.date = this.date.plusDays(days);
    }

    public String getSummary() {
        return String.format(
                "Task '%s' by %s. Due on %s. Description: %s",
                title,
                author,
                date.toString(),
                description
        );
    }

    @Override
    public String toString() {
        return String.format(
                "Task{title='%s', description='%s', author='%s', date=%s}",
                title, description, author, date
        );
    }
}
