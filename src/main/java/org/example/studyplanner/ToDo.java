package org.example.studyplanner;

import java.text.MessageFormat;

public class ToDo implements PlannerMaterial {
    private Integer id;
    private String title;
    private String description;
    private int priority;

    public ToDo(Integer id, String title, String description, int priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return MessageFormat.format("[(Priority:{3}) ToDo {0}: {1}, {2}]", id, title, description, priority);
    }

    // Encapsulated behavior: Increase priority
    public void increasePriority() {
        if (priority > 1) {
            priority--;
        }
    }

    // Encapsulated behavior: Decrease priority
    public void decreasePriority() {
        if (priority < 10) {
            priority++;
        }
    }

    // Encapsulated behavior: Check if high priority
    public boolean isHighPriority() {
        return priority == 1;
    }

    // Encapsulated behavior: Summarize ToDo details
    public String summarize() {
        return MessageFormat.format("ToDo Summary: [{0}] {1} - {2} (Priority: {3})",
                id, title, description, priority);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
