package org.example.studyregistry;

public record StudyObjectiveInfo(
        String mainObjectiveTitle,
        String mainGoalTitle,
        String mainMaterialTopic,
        String mainTask,
        Integer numberOfSteps,
        boolean isImportant
) {}
