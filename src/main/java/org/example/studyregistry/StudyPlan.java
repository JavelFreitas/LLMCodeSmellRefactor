package org.example.studyregistry;

import org.example.studyregistry.dto.StepAssignment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StudyPlan extends Registry{
    private StudyObjective objective;
    private List<String> steps;

    public StudyPlan(String planName, StudyObjective objective, List<StudyMaterial> materials) {
        this.name = planName;
        this.objective = objective;
        this.steps = new ArrayList<>();
    }

    @Override
    public String toString(){
        return "Plan: " + name + ",\nObjective: " + objective.getDescription() + ",\nSteps: " + String.join(", ", steps);
    }

    public List<String> getSteps() {
        return steps;
    }

    public StudyObjective getObjective() {
        return objective;
    }

    public void assignObjective(StudyObjective objective) {
        this.objective = objective;
    }

    public void addSingleStep(String toAdd){
        steps.add(toAdd);
    }

    public void handleAssignSteps(List<String> stringProperties, Integer numberOfSteps, boolean isImportant, LocalDateTime startDate, LocalDateTime endDate) {
        StepAssignment stepAssignment = new StepAssignment(stringProperties, numberOfSteps, isImportant, startDate, endDate);
        assignSteps(stepAssignment);
    }
    public void assignSteps(StepAssignment stepAssignment) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        this.steps = new ArrayList<>(stepAssignment.steps());
        this.steps.add("Number of steps: " + stepAssignment.numberOfSteps());
        this.steps.add("Is it important to you? " + stepAssignment.isImportant());
        this.steps.add(stepAssignment.startDate().format(formatter));
        this.steps.add(stepAssignment.endDate().format(formatter));
    }

}
