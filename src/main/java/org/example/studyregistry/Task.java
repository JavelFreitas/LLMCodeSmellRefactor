package org.example.studyregistry;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Task extends Registry{
    private String title;
    private String description;
    private String author;
    private LocalDateTime date;
    private boolean completed;
    private List<LocalDateTime> reminders = new ArrayList<>();
    private List<Task> subtasks = new ArrayList<>();


    public Task(String title, String description, String author, LocalDateTime date) {
        this.title = title;
        this.name = title;
        this.description = description;
        this.author = author;
        this.date = date;
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

    public void markAsCompleted() {
        this.completed = true;
    }

    public void addReminder(LocalDateTime reminderDate) {
        reminders.add(reminderDate);
    }

    public void addSubtask(Task subtask) {
        subtasks.add(subtask);
    }

    public void removeSubtask(Task subtask) {
        subtasks.remove(subtask);
    }
}
