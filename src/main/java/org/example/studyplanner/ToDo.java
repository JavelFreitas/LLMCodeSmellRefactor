package org.example.studyplanner;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    private void validateFields(Integer id, String title, int priority) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("ID must be positive");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (priority < 1 || priority > 5) {
            throw new IllegalArgumentException("Priority must be between 1 and 5");
        }
    }

    public void updateTitle(String newTitle) {
        if (newTitle == null || newTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = newTitle;
    }

    public String getFormattedTodoWithTracking(List<LocalDateTime> trackingDates) {
        StringBuilder str = new StringBuilder();
        str.append(this.toString()).append("\n");

        if (trackingDates == null || trackingDates.isEmpty()) {
            return str.append("No tracks found\n").toString();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (LocalDateTime date : trackingDates) {
            str.append(formatter.format(date)).append("\n");
        }
        return str.toString();
    }

    public void updateDescription(String newDescription) {
        this.description = newDescription != null ? newDescription : "";
    }

    public void increasePriority() {
        if (priority < 5) priority++;
    }

    public void decreasePriority() {
        if (priority > 1) priority--;
    }

    public boolean isHighPriority() {
        return priority >= 4;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("ID must be positive");
        }
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return MessageFormat.format("[(Priority:{3}) ToDo {0}: {1}, {2}]",
                id, title, description, priority);
    }
}