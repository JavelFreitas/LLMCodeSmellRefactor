package org.example.studyregistry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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

    public void assignSteps(StudyPlanProperties properties, Integer numberOfSteps, boolean isImportant, LocalDateTime startDate, LocalDateTime endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.steps = new ArrayList<>(Arrays.asList(
                properties.getFirstStep(),
                properties.getResetStudyMechanism(),
                properties.getConsistentStep(),
                properties.getSeasonalSteps(),
                properties.getBasicSteps(),
                "Number of steps: " + numberOfSteps.toString(),
                "Is it important to you? " + isImportant,
                startDate.format(formatter),
                endDate.format(formatter),
                properties.getMainObjectiveTitle(),
                properties.getMainGoalTitle(),
                properties.getMainMaterialTopic(),
                properties.getMainTask()
        ));
    }

    public void handleAssignSteps(StudyPlanProperties properties, Integer numberOfSteps, boolean isImportant, LocalDateTime startDate, LocalDateTime endDate) {
        assignSteps(properties, numberOfSteps, isImportant, startDate, endDate);
    }

}
