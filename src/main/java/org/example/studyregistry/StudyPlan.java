package org.example.studyregistry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudyPlan extends Registry {
    private StudyObjective objective;
    private List<String> steps;

    public StudyPlan(String planName, StudyObjective objective, List<StudyMaterial> materials) {
        this.name = planName;
        this.objective = objective;
        this.steps = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Plan: " + name + ",\nObjective: " + objective.getDescription() + ",\nSteps: "
                + String.join(", ", steps);
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

    public void addSingleStep(String toAdd) {
        steps.add(toAdd);
    }

    public void assignSteps(StepProperties properties) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        this.steps = new ArrayList<>(Arrays.asList(
                properties.getFirstStep(),
                properties.getResetStudyMechanism(),
                properties.getConsistentStep(),
                properties.getSeasonalSteps(),
                properties.getBasicSteps(),
                "Number of steps: " + properties.getNumberOfSteps().toString(),
                "Is it important to you? " + properties.isImportant(),
                properties.getStartDate().format(formatter),
                properties.getEndDate().format(formatter),
                properties.getMainObjectiveTitle(),
                properties.getMainGoalTitle(),
                properties.getMainMaterialTopic(),
                properties.getMainTask()));
    }

    public void handleAssignSteps(List<String> stringProperties, Integer numberOfSteps, boolean isImportant,
            LocalDateTime startDate, LocalDateTime endDate) {
        StepProperties properties = new StepProperties.Builder()
                .firstStep(stringProperties.get(0))
                .resetStudyMechanism(stringProperties.get(1))
                .consistentStep(stringProperties.get(2))
                .seasonalSteps(stringProperties.get(3))
                .basicSteps(stringProperties.get(4))
                .mainObjectiveTitle(stringProperties.get(5))
                .mainGoalTitle(stringProperties.get(6))
                .mainMaterialTopic(stringProperties.get(7))
                .mainTask(stringProperties.get(8))
                .numberOfSteps(numberOfSteps)
                .isImportant(isImportant)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        assignSteps(properties);
    }

}
