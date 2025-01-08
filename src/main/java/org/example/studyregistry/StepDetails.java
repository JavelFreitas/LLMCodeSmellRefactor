package org.example.studyregistry;

import java.time.LocalDateTime;


public record StepDetails(
        StudyStepsInfo stepsInfo,
        StudyObjectiveInfo objectiveInfo,
        StudyTimeInfo timeInfo
) {
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

        public Builder firstStep(String val) { firstStep = val; return this; }
        public Builder resetStudyMechanism(String val) { resetStudyMechanism = val; return this; }
        public Builder consistentStep(String val) { consistentStep = val; return this; }
        public Builder seasonalSteps(String val) { seasonalSteps = val; return this; }
        public Builder basicSteps(String val) { basicSteps = val; return this; }
        public Builder mainObjectiveTitle(String val) { mainObjectiveTitle = val; return this; }
        public Builder mainGoalTitle(String val) { mainGoalTitle = val; return this; }
        public Builder mainMaterialTopic(String val) { mainMaterialTopic = val; return this; }
        public Builder mainTask(String val) { mainTask = val; return this; }
        public Builder numberOfSteps(Integer val) { numberOfSteps = val; return this; }
        public Builder isImportant(boolean val) { isImportant = val; return this; }
        public Builder startDate(LocalDateTime val) { startDate = val; return this; }
        public Builder endDate(LocalDateTime val) { endDate = val; return this; }

        public StepDetails build() {
            return new StepDetails(
                    new StudyStepsInfo(firstStep, resetStudyMechanism, consistentStep, seasonalSteps, basicSteps),
                    new StudyObjectiveInfo(mainObjectiveTitle, mainGoalTitle, mainMaterialTopic, mainTask, numberOfSteps, isImportant),
                    new StudyTimeInfo(startDate, endDate)
            );
        }
    }

    // Convenience methods to access nested properties
    public String getFirstStep() { return stepsInfo.firstStep(); }
    public String getResetStudyMechanism() { return stepsInfo.resetStudyMechanism(); }
    public String getConsistentStep() { return stepsInfo.consistentStep(); }
    public String getSeasonalSteps() { return stepsInfo.seasonalSteps(); }
    public String getBasicSteps() { return stepsInfo.basicSteps(); }
    public String getMainObjectiveTitle() { return objectiveInfo.mainObjectiveTitle(); }
    public String getMainGoalTitle() { return objectiveInfo.mainGoalTitle(); }
    public String getMainMaterialTopic() { return objectiveInfo.mainMaterialTopic(); }
    public String getMainTask() { return objectiveInfo.mainTask(); }
    public Integer getNumberOfSteps() { return objectiveInfo.numberOfSteps(); }
    public boolean isImportant() { return objectiveInfo.isImportant(); }
    public LocalDateTime getStartDate() { return timeInfo.startDate(); }
    public LocalDateTime getEndDate() { return timeInfo.endDate(); }
}