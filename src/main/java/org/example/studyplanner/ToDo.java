package org.example.studyplanner;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ToDo implements PlannerMaterial{
    private Integer id;
    private String title;
    private String description;
    private int priority;
    private List<String> tags = new ArrayList<>(); // Initialize with an empty list



    public ToDo(Integer id, String title, String description, int priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;

    }

    public String toString() {
        return formatAsText();
    }

    private String formatAsText() {
        return MessageFormat.format("[(Priority:{3}) ToDo {0}: {1}, {2}]", id, title, description, priority);
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public void removeTag(String tag) {
        tags.remove(tag);
    }

    public boolean hasTag(String tag) {
        return tags.contains(tag);
    }

    public List<String> getTags() {
        return Collections.unmodifiableList(new ArrayList<>(tags)); // Return an unmodifiable list
    }

    public void replaceTags(List<String> newTags) {
        tags.clear(); // Clear existing tags
        tags.addAll(newTags); // Add all new tags
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
        if (priority < 1 || priority > 5) {
            throw new IllegalArgumentException("Priority must be between 1 and 5");
        }
        this.priority = priority;
    }
}
