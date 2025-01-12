package org.example.studyregistry;

public class WeekSetupData {
    public final String planName;
    public final String objectiveTitle;
    public final String objectiveDescription;
    public final String materialTopic;
    public final String materialFormat;
    public final String goal;
    public final String reminderTitle;
    public final String reminderDescription;
    public final String mainTaskTitle;
    public final String mainHabit;
    public final String mainCardStudy;

    public WeekSetupData(String planName, String objectiveTitle, String objectiveDescription, String materialTopic,
                         String materialFormat, String goal, String reminderTitle, String reminderDescription,
                         String mainTaskTitle, String mainHabit, String mainCardStudy) {
        this.planName = planName;
        this.objectiveTitle = objectiveTitle;
        this.objectiveDescription = objectiveDescription;
        this.materialTopic = materialTopic;
        this.materialFormat = materialFormat;
        this.goal = goal;
        this.reminderTitle = reminderTitle;
        this.reminderDescription = reminderDescription;
        this.mainTaskTitle = mainTaskTitle;
        this.mainHabit = mainHabit;
        this.mainCardStudy = mainCardStudy;
    }

    public String getPlanName() {
        return planName;
    }

    public String getObjectiveTitle() {
        return objectiveTitle;
    }

    public String getObjectiveDescription() {
        return objectiveDescription;
    }

    public String getMaterialTopic() {
        return materialTopic;
    }

    public String getMaterialFormat() {
        return materialFormat;
    }

    public String getGoal() {
        return goal;
    }

    public String getReminderTitle() {
        return reminderTitle;
    }

    public String getReminderDescription() {
        return reminderDescription;
    }

    public String getMainTaskTitle() {
        return mainTaskTitle;
    }

    public String getMainHabit() {
        return mainHabit;
    }

    public String getMainCardStudy() {
        return mainCardStudy;
    }
}
