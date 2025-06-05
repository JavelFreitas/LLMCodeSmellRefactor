package org.example.studyregistry;

import java.time.LocalDateTime;
import java.time.Duration;

public class Task extends Registry {
    private String title;
    private String description;
    private String author;
    private LocalDateTime date;
    private TaskStatus status;
    private TaskPriority priority;

    public enum TaskStatus {
        PENDING, IN_PROGRESS, COMPLETED, CANCELLED
    }

    public enum TaskPriority {
        LOW, MEDIUM, HIGH
    }

    public Task(String title, String description, String author, LocalDateTime date) {
        validateFields(title, description, author, date);
        this.title = title;
        this.name = title;
        this.description = description;
        this.author = author;
        this.date = date;
        this.status = TaskStatus.PENDING;
        this.priority = calculateInitialPriority();
    }

    private void validateFields(String title, String description, String author, LocalDateTime date) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Título não pode ser vazio");
        }
        if (description == null) {
            throw new IllegalArgumentException("Descrição não pode ser nula");
        }
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Autor não pode ser vazio");
        }
        if (date == null) {
            throw new IllegalArgumentException("Data não pode ser nula");
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        validateFields(title, this.description, this.author, this.date);
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        validateFields(this.title, description, this.author, this.date);
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        validateFields(this.title, this.description, author, this.date);
        this.author = author;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        validateFields(this.title, this.description, this.author, date);
        this.date = date;
    }

    public void startTask() {
        if (status != TaskStatus.PENDING) {
            throw new IllegalStateException("Tarefa deve estar pendente para ser iniciada");
        }
        status = TaskStatus.IN_PROGRESS;
    }

    public void completeTask() {
        if (status != TaskStatus.IN_PROGRESS) {
            throw new IllegalStateException("Tarefa deve estar em progresso para ser completada");
        }
        status = TaskStatus.COMPLETED;
    }

    public void cancelTask() {
        if (status == TaskStatus.COMPLETED) {
            throw new IllegalStateException("Não é possível cancelar uma tarefa completada");
        }
        status = TaskStatus.CANCELLED;
    }

    public boolean isActive() {
        return status == TaskStatus.PENDING || status == TaskStatus.IN_PROGRESS;
    }

    public boolean isOverdue() {
        return isActive() && date.isBefore(LocalDateTime.now());
    }

    private TaskPriority calculateInitialPriority() {
        Duration timeUntilDue = Duration.between(LocalDateTime.now(), date);
        if (timeUntilDue.toDays() <= 1) {
            return TaskPriority.HIGH;
        } else if (timeUntilDue.toDays() <= 7) {
            return TaskPriority.MEDIUM;
        }
        return TaskPriority.LOW;
    }

    public void updatePriority() {
        this.priority = calculateInitialPriority();
    }

    public TaskPriority getTaskPriority() {  // Método renomeado
        return priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public String getTaskSummary() {
        return String.format("%s - %s (por %s em %s) [%s - %s]",
                title, description, author, date.toString(), status, priority);
    }

    public long getRemainingDays() {
        return Duration.between(LocalDateTime.now(), date).toDays();
    }

    public boolean isUrgent() {
        return isActive() && getTaskPriority() == TaskPriority.HIGH;  // Referência atualizada
    }
}