package org.example.studyplanner;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class HabitDetails {
    private String name;
    private String motivation;
    private LocalTime duration;
    private LocalDateTime startDate;
    private boolean isConcluded;

    public HabitDetails(String name, String motivation, LocalTime duration, LocalDateTime startDate, boolean isConcluded) {
        this.name = name;
        this.motivation = motivation;
        this.duration = duration;
        this.startDate = startDate;
        this.isConcluded = isConcluded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public boolean isConcluded() {
        return isConcluded;
    }

    public void setConcluded(boolean concluded) {
        isConcluded = concluded;
    }
}