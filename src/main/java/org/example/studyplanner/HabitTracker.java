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

    public String getHabitDateViewAll() {
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



    public int handleAddHabitAdapter(List<String> stringProperties, List<Integer> intProperties, boolean isConcluded) {
        HabitRequest.HabitDetails details = new HabitRequest.HabitDetails()
                .setDailyDedication(intProperties.get(1), intProperties.get(0))
                .setStartDate(LocalDateTime.of(
                        intProperties.get(2), // year
                        intProperties.get(3), // month
                        intProperties.get(4), // day
                        intProperties.get(5), // hour
                        intProperties.get(6), // minute
                        intProperties.get(7)  // seconds
                ))
                .setIsConcluded(isConcluded);

        HabitRequest request = new HabitRequest(
                stringProperties.get(0),  // name
                stringProperties.get(1),  // motivation
                details
        );

        return addHabit(request);
    }


    // Update the addHabit methods in HabitTracker class

    public int addHabit(String name, String motivation) {
        HabitRequest request = new HabitRequest(name, motivation);
        return addHabit(request);
    }

    public int addHabit(HabitRequest request) {
        Habit habit = new HabitBuilder(request.getName())
                .withMotivation(request.getMotivation())
                .withDailyDedication(
                        request.getDetails().getDailyHoursDedication(),
                        request.getDetails().getDailyMinutesDedication())
                .withStartDate(request.getDetails().getStartDate())
                .withId(this.nextId)
                .isConcluded(request.getDetails().getIsConcluded())
                .build();

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
