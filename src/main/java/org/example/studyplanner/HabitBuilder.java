package org.example.studyplanner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class HabitBuilder {
    private String name;
    private String motivation;
    private int dailyHours;
    private int dailyMinutes;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int seconds;
    private boolean concluded;
    public HabitBuilder withName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
        return this;
    }
    public HabitBuilder withMotivation(String motivation) {
        if (motivation == null || motivation.trim().isEmpty()) {
            throw new IllegalArgumentException("Motivation cannot be empty");
        }
        this.motivation = motivation;
        return this;
    }
    public HabitBuilder withDailyTime(int hours, int minutes) {
        if (hours < 0 || hours > 23) throw new IllegalArgumentException("Invalid hours");
        if (minutes < 0 || minutes > 59) throw new IllegalArgumentException("Invalid minutes");
        this.dailyHours = hours;
        this.dailyMinutes = minutes;
        return this;
    }
    public HabitBuilder withStartDate(int year, int month, int day) {
        LocalDate.of(year, month, day); // validates date
        this.year = year;
        this.month = month;
        this.day = day;
        return this;
    }
    public HabitBuilder withStartTime(int hour, int minute, int seconds) {
        LocalTime.of(hour, minute, seconds); // validates time
        this.hour = hour;
        this.minute = minute;
        this.seconds = seconds;
        return this;
    }
    public HabitBuilder setConcluded(boolean concluded) {
        this.concluded = concluded;
        return this;
    }
    public Habit build(int id) {
        if (name == null || motivation == null) {
            throw new IllegalStateException("Name and motivation are required");
        }
        LocalTime dailyTime = LocalTime.of(dailyHours, dailyMinutes);
        LocalDateTime startDate = LocalDateTime.of(year, month, day, hour, minute, seconds);
        return new Habit(name, motivation, dailyTime, id, startDate, concluded);
    }
}