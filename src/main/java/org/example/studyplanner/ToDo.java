package org.example.studyplanner;

import java.text.MessageFormat;

public class ToDo implements PlannerMaterial {
    private Integer id;
    private String title;
    private String description;
    private int priority;

    public ToDo(Integer id, String title, String description, int priority) {
        validateFields(id, title, priority);
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public String toStringWithTracking(String trackingInfo) {
        return this.toString() + "\n" + trackingInfo;
    }

    private void validateFields(Integer id, String title, int priority) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID deve ser positivo");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Título é obrigatório");
        }
        if (priority < 1 || priority > 5) {
            throw new IllegalArgumentException("Prioridade deve estar entre 1 e 5");
        }
    }

    public void increasePriority() {
        if (priority < 5) {
            priority++;
        }
    }

    public void decreasePriority() {
        if (priority > 1) {
            priority--;
        }
    }

    public void updateDetails(String newTitle, String newDescription) {
        validateFields(this.id, newTitle, this.priority);
        this.title = newTitle;
        this.description = newDescription;
    }

    public boolean isHighPriority() {
        return priority >= 4;
    }

    @Override
    public String toString() {
        return MessageFormat.format("[(Priority:{3}) ToDo {0}: {1}, {2}]",
                id, title, description, priority);
    }

    // Métodos necessários para compatibilidade com testes existentes
    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        validateFields(id, this.title, this.priority);
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        validateFields(this.id, title, this.priority);
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        validateFields(this.id, this.title, priority);
        this.priority = priority;
    }

    // Métodos específicos de comportamento
    public boolean isPending() {
        return true;
    }

    public boolean hasSamePriority(ToDo other) {
        return this.priority == other.priority;
    }

    public boolean isMoreUrgentThan(ToDo other) {
        return this.priority > other.priority;
    }
}