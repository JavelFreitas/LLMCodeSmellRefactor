package org.example.studyregistry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudyPlan extends Registry {
    private StudyObjective objective;
    private List<String> steps;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public StudyPlan(String planName, StudyObjective objective, List<StudyMaterial> materials) {
        this.name = planName;
        this.objective = objective;
        this.steps = new ArrayList<>();
    }

    @Override
    public String toString() {
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

    public void addSingleStep(String toAdd) {
        steps.add(toAdd);
    }

    private List<String> createStepsList(StepDetails details) {
        return Arrays.asList(
                details.getFirstStep(),
                details.getResetStudyMechanism(),
                details.getConsistentStep(),
                details.getSeasonalSteps(),
                details.getBasicSteps(),
                "Number of steps: " + details.getNumberOfSteps(),
                "Is it important to you? " + details.isImportant(),
                details.getStartDate().format(formatter),
                details.getEndDate().format(formatter),
                details.getMainObjectiveTitle(),
                details.getMainGoalTitle(),
                details.getMainMaterialTopic(),
                details.getMainTask()
        );
    }

    public void assignSteps(StepDetails details) {
        this.steps = new ArrayList<>(createStepsList(details));
    }

    public void handleAssignSteps(List<String> stringProperties, Integer numberOfSteps,
                                  boolean isImportant, LocalDateTime startDate, LocalDateTime endDate) {
        StepDetails details = new StepDetails.Builder()
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

        assignSteps(details);
    }
}
