package org.example.studyregistry;

import java.time.LocalDateTime;

public class StepProperties {
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

    private StepProperties(Builder builder) {
        validateRequiredFields(builder);
        validateDates(builder);
        validateStepCount(builder);

        initializeStepFields(builder);
        initializeObjectiveFields(builder);
        initializeDateFields(builder);
    }

    private void validateRequiredFields(Builder builder) {
        validateFirstStep(builder);
        validateObjectiveTitle(builder);
        validateMainTask(builder);
    }

    private void validateFirstStep(Builder builder) {
        if (isNullOrEmpty(builder.firstStep)) {
            throw new IllegalArgumentException("First step cannot be empty");
        }
    }

    private void validateObjectiveTitle(Builder builder) {
        if (isNullOrEmpty(builder.mainObjectiveTitle)) {
            throw new IllegalArgumentException("Main objective title cannot be empty");
        }
    }

    private void validateMainTask(Builder builder) {
        if (isNullOrEmpty(builder.mainTask)) {
            throw new IllegalArgumentException("Main task cannot be empty");
        }
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public boolean isStepComplete() {
        return !isNullOrEmpty(firstStep) && numberOfSteps != null && numberOfSteps > 0;
    }

    public boolean hasValidSchedule() {
        return startDate != null && endDate != null && !endDate.isBefore(startDate);
    }

    public boolean isMainTaskPriority() {
        return isImportant && !isNullOrEmpty(mainTask);
    }

    public String getStepSummary() {
        return String.format("Step: %s - Objective: %s - Task: %s",
                firstStep, mainObjectiveTitle, mainTask);
    }

    public boolean containsTopicReference(String topic) {
        return mainMaterialTopic != null &&
                mainMaterialTopic.toLowerCase().contains(topic.toLowerCase());
    }

    public boolean isInDateRange(LocalDateTime checkDate) {
        return (startDate == null || !checkDate.isBefore(startDate)) &&
                (endDate == null || !checkDate.isAfter(endDate));
    }

    private void validateDates(Builder builder) {
        if (builder.startDate != null && builder.endDate != null
                && builder.endDate.isBefore(builder.startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }
    }

    private void validateStepCount(Builder builder) {
        if (builder.numberOfSteps != null && builder.numberOfSteps <= 0) {
            throw new IllegalArgumentException("Number of steps must be positive");
        }
    }

    private void initializeStepFields(Builder builder) {
        this.firstStep = builder.firstStep;
        this.resetStudyMechanism = builder.resetStudyMechanism;
        this.consistentStep = builder.consistentStep;
        this.seasonalSteps = builder.seasonalSteps;
        this.basicSteps = builder.basicSteps;
        this.numberOfSteps = builder.numberOfSteps;
    }

    private void initializeObjectiveFields(Builder builder) {
        this.mainObjectiveTitle = builder.mainObjectiveTitle;
        this.mainGoalTitle = builder.mainGoalTitle;
        this.mainMaterialTopic = builder.mainMaterialTopic;
        this.mainTask = builder.mainTask;
        this.isImportant = builder.isImportant;
    }

    private void initializeDateFields(Builder builder) {
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
    }

    public boolean isValidDateRange() {
        return startDate != null && endDate != null && !endDate.isBefore(startDate);
    }

    public static class Builder {
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

        public Builder firstStep(String firstStep) {
            this.firstStep = firstStep;
            return this;
        }

        public Builder resetStudyMechanism(String resetStudyMechanism) {
            this.resetStudyMechanism = resetStudyMechanism;
            return this;
        }

        public Builder consistentStep(String consistentStep) {
            this.consistentStep = consistentStep;
            return this;
        }

        public Builder seasonalSteps(String seasonalSteps) {
            this.seasonalSteps = seasonalSteps;
            return this;
        }

        public Builder basicSteps(String basicSteps) {
            this.basicSteps = basicSteps;
            return this;
        }

        public Builder mainObjectiveTitle(String mainObjectiveTitle) {
            this.mainObjectiveTitle = mainObjectiveTitle;
            return this;
        }

        public Builder mainGoalTitle(String mainGoalTitle) {
            this.mainGoalTitle = mainGoalTitle;
            return this;
        }

        public Builder mainMaterialTopic(String mainMaterialTopic) {
            this.mainMaterialTopic = mainMaterialTopic;
            return this;
        }

        public Builder mainTask(String mainTask) {
            this.mainTask = mainTask;
            return this;
        }

        public Builder numberOfSteps(Integer numberOfSteps) {
            this.numberOfSteps = numberOfSteps;
            return this;
        }

        public Builder isImportant(boolean isImportant) {
            this.isImportant = isImportant;
            return this;
        }

        public Builder startDate(LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(LocalDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public StepProperties build() {
            return new StepProperties(this);
        }
    }

    // Getters
    public String getFirstStep() {
        return firstStep;
    }

    public String getResetStudyMechanism() {
        return resetStudyMechanism;
    }

    public String getConsistentStep() {
        return consistentStep;
    }

    public String getSeasonalSteps() {
        return seasonalSteps;
    }

    public String getBasicSteps() {
        return basicSteps;
    }

    public String getMainObjectiveTitle() {
        return mainObjectiveTitle;
    }

    public String getMainGoalTitle() {
        return mainGoalTitle;
    }

    public String getMainMaterialTopic() {
        return mainMaterialTopic;
    }

    public String getMainTask() {
        return mainTask;
    }

    public Integer getNumberOfSteps() {
        return numberOfSteps;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}