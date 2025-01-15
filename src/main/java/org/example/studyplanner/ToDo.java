package org.example.studyplanner;

import java.time.LocalDateTime;

public class ToDo implements PlannerMaterial {
    private Integer id;
    private String title;
    private String description;
    private int priority;
    private LocalDateTime dueDate; // Data de vencimento opcional
    private boolean completed;

    public ToDo(Integer id, String title, String description, int priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.completed = false; // Por padrão, a tarefa não está completa
    }

    @Override
    public String toString() {
        return String.format("[(Priority:%d) ToDo %d: %s, %s]", priority, id, title, description);
    }

    // Métodos getters e setters (sem alterações)
    public int getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }
    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    // *** ADIÇÃO DE COMPORTAMENTOS (CHAVE DA REFATORAÇÃO) ***

    public boolean isHighPriority() {
        return this.priority >= 3;
    }

    public boolean isOverdue() {
        return this.dueDate != null && this.dueDate.isBefore(LocalDateTime.now());
    }

    public void markAsCompleted() {
        this.completed = true;
    }

    public void updateDescription(String newDescription) {
        this.description = newDescription;
    }

    public String getStatus(){
        if(this.isCompleted()){
            return "Completed";
        }else if(this.isOverdue()){
            return "Overdue";
        }else{
            return "Pending";
        }
    }
}