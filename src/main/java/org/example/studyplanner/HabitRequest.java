package org.example.studyplanner;

import java.time.LocalDateTime;

public class HabitRequest {
    private final String name;
    private final String motivation;
    private final HabitDetails details;

    public static class HabitDetails {
        private Integer dailyMinutesDedication = 0;
        private Integer dailyHoursDedication = 0;
        private LocalDateTime startDate = LocalDateTime.now();
        private Boolean isConcluded = false;

        public HabitDetails setDailyDedication(int hours, int minutes) {
            this.dailyHoursDedication = hours;
            this.dailyMinutesDedication = minutes;
            return this;
        }

        public HabitDetails setStartDate(LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public HabitDetails setIsConcluded(boolean concluded) {
            this.isConcluded = concluded;
            return this;
        }

        // Add getters
        public Integer getDailyMinutesDedication() { return dailyMinutesDedication; }
        public Integer getDailyHoursDedication() { return dailyHoursDedication; }
        public LocalDateTime getStartDate() { return startDate; }
        public Boolean getIsConcluded() { return isConcluded; }
    }

    public HabitRequest(String name, String motivation) {
        this.name = name;
        this.motivation = motivation;
        this.details = new HabitDetails();
    }

    public HabitRequest(String name, String motivation, HabitDetails details) {
        this.name = name;
        this.motivation = motivation;
        this.details = details;
    }

    public String getName() { return name; }
    public String getMotivation() { return motivation; }
    public HabitDetails getDetails() { return details; }
}
