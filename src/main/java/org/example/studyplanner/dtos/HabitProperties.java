package org.example.studyplanner.dtos;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

public class HabitProperties {
    private String name;
    private String motivation;
    private int dailyMinutesDedication;
    private int dailyHoursDedication;
    private LocalDateTime startDate;
    private boolean isConcluded;

    // Constructor
    public HabitProperties(String name, String motivation, int dailyMinutesDedication, int dailyHoursDedication, LocalDateTime startDate, boolean isConcluded) {
        validateHabitProperties(name, motivation, dailyMinutesDedication, dailyHoursDedication);
        this.name = name;
        this.motivation = motivation;
        this.dailyMinutesDedication = dailyMinutesDedication;
        this.dailyHoursDedication = dailyHoursDedication;
        this.startDate = startDate;
        this.isConcluded = isConcluded;
    }

    // Validate input data
    private void validateHabitProperties(String name, String motivation, int dailyMinutesDedication, int dailyHoursDedication) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Habit name cannot be null or empty.");
        }
        if (motivation == null || motivation.isEmpty()) {
            throw new IllegalArgumentException("Motivation cannot be null or empty.");
        }
        if (dailyMinutesDedication < 0 || dailyHoursDedication < 0) {
            throw new IllegalArgumentException("Dedication times cannot be negative.");
        }
    }

    // Calculate total dedication time in minutes
    public int getTotalDedicationMinutes() {
        return (dailyHoursDedication * 60) + dailyMinutesDedication;
    }

    // Calculate total dedication in hours
    public double getTotalDedicationHours() {
        return dailyHoursDedication + (dailyMinutesDedication / 60.0);
    }

    // Check if the habit is achievable based on its dedication time
    public boolean isAchievable() {
        // For example, if the total dedication time is greater than 4 hours, it's considered difficult
        return getTotalDedicationHours() <= 4;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public String getMotivation() {
        return motivation;
    }

    public int getDailyMinutesDedication() {
        return dailyMinutesDedication;
    }

    public int getDailyHoursDedication() {
        return dailyHoursDedication;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public boolean isConcluded() {
        return isConcluded;
    }

    // A method that outputs a summary of the habit
    public String getHabitSummary() {
        return "Habit: " + name + "\nMotivation: " + motivation + "\nDedication: " + getTotalDedicationMinutes() + " minutes per day\nAchievable: " + (isAchievable() ? "Yes" : "No");
    }
}
