package org.example.studyregistry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudyTaskManager {
    private static StudyTaskManager instance;
    private StudyMaterial studyMaterial = StudyMaterial.getStudyMaterial();
    private List<Registry> registryList;
    private List<String> weekResponsibilities = List.of();

    private StudyTaskManager() {
        this.registryList = new ArrayList<>();
    }

    public static StudyTaskManager getStudyTaskManager() {
        if (instance == null) {
            instance = new StudyTaskManager();
        }
        return instance;
    }

    public List<String> getWeekResponsibilities() {
        return weekResponsibilities;
    }

    public void setUpWeek(WeekPlan weekPlan) {
        this.weekResponsibilities = new ArrayList<>();
        this.weekResponsibilities.addAll(Arrays.asList(
            weekPlan.getPlanName(),
            weekPlan.getObjectiveDetails().generateSummary(),
            weekPlan.getMaterialDetails().generateSummary(),
            weekPlan.getReminderDetails().generateSummary(),
            weekPlan.getMainTaskTitle(),
            weekPlan.getMainHabit(),
            weekPlan.getMainCardStudy()
        ));
    }

    public void handleSetUpWeek(List<String> stringProperties) {
        ObjectiveDetails objective = new ObjectiveDetails(stringProperties.get(0), stringProperties.get(1));
        MaterialDetails material = new MaterialDetails(stringProperties.get(2), stringProperties.get(3));
        ReminderDetails reminder = new ReminderDetails(stringProperties.get(4), stringProperties.get(5));

        WeekPlan weekPlan = new WeekPlan(
            stringProperties.get(6), // Plan Name
            objective,
            material,
            reminder,
            stringProperties.get(7), // Main Task Title
            stringProperties.get(8), // Main Habit
            stringProperties.get(9)  // Main Card Study
        );

        setUpWeek(weekPlan);
    }

    public void addRegistry(Registry registry) {
        registryList.add(registry);
    }

    public void removeRegistry(Registry registry) {
        registryList.remove(registry);
    }

    public List<Registry> getRegistryList() {
        return registryList;
    }

    public List<String> searchInRegistries(String text) {
        List<String> response = new ArrayList<>();
        for (Registry registry : registryList) {
            String mix = (registry.getName() != null ? registry.getName() : "");
            if (mix.toLowerCase().contains(text.toLowerCase())) {
                response.add(registry.getName());
            }
        }
        return response;
    }

    public static class WeekPlan {
        private final String planName;
        private final ObjectiveDetails objectiveDetails;
        private final MaterialDetails materialDetails;
        private final ReminderDetails reminderDetails;
        private final String mainTaskTitle;
        private final String mainHabit;
        private final String mainCardStudy;

        public WeekPlan(String planName, ObjectiveDetails objectiveDetails, MaterialDetails materialDetails,
                        ReminderDetails reminderDetails, String mainTaskTitle, String mainHabit, String mainCardStudy) {
            this.planName = planName;
            this.objectiveDetails = objectiveDetails;
            this.materialDetails = materialDetails;
            this.reminderDetails = reminderDetails;
            this.mainTaskTitle = mainTaskTitle;
            this.mainHabit = mainHabit;
            this.mainCardStudy = mainCardStudy;
        }

        public String getPlanName() {
            return planName;
        }

        public ObjectiveDetails getObjectiveDetails() {
            return objectiveDetails;
        }

        public MaterialDetails getMaterialDetails() {
            return materialDetails;
        }

        public ReminderDetails getReminderDetails() {
            return reminderDetails;
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

        public boolean isPlanComplete() {
            return planName != null && !planName.isBlank() &&
                   objectiveDetails.isObjectiveDefined() &&
                   materialDetails.hasMaterials() &&
                   reminderDetails.hasReminders() &&
                   mainTaskTitle != null && !mainTaskTitle.isBlank() &&
                   mainHabit != null && !mainHabit.isBlank() &&
                   mainCardStudy != null && !mainCardStudy.isBlank();
        }

        public String generateWeeklyPlanSummary() {
            return String.format("""
                    Weekly Plan Summary:
                    - Plan Name: %s
                    - Objective: %s
                    - Materials: %s
                    - Reminders: %s
                    - Main Task: %s
                    - Main Habit: %s
                    - Main Card Study: %s
                    """, planName,
                    objectiveDetails.generateSummary(),
                    materialDetails.generateSummary(),
                    reminderDetails.generateSummary(),
                    mainTaskTitle, mainHabit, mainCardStudy);
        }

        public String validatePlanConsistency() {
            if (!isPlanComplete()) {
                return "Plan is incomplete. Please provide all required details.";
            }
            if (!objectiveDetails.isObjectiveAchievable()) {
                return "Objective is not achievable based on current settings.";
            }
            return "Plan is consistent and ready to execute.";
        }

        public void executeWeeklyPlan() {
            if (!isPlanComplete()) {
                throw new IllegalStateException("Cannot execute an incomplete plan.");
            }
            System.out.println("Executing Weekly Plan: " + planName);
        }
    }

    public static class ObjectiveDetails {
        private final String objectiveTitle;
        private final String objectiveDescription;

        public ObjectiveDetails(String objectiveTitle, String objectiveDescription) {
            this.objectiveTitle = objectiveTitle;
            this.objectiveDescription = objectiveDescription;
        }

        public boolean isObjectiveDefined() {
            return objectiveTitle != null && !objectiveTitle.isBlank() &&
                   objectiveDescription != null && !objectiveDescription.isBlank();
        }

        public String generateSummary() {
            return String.format("Objective: %s - %s", objectiveTitle, objectiveDescription);
        }

        public boolean isObjectiveAchievable() {
            return objectiveDescription.length() < 500;
        }
    }

    public static class MaterialDetails {
        private final String materialTopic;
        private final String materialFormat;

        public MaterialDetails(String materialTopic, String materialFormat) {
            this.materialTopic = materialTopic;
            this.materialFormat = materialFormat;
        }

        public boolean hasMaterials() {
            return materialTopic != null && !materialTopic.isBlank() &&
                   materialFormat != null && !materialFormat.isBlank();
        }

        public String generateSummary() {
            return String.format("Material Topic: %s, Format: %s", materialTopic, materialFormat);
        }
    }

    public static class ReminderDetails {
        private final String reminderTitle;
        private final String reminderDescription;

        public ReminderDetails(String reminderTitle, String reminderDescription) {
            this.reminderTitle = reminderTitle;
            this.reminderDescription = reminderDescription;
        }

        public boolean hasReminders() {
            return reminderTitle != null && !reminderTitle.isBlank() &&
                   reminderDescription != null && !reminderDescription.isBlank();
        }

        public String generateSummary() {
            return String.format("Reminder: %s - %s", reminderTitle, reminderDescription);
        }
    }
}
