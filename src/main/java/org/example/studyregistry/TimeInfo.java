package org.example.studyregistry;

import java.time.LocalDateTime;

public class TimeInfo {
    private Integer practicedDays;
    private LocalDateTime startDate;
    private Double duration;

    public TimeInfo(Integer practicedDays, int day, int month, int year, Double duration) {
        this.practicedDays = practicedDays;
        this.startDate = LocalDateTime.of(year, month, day, 0, 0);
        this.duration = duration;
    }

    // Getters
    public Integer getPracticedDays() { return practicedDays; }
    public LocalDateTime getStartDate() { return startDate; }
    public Double getDuration() { return duration; }
}
