package org.example.studyplanner;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HabitTracker {
    private List<Habit> habits;
    private Map<Integer, List<LocalDateTime>> tracker;
    private Integer nextId;

    private static HabitTracker instance;

    public static HabitTracker getHabitTracker() {
        if (instance == null) {
            instance = new HabitTracker();
        }
        return instance;
    }

    private HabitTracker() {
        this.habits = new ArrayList<>();
        this.tracker = new HashMap<>();
        this.nextId = 1;
    }

    @Override
    public String toString() {
        StringBuilder response = new StringBuilder();
        for (Habit habit : habits) {
            response.append(habit.toString()).append(", ");
        }
        return "Habits: " + response.toString();
    }

    public Habit getHabitById(Integer id) {
        return this.habits.stream()
                .filter(habit -> Objects.equals(habit.getId(), id))
                .findFirst().orElse(null);
    }

    public List<Habit> getHabits() {
        return this.habits;
    }

    public String formatHabitDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return date.format(formatter);
    }

    public List<Integer> getTrackerKeys() {
        return new ArrayList<>(this.tracker.keySet());
    }

    public int addHabit(HabitBuilder builder) {
        Habit habit = builder.build();
        this.habits.add(habit);
        this.tracker.put(habit.getId(), new ArrayList<>());
        return habit.getId();
    }

    public void addHabitRecord(Integer id) {
        tracker.get(id).add(LocalDateTime.now());
    }

    public void toggleConcludeHabit(Integer id) {
        for (Habit habit : this.habits) {
            if (habit.getId().equals(id)) {
                habit.setIsConcluded(!habit.getIsConcluded());
            }
        }
    }

    public void removeHabit(Integer id) {
        this.habits.removeIf(habit -> habit.getId().equals(id));
        this.tracker.remove(id);
    }

    public List<LocalDateTime> getHabitRecords(Integer id) {
        return this.tracker.get(id);
    }

    public List<String> searchInHabits(String search) {
        List<String> result = new ArrayList<>();
        for (Habit habit : this.habits) {
            if (habit.getName().toLowerCase().contains(search.toLowerCase()) ||
                    habit.getMotivation().toLowerCase().contains(search.toLowerCase())) {
                result.add(habit.toString());
            }
        }
        return result;
    }

    public String formatAllHabits() {
        StringBuilder response = new StringBuilder();
        for (Habit habit : habits) {
            response.append(formatHabitWithRecords(habit));
        }
        return response.toString();
    }

    public String formatHabitWithRecords(Habit habit) {
        StringBuilder response = new StringBuilder();
        response.append("[ Habit: ").append(habit.getName()).append(". Records: ");
        List<LocalDateTime> records = getHabitRecords(habit.getId());
        for (LocalDateTime record : records) {
            response.append(formatHabitDate(record)).append(", ");
        }
        response.append("]");
        return response.toString();
    }

    // Nested static Builder class
    public static class HabitBuilder {
        private String name;
        private String motivation;
        private LocalTime dailyDedicationTime;
        private Integer id;
        private LocalDateTime startDate;
        private Boolean isConcluded;

        private Integer nextId;

        public HabitBuilder(Integer nextId) {
            this.nextId = nextId;
        }

        public HabitBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public HabitBuilder withMotivation(String motivation) {
            this.motivation = motivation;
            return this;
        }

        public HabitBuilder withDailyDedicationTime(Integer hours, Integer minutes) {
            this.dailyDedicationTime = LocalTime.of(hours, minutes);
            return this;
        }

        public HabitBuilder withStartDate(Integer year, Integer month, Integer day, Integer hour, Integer minute, Integer second) {
            this.startDate = LocalDateTime.of(year, month, day, hour, minute, second);
            return this;
        }

        public HabitBuilder withIsConcluded(Boolean isConcluded) {
            this.isConcluded = isConcluded;
            return this;
        }

        public Habit build() {
            return new Habit(name, motivation, dailyDedicationTime, nextId, startDate, isConcluded);
        }
    }
}
