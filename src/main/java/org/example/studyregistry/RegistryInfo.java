package org.example.studyregistry;

import java.time.LocalDateTime;

public class RegistryInfo {
    private Integer id;
    private String name;
    private Integer priority;
    private boolean isActive;

    public RegistryInfo(Integer id, String name, Integer priority, boolean isActive) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.isActive = isActive;
    }

    // Getters
    public Integer getId() { return id; }
    public String getName() { return name; }
    public Integer getPriority() { return priority; }
    public boolean isActive() { return isActive; }
}


