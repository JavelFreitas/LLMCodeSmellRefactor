package org.example.studyregistry;

public class RegistryProperties {
    private Integer id;
    private String name;
    private Integer priority;
    private boolean isActive;

    public RegistryProperties(Integer id, String name, Integer priority, boolean isActive) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.isActive = isActive;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public Integer getPriority() { return priority; }
    public boolean isActive() { return isActive; }
}