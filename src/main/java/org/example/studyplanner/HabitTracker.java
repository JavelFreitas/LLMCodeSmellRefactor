package org.example.studyplanner;

import org.example.studyplanner.dto.HabitProperties;
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
        return this.tracker.keySet().stream().toList();
    }

    public int addHabit(HabitProperties habitProperties) {
        LocalTime lt = LocalTime.of(habitProperties.getDailyHoursDedication(), habitProperties.getDailyMinutesDedication());
        LocalDateTime startDate = habitProperties.getStartDate();
        Habit habit = new Habit(
                habitProperties.getName(),
                habitProperties.getMotivation(),
                lt,
                this.nextId,
                startDate,
                habitProperties.isConcluded() // Corrigido para isConcluded()
        );
        this.habits.add(habit);
        int response = nextId;
        this.tracker.put(nextId, new ArrayList<>());
        this.nextId++;
        return response;
    }

    public int handleAddHabitAdapter(List<String> stringProperties, List<Integer> intProperties, boolean isConcluded) {
        String name = stringProperties.get(0);
        String motivation = stringProperties.get(1);
        int dailyMinutesDedication = intProperties.get(0);
        int dailyHoursDedication = intProperties.get(1);
        LocalDateTime startDate = buildStartDate(intProperties);
        HabitProperties habitProperties = new HabitProperties(name, motivation, dailyMinutesDedication, dailyHoursDedication, startDate, isConcluded);
        return addHabit(habitProperties);
    }

    private LocalDateTime buildStartDate(List<Integer> intProperties) {
        int year = intProperties.get(2);
        int month = intProperties.get(3);
        int day = intProperties.get(4);
        int hour = intProperties.get(5);
        int minute = intProperties.get(6);
        int seconds = intProperties.get(7);
        return LocalDateTime.of(year, month, day, hour, minute, seconds);
    }

    public int addHabit(String name, String motivation) {
        Habit habit = new Habit(name, motivation, this.nextId);
        this.habits.add(habit);
        int response = nextId;
        this.tracker.put(nextId, new ArrayList<>());
        this.nextId++;
        return response;
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
        List<String> habits = new ArrayList<>();
        for (Habit habit : this.habits) {
            if (habit.getName().toLowerCase().contains(search.toLowerCase()) || habit.getMotivation().toLowerCase().contains(search.toLowerCase())) {
                habits.add(habit.toString());
            }
        }
        return habits;
    }
}
