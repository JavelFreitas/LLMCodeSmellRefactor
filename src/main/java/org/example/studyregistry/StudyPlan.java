package org.example.studyregistry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudyPlan extends Registry {
    private StudyObjective objective;
    private List<String> steps;
    private List<StudyMaterial> materials;

    public StudyPlan(String planName, StudyObjective objective, List<StudyMaterial> materials) {
        this.name = planName;
        this.objective = objective;
        this.materials = materials;
        this.steps = new ArrayList<>();
    }

    public void handleAssignSteps(List<String> stringProperties, Integer numberOfSteps,
                                  boolean isImportant, LocalDateTime startDate, LocalDateTime endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        this.steps = new ArrayList<>(Arrays.asList(
                stringProperties.get(0),  // firstStep
                stringProperties.get(1),  // resetStudyMechanism
                stringProperties.get(2),  // consistentStep
                stringProperties.get(3),  // seasonalSteps
                stringProperties.get(4),  // basicSteps
                stringProperties.get(5),  // mainObjectiveTitle
                stringProperties.get(6),  // mainGoalTitle
                stringProperties.get(7),  // mainMaterialTopic
                stringProperties.get(8),  // mainTaskgt
                "Number of steps: " + numberOfSteps.toString(),
                "Is it important to you? " + isImportant,
                startDate.format(formatter),
                endDate.format(formatter)
        ));
    }

    public String getName() {
        return name;
    }

    public StudyObjective getObjective() {
        return objective;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void assignObjective(StudyObjective objective) {
        this.objective = objective;
    }

    public void addSingleStep(String toAdd) {
        steps.add(toAdd);
    }

    @Override
    public String toString() {
        return "Plan: " + name + ",\nObjective: " + objective.getName() + ",\nSteps: " + String.join(", ", steps);
    }
}