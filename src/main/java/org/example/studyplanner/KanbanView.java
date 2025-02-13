package org.example.studyplanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KanbanView {
    public enum State {
        TODO, DOING, DONE;
    }

    HabitTracker habitTracker = null;
    TodoTracker todoTracker = null;
    Map<State, List<PlannerMaterial>> kanban = null;

    public KanbanView(HabitTracker habitTracker, TodoTracker todoTracker) {
        this.habitTracker = habitTracker;
        this.todoTracker = todoTracker;
        this.kanban = new HashMap<>();
        this.kanban.put(State.TODO, new ArrayList<>());
        this.kanban.put(State.DOING, new ArrayList<>());
        this.kanban.put(State.DONE, new ArrayList<>());
    }

    public List<PlannerMaterial> getKanbanByState(State state) {
        return kanban.get(state);
    }

    public void addHabitToKanban(State state, Integer id) throws Exception {
        try {
            Habit toAdd = this.habitTracker.getHabitById(id);
            if (toAdd == null) {
                throw new Exception("Habit not found with id: " + id);
            }
            kanban.get(state).add(toAdd);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void addToDoToKanban(State state, Integer id) throws Exception {
        try {
            ToDo toAdd = this.todoTracker.getToDoById(id);
            if (toAdd == null) {
                throw new Exception("ToDo not found with id: " + id);
            }
            kanban.get(state).add(toAdd);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void removeHabitFromKanban(State state, Integer id) throws Exception {
        try {
            Habit toRemove = this.habitTracker.getHabitById(id);
            if (toRemove == null) {
                throw new Exception("No habit found with id: " + id);
            }
            kanban.get(state).remove(toRemove);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void removeToDoFromKanban(State state, Integer id) throws Exception {
        try {
            ToDo toRemove = this.todoTracker.getToDoById(id);
            if (toRemove == null) {
                throw new Exception("No todo found with id: " + id);
            }
            kanban.get(state).remove(toRemove);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public String kanbanView() throws Exception {
        if (kanban.isEmpty()) {
            throw new Exception("No material found");
        }

        return "[" + buildKanbanSections() + "]";
    }

    private String buildKanbanSections() {
        return Arrays.stream(State.values()) // Correct way to stream the State enum values
                .map(this::buildKanbanSectionForState) // Method reference is correct now
                .collect(Collectors.joining());
    }

    private String buildKanbanSectionForState(State state) { // New helper method
        return buildKanbanSection(state, getStateName(state));
    }

    private String getStateName(State state) {
        switch (state) {
            case TODO:
                return "ToDo";
            case DOING:
                return "In Progress";
            case DONE:
                return "Completed";
            default:
                return ""; // Or throw an exception for unknown states
        }
    }

    private String buildKanbanSection(State state, String sectionName) {
        return System.lineSeparator() + "Material " + sectionName + ":" + System.lineSeparator() +
                getKanbanMaterialsAsString(state);
    }

    private String getKanbanMaterialsAsString(State state) {
        if (kanban.get(state).isEmpty()) {
            return "No material found";
        } else {
            return kanban.get(state).stream()
                    .map(PlannerMaterial::toString)
                    .collect(Collectors.joining(", "));
        }
    }
}