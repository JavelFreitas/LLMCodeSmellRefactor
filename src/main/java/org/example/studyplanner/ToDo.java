
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

    public void updateToDo(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public boolean matchesId(Integer id) {
        return this.id.equals(id);
    }

    public void validatePriority() throws Exception {
        if (priority < 1 || priority > 5) {
            throw new Exception("Priority must be between 1 and 5");
        }
    }

    public boolean isHighPriority() {
        return priority >= 4;
    }

    public boolean hasSamePriority(ToDo other) {
        return this.priority == other.priority;
    }

    public int getId() {
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