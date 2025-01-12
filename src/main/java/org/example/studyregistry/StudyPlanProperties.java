package org.example.studyregistry;

public class StudyPlanProperties {
    private String firstStep;
    private String resetStudyMechanism;
    private String consistentStep;
    private String seasonalSteps;
    private String basicSteps;
    private String mainObjectiveTitle;
    private String mainGoalTitle;
    private String mainMaterialTopic;
    private String mainTask;

    public StudyPlanProperties(String firstStep, String resetStudyMechanism, String consistentStep, String seasonalSteps, String basicSteps, String mainObjectiveTitle, String mainGoalTitle, String mainMaterialTopic, String mainTask) {
        this.firstStep = firstStep;
        this.resetStudyMechanism = resetStudyMechanism;
        this.consistentStep = consistentStep;
        this.seasonalSteps = seasonalSteps;
        this.basicSteps = basicSteps;
        this.mainObjectiveTitle = mainObjectiveTitle;
        this.mainGoalTitle = mainGoalTitle;
        this.mainMaterialTopic = mainMaterialTopic;
        this.mainTask = mainTask;
    }

    public String getFirstStep() {
        return firstStep;
    }

    public void setFirstStep(String firstStep) {
        this.firstStep = firstStep;
    }

    public String getResetStudyMechanism() {
        return resetStudyMechanism;
    }

    public void setResetStudyMechanism(String resetStudyMechanism) {
        this.resetStudyMechanism = resetStudyMechanism;
    }

    public String getConsistentStep() {
        return consistentStep;
    }

    public void setConsistentStep(String consistentStep) {
        this.consistentStep = consistentStep;
    }

    public String getSeasonalSteps() {
        return seasonalSteps;
    }

    public void setSeasonalSteps(String seasonalSteps) {
        this.seasonalSteps = seasonalSteps;
    }

    public String getBasicSteps() {
        return basicSteps;
    }

    public void setBasicSteps(String basicSteps) {
        this.basicSteps = basicSteps;
    }

    public String getMainObjectiveTitle() {
        return mainObjectiveTitle;
    }

    public void setMainObjectiveTitle(String mainObjectiveTitle) {
        this.mainObjectiveTitle = mainObjectiveTitle;
    }

    public String getMainGoalTitle() {
        return mainGoalTitle;
    }

    public void setMainGoalTitle(String mainGoalTitle) {
        this.mainGoalTitle = mainGoalTitle;
    }

    public String getMainMaterialTopic() {
        return mainMaterialTopic;
    }

    public void setMainMaterialTopic(String mainMaterialTopic) {
        this.mainMaterialTopic = mainMaterialTopic;
    }

    public String getMainTask() {
        return mainTask;
    }

    public void setMainTask(String mainTask) {
        this.mainTask = mainTask;
    }
}
