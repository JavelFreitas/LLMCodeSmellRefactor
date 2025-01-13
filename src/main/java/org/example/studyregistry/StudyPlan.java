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

    public void assignSteps(AssignStepsBuilder builder) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        this.steps = new ArrayList<>(Arrays.asList(
                builder.firstStep,
                builder.resetStudyMechanism,
                builder.consistentStep,
                builder.seasonalSteps,
                builder.basicSteps,
                "Number of steps: " + builder.numberOfSteps,
                "Is it important to you? " + builder.isImportant,
                builder.startDate.format(formatter),
                builder.endDate.format(formatter),
                builder.mainObjectiveTitle,
                builder.mainGoalTitle,
                builder.mainMaterialTopic,
                builder.mainTask
        ));
    }

    public void handleAssignSteps(AssignStepsBuilder builder) {
        assignSteps(builder);
    }

    public static class AssignStepsBuilder {
        private String firstStep;
        private String resetStudyMechanism;
        private String consistentStep;
        private String seasonalSteps;
        private String basicSteps;
        private String mainObjectiveTitle;
        private String mainGoalTitle;
        private String mainMaterialTopic;
        private String mainTask;
        private Integer numberOfSteps;
        private boolean isImportant;
        private LocalDateTime startDate;
        private LocalDateTime endDate;

        public AssignStepsBuilder setFirstStep(String firstStep) {
            this.firstStep = firstStep;
            return this;
        }

        public AssignStepsBuilder setResetStudyMechanism(String resetStudyMechanism) {
            this.resetStudyMechanism = resetStudyMechanism;
            return this;
        }

        public AssignStepsBuilder setConsistentStep(String consistentStep) {
            this.consistentStep = consistentStep;
            return this;
        }

        public AssignStepsBuilder setSeasonalSteps(String seasonalSteps) {
            this.seasonalSteps = seasonalSteps;
            return this;
        }

        public AssignStepsBuilder setBasicSteps(String basicSteps) {
            this.basicSteps = basicSteps;
            return this;
        }

        public AssignStepsBuilder setMainObjectiveTitle(String mainObjectiveTitle) {
            this.mainObjectiveTitle = mainObjectiveTitle;
            return this;
        }

        public AssignStepsBuilder setMainGoalTitle(String mainGoalTitle) {
            this.mainGoalTitle = mainGoalTitle;
            return this;
        }

        public AssignStepsBuilder setMainMaterialTopic(String mainMaterialTopic) {
            this.mainMaterialTopic = mainMaterialTopic;
            return this;
        }

        public AssignStepsBuilder setMainTask(String mainTask) {
            this.mainTask = mainTask;
            return this;
        }

        public AssignStepsBuilder setNumberOfSteps(Integer numberOfSteps) {
            this.numberOfSteps = numberOfSteps;
            return this;
        }

        public AssignStepsBuilder setIsImportant(boolean isImportant) {
            this.isImportant = isImportant;
            return this;
        }

        public AssignStepsBuilder setStartDate(LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public AssignStepsBuilder setEndDate(LocalDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public StudyPlan build(String planName, StudyObjective objective, List<StudyMaterial> materials) {
            StudyPlan plan = new StudyPlan(planName, objective, materials);
            plan.assignSteps(this);
            return plan;
        }
    }
}
