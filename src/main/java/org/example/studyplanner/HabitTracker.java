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

    // Singleton para obter a instância de HabitTracker
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

    // Obtém um hábito pelo ID
    public Habit getHabitById(Integer id) {
        return this.habits.stream()
                .filter(habit -> Objects.equals(habit.getId(), id))
                .findFirst()
                .orElse(null);
    }

    // Retorna todos os hábitos
    public List<Habit> getHabits() {
        return this.habits;
    }

    // Formata a data de um hábito
    public String formatHabitDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return date.format(formatter);
    }

    // Retorna as chaves do tracker
    public List<Integer> getTrackerKeys() {
        return this.tracker.keySet().stream().toList();
    }

    // Adiciona um novo hábito com todos os parâmetros
    public int addHabit(String name, String motivation, LocalTime dailyDedication, LocalDateTime startDate, Boolean isConcluded) {
        Habit habit = new Habit(name, motivation, dailyDedication, this.nextId, startDate, isConcluded);
        this.habits.add(habit);
        int response = nextId;
        this.tracker.put(nextId, new ArrayList<>());
        this.nextId++;
        return response;
    }

    // Adiciona um hábito a partir de adaptadores de propriedades
    public int handleAddHabitAdapter(List<String> stringProperties, List<Integer> intProperties, boolean isConcluded) {
        LocalDateTime startDate = LocalDateTime.of(intProperties.get(2), intProperties.get(3), intProperties.get(4),
                intProperties.get(5), intProperties.get(6), intProperties.get(7));
        LocalTime dailyDedication = LocalTime.of(intProperties.get(1), intProperties.get(0)); // Ordem corrigida
        return addHabit(stringProperties.get(0), stringProperties.get(1), dailyDedication, startDate, isConcluded);
    }

    // Adiciona um hábito com apenas nome e motivação
    public int addHabit(String name, String motivation) {
        Habit habit = new Habit(name, motivation, this.nextId);
        this.habits.add(habit);
        int response = nextId;
        this.tracker.put(nextId, new ArrayList<>());
        this.nextId++;
        return response;
    }

    // Adiciona um registro de hábito pelo ID
    public void addHabitRecord(Integer id) {
        tracker.get(id).add(LocalDateTime.now());
    }

    // Alterna a conclusão de um hábito pelo ID
    public void toggleConcludeHabit(Integer id) {
        for (Habit habit : this.habits) {
            if (habit.getId().equals(id)) {
                habit.setIsConcluded(!habit.getIsConcluded());
            }
        }
    }

    // Remove um hábito pelo ID
    public void removeHabit(Integer id) {
        this.habits.removeIf(habit -> habit.getId().equals(id));
        this.tracker.remove(id);
    }

    // Retorna os registros de um hábito pelo ID
    public List<LocalDateTime> getHabitRecords(Integer id) {
        return this.tracker.getOrDefault(id, new ArrayList<>());
    }

    // Realiza uma busca por hábitos com base no nome ou motivação
    public List<String> searchInHabits(String search) {
        String lowerSearch = search.toLowerCase();
        List<String> habits = new ArrayList<>();
        for (Habit habit : this.habits) {
            if (habit.getName().toLowerCase().contains(lowerSearch) || habit.getMotivation().toLowerCase().contains(lowerSearch)) {
                habits.add(habit.toString());
            }
        }
        return habits;
    }

    // Exibe todos os hábitos e seus registros formatados
    public String habitDateViewAll() {
        StringBuilder response = new StringBuilder();
        for (Habit habit : this.habits) {
            response.append("[ Habit: ")
                    .append(habit.getName())
                    .append(". Records: ");
            List<LocalDateTime> records = this.getHabitRecords(habit.getId());
            for (LocalDateTime record : records) {
                response.append(this.formatHabitDate(record)).append(", ");
            }
            response.append("]");
        }
        return response.toString();
    }
    public List<String> handleSearch(String text) {
        return searchInHabits(text); // Delegar a busca ao método já existente
    }
}
