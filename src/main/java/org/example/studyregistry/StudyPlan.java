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

    public void assignSteps(AssignStepsParameters params) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        this.steps = new ArrayList<>(Arrays.asList(
            params.getFirstStep(),
            params.getResetStudyMechanism(),
            params.getConsistentStep(),
            params.getSeasonalSteps(),
            params.getBasicSteps(),
            "Number of steps: " + params.getNumberOfSteps(),
            "Is it important to you? " + params.isImportant(),
            params.getStartDate().format(formatter),
            params.getEndDate().format(formatter),
            params.getMainObjectiveTitle(),
            params.getMainGoalTitle(),
            params.getMainMaterialTopic(),
            params.getMainTask()
        ));
    }

    
    public void handleAssignSteps(List<String> stringProperties, Integer numberOfSteps, boolean isImportant, LocalDateTime startDate, LocalDateTime endDate){
        if(stringProperties.size() < 9){
            throw new IllegalArgumentException("A lista de propriedades deve conter pelo menos 9 elementos.");
        }

        AssignStepsParameters params = new AssignStepsParameters(
            stringProperties.get(0), // firstStep
            stringProperties.get(1), // resetStudyMechanism
            stringProperties.get(2), // consistentStep
            stringProperties.get(3), // seasonalSteps
            stringProperties.get(4), // basicSteps
            stringProperties.get(5), // mainObjectiveTitle
            stringProperties.get(6), // mainGoalTitle
            stringProperties.get(7), // mainMaterialTopic
            stringProperties.get(8), // mainTask
            numberOfSteps,
            isImportant,
            startDate,
            endDate
        );

        assignSteps(params);
    }

}
