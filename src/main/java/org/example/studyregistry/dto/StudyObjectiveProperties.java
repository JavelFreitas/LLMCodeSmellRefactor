package org.example.studyregistry.dto;

import java.util.Objects;

public class StudyObjectiveProperties {

    private Integer id;
    private Integer priority;
    private DateTime dateTime; // Using DateTime for date-related fields
    private String name;
    private String title;
    private String description;
    private String topic;
    private String objectiveInOneLine;
    private String objectiveFullDescription;
    private String motivation;
    private Double duration;
    private boolean isActive;
    private Integer practicedDays; // Added field for practicedDays

    // Constructor using the Builder
    private StudyObjectiveProperties(Builder builder) {
        this.id = builder.id;
        this.priority = builder.priority;
        this.dateTime = builder.dateTime; // date-time fields grouped
        initializeFields(builder); // Initialize fields using a helper method
    }

    private void initializeFields(Builder builder) {
        this.name = builder.name;
        this.title = builder.title;
        this.description = builder.description;
        this.topic = builder.topic;
        this.objectiveInOneLine = builder.objectiveInOneLine;
        this.objectiveFullDescription = builder.objectiveFullDescription;
        this.motivation = builder.motivation;
        this.duration = builder.duration;
        this.isActive = builder.isActive;
        this.practicedDays = builder.practicedDays;
    }

    // Business logic example: Check if the objective is complete
    public boolean isComplete() {
        return !this.objectiveInOneLine.isEmpty() && !this.objectiveFullDescription.isEmpty() && this.isActive;
    }

    // Checks if the objective is active and has complete description
    public boolean isActiveAndComplete() {
        return isActive && !objectiveInOneLine.isEmpty() && !objectiveFullDescription.isEmpty();
    }

    // Returns a summary of the objective
    public String getSummary() {
        return String.format("Objective: %s, Priority: %d, Duration: %.2f", objectiveInOneLine, priority, duration);
    }

    // Getters
    public Integer getId() { return id; }
    public Integer getPriority() { return priority; }
    public String getName() { return name; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getTopic() { return topic; }
    public String getObjectiveInOneLine() { return objectiveInOneLine; }
    public String getObjectiveFullDescription() { return objectiveFullDescription; }
    public String getMotivation() { return motivation; }
    public Double getDuration() { return duration; }
    public boolean isActive() { return isActive; }

    // Getter for DateTime (Access to day, month, year)
    public DateTime getDateTime() { return dateTime; }

    // Getters for day, month, year
    public int getDay() { return dateTime.getDay(); }
    public int getMonth() { return dateTime.getMonth(); }
    public int getYear() { return dateTime.getYear(); }

    public Integer getPracticedDays() { return practicedDays; } // Getter for practicedDays

    // Inner class to represent Date-related fields
    public static class DateTime {
        private int day;
        private int month;
        private int year;

        public DateTime(int day, int month, int year) {
            this.day = day;
            this.month = month;
            this.year = year;
        }

        // Getters
        public int getDay() { return day; }
        public int getMonth() { return month; }
        public int getYear() { return year; }
    }

    // Builder class to construct StudyObjectiveProperties
    public static class Builder {
        private Integer id;
        private Integer priority;
        private DateTime dateTime; // DateTime object for date-related fields
        private String name;
        private String title;
        private String description;
        private String topic;
        private String objectiveInOneLine;
        private String objectiveFullDescription;
        private String motivation;
        private Double duration;
        private boolean isActive;
        private Integer practicedDays; // Missing field for practicedDays

        // Builders for DateTime fields
        public Builder withDateTime(int day, int month, int year) {
            this.dateTime = new DateTime(day, month, year);
            return this;
        }

        // Setters for other fields
        public Builder withId(Integer id) { this.id = id; return this; }
        public Builder withPriority(Integer priority) { this.priority = priority; return this; }
        public Builder withPracticedDays(Integer practicedDays) { this.practicedDays = practicedDays; return this; } // Add this method
        public Builder withName(String name) { this.name = name; return this; }
        public Builder withTitle(String title) { this.title = title; return this; }
        public Builder withDescription(String description) { this.description = description; return this; }
        public Builder withTopic(String topic) { this.topic = topic; return this; }
        public Builder withObjectiveInOneLine(String objectiveInOneLine) { this.objectiveInOneLine = objectiveInOneLine; return this; }
        public Builder withObjectiveFullDescription(String objectiveFullDescription) { this.objectiveFullDescription = objectiveFullDescription; return this; }
        public Builder withMotivation(String motivation) { this.motivation = motivation; return this; }
        public Builder withDuration(Double duration) { this.duration = duration; return this; }
        public Builder withIsActive(boolean isActive) { this.isActive = isActive; return this; }

        // Final build method to return the object
        public StudyObjectiveProperties build() {
            return new StudyObjectiveProperties(this);
        }
    }
}