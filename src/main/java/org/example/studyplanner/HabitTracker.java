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

    private HabitTracker(){
        this.habits = new ArrayList<>();
        this.tracker = new HashMap<>();
        this.nextId = 1;
    }

    public static class HabitBuilder {
        private String name;
        private String motivation;
        private Integer dailyMinutesDedication = 0;
        private Integer dailyHoursDedication = 0;
        private Integer year;
        private Integer month;
        private Integer day;
        private Integer hour;
        private Integer minute;
        private Integer seconds;
        private Boolean isConcluded = false;

        public HabitBuilder(String name) {
            this.name = name;
        }

        public HabitBuilder motivation(String motivation) {
            this.motivation = motivation;
            return this;
        }

        public HabitBuilder dailyDedication(int hours, int minutes) {
            this.dailyHoursDedication = hours;
            this.dailyMinutesDedication = minutes;
            return this;
        }

        public HabitBuilder startDate(int year, int month, int day, int hour, int minute, int seconds) {
            this.year = year;
            this.month = month;
            this.day = day;
            this.hour = hour;
            this.minute = minute;
            this.seconds = seconds;
            return this;
        }

        public HabitBuilder concluded(boolean concluded) {
            this.isConcluded = concluded;
            return this;
        }
    }

    public String habitDateViewAll() {
        StringBuilder response = new StringBuilder();
        for(Habit habit : habits) {
            response.append("[ Habit: ")
                    .append(habit.getName())
                    .append(". Records: ");
            List<LocalDateTime> records = getHabitRecords(habit.getId());
            for(LocalDateTime record : records) {
                response.append(formatHabitDate(record)).append(", ");
            }
            response.append("]");
        }
        return response.toString();
    }

    @Override
    public String toString() {
        StringBuilder response = new StringBuilder();
        for (Habit habit : habits) {
            response.append(habit.toString()).append(", ");
        }
        return "Habits: " + response.toString();
    }

    public Habit getHabitById(Integer id){
        return this.habits.stream()
                .filter(habit -> Objects.equals(habit.getId(), id))
                .findFirst().orElse(null);
    }

    public List<Habit> getHabits() {
        return this.habits;
    }

    public String formatHabitDate(LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return date.format(formatter);
    }

    public List<Integer> getTrackerKeys(){
        return this.tracker.keySet().stream().toList();
    }

    public int addHabit(HabitBuilder builder) {
        LocalTime lt = LocalTime.of(builder.dailyHoursDedication, builder.dailyMinutesDedication);
        LocalDateTime startDate = LocalDateTime.of(
                builder.year, builder.month, builder.day,
                builder.hour, builder.minute, builder.seconds
        );
        Habit habit = new Habit(
                builder.name,
                builder.motivation,
                lt,
                this.nextId,
                startDate,
                builder.isConcluded
        );
        this.habits.add(habit);
        int response = nextId;
        this.tracker.put(nextId, new ArrayList<>());
        this.nextId++;
        return response;
    }

    public int handleAddHabitAdapter(List<String> stringProperties, List<Integer> intProperties, boolean isConcluded) {
        HabitBuilder builder = new HabitBuilder(stringProperties.get(0))
                .motivation(stringProperties.get(1))
                .dailyDedication(intProperties.get(1), intProperties.get(0))
                .startDate(
                        intProperties.get(2),
                        intProperties.get(3),
                        intProperties.get(4),
                        intProperties.get(5),
                        intProperties.get(6),
                        intProperties.get(7)
                )
                .concluded(isConcluded);

        return addHabit(builder);
    }


    public int addHabit(String name, String motivation) {

        Habit habit = new Habit(name, motivation, this.nextId);
        this.habits.add(habit);
        int response = nextId;
        this.tracker.put(nextId, new ArrayList<>());
        this.nextId++;
        return response;
    }

    public void addHabitRecord(Integer id){
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

    public List<String> searchInHabits(String search){
        List<String> habits = new ArrayList<>();
        for (Habit habit : this.habits) {
            if (habit.getName().toLowerCase().contains(search.toLowerCase()) || habit.getMotivation().toLowerCase().contains(search.toLowerCase())) {
                habits.add(habit.toString());
            }
        }
        return habits;
    }

}
