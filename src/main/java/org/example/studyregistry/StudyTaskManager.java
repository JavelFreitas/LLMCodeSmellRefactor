package org.example.studyregistry;

import java.util.ArrayList;
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

    public void setUpWeek(WeekSetup weekSetup) {
        if (weekSetup.isValid()) {
            this.weekResponsibilities = weekSetup.toResponsibilitiesList();
        } else {
            throw new IllegalArgumentException("Invalid WeekSetup data!");
        }
    }

    public void handleSetUpWeek(List<String> stringProperties) {
        WeekSetup.Builder builder = new WeekSetup.Builder()
                .setPlanName(stringProperties.get(0))
                .setObjectiveTitle(stringProperties.get(1))
                .setObjectiveDescription(stringProperties.get(2))
                .setMaterialTopic(stringProperties.get(3))
                .setMaterialFormat(stringProperties.get(4))
                .setGoal(stringProperties.get(5))
                .setReminderTitle(stringProperties.get(6))
                .setReminderDescription(stringProperties.get(7))
                .setMainTaskTitle(stringProperties.get(8))
                .setMainHabit(stringProperties.get(9))
                .setMainCardStudy(stringProperties.get(10));
        setUpWeek(builder.build());
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
            String name = (registry.getName() != null ? registry.getName() : "");
            if (name.toLowerCase().contains(text.toLowerCase())) {
                response.add(registry.getName());
            }
        }
        return response;
    }

    // Classe WeekSetup com métodos encapsulados
    public static class WeekSetup {
        private String planName;
        private String objectiveTitle;
        private String objectiveDescription;
        private String materialTopic;
        private String materialFormat;
        private String goal;
        private String reminderTitle;
        private String reminderDescription;
        private String mainTaskTitle;
        private String mainHabit;
        private String mainCardStudy;

        private WeekSetup(Builder builder) {
            initializeFields(builder);
        }

        private void initializeFields(Builder builder) {
            initializePlanDetails(builder);
            initializeObjectiveDetails(builder);
            initializeMaterialDetails(builder);
            initializeReminderDetails(builder);
            initializeTaskDetails(builder);
        }

        private void initializePlanDetails(Builder builder) {
            this.planName = validateAndAssignField(builder.planName, "Plan Name");
        }

        private void initializeObjectiveDetails(Builder builder) {
            this.objectiveTitle = validateAndAssignField(builder.objectiveTitle, "Objective Title");
            this.objectiveDescription = validateAndAssignField(builder.objectiveDescription, "Objective Description");
        }

        private void initializeMaterialDetails(Builder builder) {
            this.materialTopic = validateAndAssignField(builder.materialTopic, "Material Topic");
            this.materialFormat = validateAndAssignField(builder.materialFormat, "Material Format");
        }

        private void initializeReminderDetails(Builder builder) {
            this.goal = validateAndAssignField(builder.goal, "Goal");
            this.reminderTitle = validateAndAssignField(builder.reminderTitle, "Reminder Title");
            this.reminderDescription = validateAndAssignField(builder.reminderDescription, "Reminder Description");
        }

        private void initializeTaskDetails(Builder builder) {
            this.mainTaskTitle = validateAndAssignField(builder.mainTaskTitle, "Main Task Title");
            this.mainHabit = validateAndAssignField(builder.mainHabit, "Main Habit");
            this.mainCardStudy = validateAndAssignField(builder.mainCardStudy, "Main Card Study");
        }

        private String validateAndAssignField(String value, String fieldName) {
            validateField(value, fieldName);
            return value;
        }

        private void validateField(String value, String fieldName) {
            if (value == null || value.isEmpty()) {
                throw new IllegalArgumentException(fieldName + " cannot be null or empty.");
            }
        }

        public boolean isValid() {
            return planName != null && !planName.isEmpty()
                    && objectiveTitle != null && !objectiveTitle.isEmpty()
                    && objectiveDescription != null && !objectiveDescription.isEmpty()
                    && materialTopic != null && !materialTopic.isEmpty()
                    && materialFormat != null && !materialFormat.isEmpty()
                    && goal != null && !goal.isEmpty()
                    && reminderTitle != null && !reminderTitle.isEmpty()
                    && reminderDescription != null && !reminderDescription.isEmpty()
                    && mainTaskTitle != null && !mainTaskTitle.isEmpty()
                    && mainHabit != null && !mainHabit.isEmpty()
                    && mainCardStudy != null && !mainCardStudy.isEmpty();
        }

        public List<String> toResponsibilitiesList() {
            return List.of(
                    planName, objectiveTitle, objectiveDescription,
                    materialTopic, materialFormat, goal,
                    reminderTitle, reminderDescription,
                    mainTaskTitle, mainHabit, mainCardStudy
            );
        }

        public static class Builder {
            private String planName;
            private String objectiveTitle;
            private String objectiveDescription;
            private String materialTopic;
            private String materialFormat;
            private String goal;
            private String reminderTitle;
            private String reminderDescription;
            private String mainTaskTitle;
            private String mainHabit;
            private String mainCardStudy;

            public Builder setPlanName(String planName) {
                this.planName = planName;
                return this;
            }

            public Builder setObjectiveTitle(String objectiveTitle) {
                this.objectiveTitle = objectiveTitle;
                return this;
            }

            public Builder setObjectiveDescription(String objectiveDescription) {
                this.objectiveDescription = objectiveDescription;
                return this;
            }

            public Builder setMaterialTopic(String materialTopic) {
                this.materialTopic = materialTopic;
                return this;
            }

            public Builder setMaterialFormat(String materialFormat) {
                this.materialFormat = materialFormat;
                return this;
            }

            public Builder setGoal(String goal) {
                this.goal = goal;
                return this;
            }

            public Builder setReminderTitle(String reminderTitle) {
                this.reminderTitle = reminderTitle;
                return this;
            }

            public Builder setReminderDescription(String reminderDescription) {
                this.reminderDescription = reminderDescription;
                return this;
            }

            public Builder setMainTaskTitle(String mainTaskTitle) {
                this.mainTaskTitle = mainTaskTitle;
                return this;
            }

            public Builder setMainHabit(String mainHabit) {
                this.mainHabit = mainHabit;
                return this;
            }

            public Builder setMainCardStudy(String mainCardStudy) {
                this.mainCardStudy = mainCardStudy;
                return this;
            }

            public WeekSetup build() {
                return new WeekSetup(this);
            }
        }
    }
}