package org.example.studyregistry;

import java.time.LocalDateTime;

public class AssignStepsParameters {
    private final String firstStep;
    private final String resetStudyMechanism;
    private final String consistentStep;
    private final String seasonalSteps;
    private final String basicSteps;
    private final String mainObjectiveTitle;
    private final String mainGoalTitle;
    private final String mainMaterialTopic;
    private final String mainTask;
    private final Integer numberOfSteps;
    private final boolean isImportant;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public AssignStepsParameters(String firstStep, String resetStudyMechanism, String consistentStep, 
                                 String seasonalSteps, String basicSteps, String mainObjectiveTitle, 
                                 String mainGoalTitle, String mainMaterialTopic, String mainTask, 
                                 Integer numberOfSteps, boolean isImportant, 
                                 LocalDateTime startDate, LocalDateTime endDate) {
        if (firstStep == null || resetStudyMechanism == null || consistentStep == null ||
            seasonalSteps == null || basicSteps == null || mainObjectiveTitle == null ||
            mainGoalTitle == null || mainMaterialTopic == null || mainTask == null ||
            startDate == null || endDate == null) {
            throw new IllegalArgumentException("Nenhum campo pode ser nulo.");
        }
        this.firstStep = firstStep;
        this.resetStudyMechanism = resetStudyMechanism;
        this.consistentStep = consistentStep;
        this.seasonalSteps = seasonalSteps;
        this.basicSteps = basicSteps;
        this.mainObjectiveTitle = mainObjectiveTitle;
        this.mainGoalTitle = mainGoalTitle;
        this.mainMaterialTopic = mainMaterialTopic;
        this.mainTask = mainTask;
        this.numberOfSteps = numberOfSteps;
        this.isImportant = isImportant;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters
    public String getFirstStep() { return firstStep; }
    public String getResetStudyMechanism() { return resetStudyMechanism; }
    public String getConsistentStep() { return consistentStep; }
    public String getSeasonalSteps() { return seasonalSteps; }
    public String getBasicSteps() { return basicSteps; }
    public String getMainObjectiveTitle() { return mainObjectiveTitle; }
    public String getMainGoalTitle() { return mainGoalTitle; }
    public String getMainMaterialTopic() { return mainMaterialTopic; }
    public String getMainTask() { return mainTask; }
    public Integer getNumberOfSteps() { return numberOfSteps; }
    public boolean isImportant() { return isImportant; }
    public LocalDateTime getStartDate() { return startDate; }
    public LocalDateTime getEndDate() { return endDate; }
}
