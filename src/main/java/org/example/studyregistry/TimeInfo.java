package org.example.studyregistry;

import java.time.LocalDate;
import java.time.Period;

public class TimeInfo {
    private Integer practicedDays;
    private int day;
    private int month;
    private int year;
    private Double duration;

    public TimeInfo(Integer practicedDays, int day, int month, int year, Double duration) {
        validateDate(day, month, year);
        validateDuration(duration);
        this.practicedDays = practicedDays;
        this.day = day;
        this.month = month;
        this.year = year;
        this.duration = duration;
    }

    private void validateDate(int day, int month, int year) {
        try {
            LocalDate.of(year, month, day);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date");
        }
    }

    private void validateDuration(Double duration) {
        if (duration != null && duration <= 0) {
            throw new IllegalArgumentException("Duration must be positive");
        }
    }

    public long getDaysSinceStart() {
        LocalDate start = LocalDate.of(year, month, day);
        LocalDate now = LocalDate.now();
        return Period.between(start, now).getDays();
    }

    public double getAverageDailyPractice() {
        if (practicedDays == null || practicedDays == 0) return 0;
        return duration / practicedDays;
    }

    public String formatDateInfo() {
        return String.format("%02d/%02d/%d (Practiced: %d days, Duration: %.1f hours)",
                day, month, year,
                practicedDays != null ? practicedDays : 0,
                duration != null ? duration : 0.0);
    }

    public boolean isActive() {
        return getDaysSinceStart() <= 30; // Considera ativo se iniciado nos últimos 30 dias
    }

    // Manter apenas getters essenciais
    public LocalDate getDate() {
        return LocalDate.of(year, month, day);
    }
    public Double getDuration() { return duration; }

    public Integer getPracticedDays() { return practicedDays; }
    public int getYear() { return year; }
    public int getMonth() { return month; }
    public int getDay() { return day; }
}
