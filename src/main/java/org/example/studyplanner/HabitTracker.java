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

    public int addHabit(HabitParameters params) {
        LocalTime lt = LocalTime.of(params.getDailyHoursDedication(), params.getDailyMinutesDedication());
        LocalDateTime startDate = LocalDateTime.of(params.getYear(), params.getMonth(), params.getDay(), params.getHour(), params.getMinute(), params.getSeconds());
        Habit habit = new Habit(params.getName(), params.getMotivation(), lt, this.nextId, startDate, params.getIsConcluded());
        this.habits.add(habit);
        int response = nextId;
        this.tracker.put(nextId, new ArrayList<>());
        this.nextId++;
        return response;
    }

     public int handleAddHabitAdapter(List<String> stringProperties, List<Integer> intProperties, boolean isConcluded){
        HabitParameters params = new HabitParameters(
            stringProperties.get(0), stringProperties.get(1),
            intProperties.get(0), intProperties.get(1),
            intProperties.get(2), intProperties.get(3),
            intProperties.get(4), intProperties.get(5),
            intProperties.get(6), intProperties.get(7),
            isConcluded
        );
        return addHabit(params);
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

    // HabitTracker.java
// Adicionar novo método
    public String getFormattedTimelineView() {
        StringBuilder response = new StringBuilder();
        for(Habit habit : habits){
            response.append("[ Habit: ")
                    .append(habit.getName())
                    .append(". Records: ");
            List<LocalDateTime> records = getHabitRecords(habit.getId());
            for(LocalDateTime record : records){
                response.append(formatHabitDate(record)).append(", ");
            }
            response.append("]");
        }
        return response.toString();
    }

    public static class HabitParameters {
        private String name;
        private String motivation;
        private Integer dailyMinutesDedication;
        private Integer dailyHoursDedication;
        private Integer year;
        private Integer month;
        private Integer day;
        private Integer hour;
        private Integer minute;
        private Integer seconds;
        private Boolean isConcluded;

        // Constructor
        public HabitParameters(String name, String motivation, Integer dailyMinutesDedication, Integer dailyHoursDedication, Integer year, Integer month, Integer day, Integer hour, Integer minute, Integer seconds, Boolean isConcluded) {
            this.name = name;
            this.motivation = motivation;
            this.dailyMinutesDedication = dailyMinutesDedication;
            this.dailyHoursDedication = dailyHoursDedication;
            this.year = year;
            this.month = month;
            this.day = day;
            this.hour = hour;
            this.minute = minute;
            this.seconds = seconds;
            this.isConcluded = isConcluded;
        }

        // Getters
        public String getName() { return name; }
        public String getMotivation() { return motivation; }
        public Integer getDailyMinutesDedication() { return dailyMinutesDedication; }
        public Integer getDailyHoursDedication() { return dailyHoursDedication; }
        public Integer getYear() { return year; }
        public Integer getMonth() { return month; }
        public Integer getDay() { return day; }
        public Integer getHour() { return hour; }
        public Integer getMinute() { return minute; }
        public Integer getSeconds() { return seconds; }
        public Boolean getIsConcluded() { return isConcluded; }
    }

}
