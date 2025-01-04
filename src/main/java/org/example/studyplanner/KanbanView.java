package org.example.studyplanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KanbanView {
    public enum State{
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
        try{
            Habit toAdd = this.habitTracker.getHabitById(id);
            if(toAdd == null){
                throw new Exception("Habit not found with id: " + id);
            }
            kanban.get(state).add(toAdd);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public void addToDoToKanban(State state, Integer id) throws Exception {
        try{
            ToDo toAdd = this.todoTracker.getToDoById(id);
            if(toAdd == null){
                throw new Exception("ToDo not found with id: " + id);
            }
            kanban.get(state).add(toAdd);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public void removeHabitFromKanban(State state, Integer id) throws Exception {
        try{
            Habit toRemove = this.habitTracker.getHabitById(id);
            if(toRemove == null) {
                throw new Exception("No habit found with id: " + id);
            }
            kanban.get(state).remove(toRemove);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    public void removeToDoFromKanban(State state, Integer id) throws Exception {
        try{
            ToDo toRemove = this.todoTracker.getToDoById(id);
            if(toRemove == null) {
                throw new Exception("No todo found with id: " + id);
            }
            kanban.get(state).remove(toRemove);
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public String kanbanView() throws Exception {
        try {
            if (kanban.isEmpty()) {
                throw new Exception("No material found");
            }

            String result = "[ ";
            result += getMaterialsByState(State.TODO, "Material ToDo:") + System.lineSeparator();
            result += getMaterialsByState(State.DOING, "Material in progress:") + System.lineSeparator();
            result += getMaterialsByState(State.DONE, "Material completed:") + System.lineSeparator();
            result += "]";

            return result;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private String getMaterialsByState(State state, String stateName) {
        List<PlannerMaterial> materials = kanban.get(state);
        return formatMaterials(stateName, materials);
    }

    private String formatMaterials(String stateName, List<PlannerMaterial> materials) {
        return stateName + System.lineSeparator() +
                (materials.isEmpty() ? "No material found" :
                        materials.stream().map(Object::toString).collect(Collectors.joining(", ")));
    }

}
