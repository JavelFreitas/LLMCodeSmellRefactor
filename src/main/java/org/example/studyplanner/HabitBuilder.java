package org.example.studyplanner;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class HabitBuilder {
    private String name;
    private String motivation;
    private LocalTime dedication = LocalTime.of(0, 0);
    private LocalDateTime startDate = LocalDateTime.now();
    private Boolean isConcluded = false;
    private Integer id;

    public HabitBuilder(String name) {
        this.name = name;
    }

    public HabitBuilder withMotivation(String motivation) {
        this.motivation = motivation;
        return this;
    }

    public HabitBuilder withDailyDedication(int hours, int minutes) {
        this.dedication = LocalTime.of(hours, minutes);
        return this;
    }

    public HabitBuilder withStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public HabitBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public HabitBuilder isConcluded(Boolean concluded) {
        this.isConcluded = concluded;
        return this;
    }

    public Habit build() {
        return new Habit(name, motivation, dedication, id, startDate, isConcluded);
    }
}