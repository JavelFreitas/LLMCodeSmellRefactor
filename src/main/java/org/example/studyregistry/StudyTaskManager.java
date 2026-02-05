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

    public void setUpWeek(WeekSetupParams params) {
        this.weekResponsibilities = new ArrayList<>(params.toList());
    }

    public void handleSetUpWeek(List<String> stringProperties) {
        WeekSetupParams params = createWeekSetupParams(stringProperties);
        setUpWeek(params);
    }

    private WeekSetupParams createWeekSetupParams(List<String> properties) {
        return new WeekSetupParams(
                properties.get(0), // planName
                properties.get(1), // objectiveTitle
                properties.get(2), // objectiveDescription
                properties.get(3), // materialTopic
                properties.get(4), // materialFormat
                properties.get(5), // goal
                properties.get(6), // reminderTitle
                properties.get(7), // reminderDescription
                properties.get(8), // mainTaskTitle
                properties.get(9), // mainHabit
                properties.get(10) // mainCardStudy
        );
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
        return registryList.stream()
                .filter(registry -> registry.matchesSearchCriteria(text))
                .map(Registry::getName)
                .toList();
    }

    public record WeekSetupParams(
            String planName,
            String objectiveTitle,
            String objectiveDescription,
            String materialTopic,
            String materialFormat,
            String goal,
            String reminderTitle,
            String reminderDescription,
            String mainTaskTitle,
            String mainHabit,
            String mainCardStudy
    ) {

        public List<String> toList() {
            return List.of(
                    planName, objectiveTitle, objectiveDescription,
                    materialTopic, materialFormat, goal, reminderTitle,
                    reminderDescription, mainTaskTitle, mainHabit, mainCardStudy
            );
        }
    }
}