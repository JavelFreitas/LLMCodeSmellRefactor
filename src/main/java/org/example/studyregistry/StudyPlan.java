package org.example.studyregistry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.example.studyregistry.StudyPlan.StepConfiguration;
import org.example.studyregistry.StudyPlan.StepDetails;
import org.example.studyregistry.StudyPlan.StepMetadata;

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

    public void assignSteps(StepDetails details) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        this.steps = new ArrayList<>(Arrays.asList(
            details.getConfiguration().summarizeSteps(),
            "Number of steps: " + details.getMetadata().getNumberOfSteps(),
            "Is it important to you? " + details.getMetadata().isImportant(),
            "Start Date: " + details.getMetadata().getStartDate().format(formatter),
            "End Date: " + details.getMetadata().getEndDate().format(formatter),
            details.getConfiguration().generateConfigurationReport()
        ));
    }

    public void handleAssignSteps(List<String> stringProperties, Integer numberOfSteps, boolean isImportant, LocalDateTime startDate, LocalDateTime endDate) {
        StepConfiguration configuration = StepConfiguration.buildFromProperties(stringProperties);

        StepMetadata metadata = new StepMetadata(numberOfSteps, isImportant, startDate, endDate);

        StepDetails details = new StepDetails(configuration, metadata);
        assignSteps(details);
    }

    public static class StepDetails {
        private final StepConfiguration configuration;
        private final StepMetadata metadata;

        public StepDetails(StepConfiguration configuration, StepMetadata metadata) {
            this.configuration = configuration;
            this.metadata = metadata;
        }

        public StepConfiguration getConfiguration() {
            return configuration;
        }

        public StepMetadata getMetadata() {
            return metadata;
        }
    }

    public static class StepConfiguration {
        private final String firstStep;
        private final String resetStudyMechanism;
        private final String consistentStep;
        private final String seasonalSteps;
        private final String basicSteps;
        private final String mainObjectiveTitle;
        private final String mainGoalTitle;
        private final String mainMaterialTopic;
        private final String mainTask;

        public StepConfiguration(String firstStep, String resetStudyMechanism, String consistentStep, String seasonalSteps,
                                 String basicSteps, String mainObjectiveTitle, String mainGoalTitle, String mainMaterialTopic, String mainTask) {
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

        public static StepConfiguration buildFromProperties(List<String> properties) {
            if (properties == null || properties.size() < 9) {
                throw new IllegalArgumentException("Insufficient properties to build StepConfiguration");
            }

            return new StepConfiguration(
                properties.get(0),
                properties.get(1),
                properties.get(2),
                properties.get(3),
                properties.get(4),
                properties.get(5),
                properties.get(6),
                properties.get(7),
                properties.get(8)
            );
        }

        public boolean validateConsistency() {
            return firstStep != null && !firstStep.isBlank() &&
                   resetStudyMechanism != null && !resetStudyMechanism.isBlank() &&
                   consistentStep != null && !consistentStep.isBlank();
        }

        public String summarizeSteps() {
            return String.format("First Step: %s, Reset Mechanism: %s, Consistent Step: %s", 
                                  firstStep, resetStudyMechanism, consistentStep);
        }

        public String generateConfigurationReport() {
            return String.format("""
                    Step Configuration Report:
                    - First Step: %s
                    - Reset Mechanism: %s
                    - Consistent Step: %s
                    - Seasonal Steps: %s
                    - Basic Steps: %s
                    - Main Objective Title: %s
                    - Main Goal Title: %s
                    - Main Material Topic: %s
                    - Main Task: %s
                    """, firstStep, resetStudyMechanism, consistentStep, seasonalSteps, basicSteps, 
                         mainObjectiveTitle, mainGoalTitle, mainMaterialTopic, mainTask);
        }

        public String identifyKeyChallenges() {
            return String.format("Key Challenges: %s, %s, %s", consistentStep, resetStudyMechanism, seasonalSteps);
        }

        public List<String> listAllSteps() {
            return Arrays.asList(firstStep, resetStudyMechanism, consistentStep, seasonalSteps, basicSteps);
        }

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
    }

    public static class StepMetadata {
        private final Integer numberOfSteps;
        private final boolean isImportant;
        private final LocalDateTime startDate;
        private final LocalDateTime endDate;

        public StepMetadata(Integer numberOfSteps, boolean isImportant, LocalDateTime startDate, LocalDateTime endDate) {
            this.numberOfSteps = numberOfSteps;
            this.isImportant = isImportant;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public boolean isStartDateInFuture() {
            return startDate.isAfter(LocalDateTime.now());
        }

        public long getDurationInDays() {
            return java.time.Duration.between(startDate, endDate).toDays();
        }

        public boolean hasValidDuration() {
            return getDurationInDays() > 0;
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
}
