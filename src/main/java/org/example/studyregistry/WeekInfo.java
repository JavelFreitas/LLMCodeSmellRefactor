package org.example.studyregistry;

import java.util.Arrays;
import java.util.List;

public record WeekInfo(
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
    public static WeekInfo fromList(List<String> properties) {
        return new WeekInfo(
                properties.get(0),
                properties.get(1),
                properties.get(2),
                properties.get(3),
                properties.get(4),
                properties.get(5),
                properties.get(6),
                properties.get(7),
                properties.get(8),
                properties.get(9),
                properties.get(10)
        );
    }

    public List<String> toList() {
        return Arrays.asList(
                planName,
                objectiveTitle,
                objectiveDescription,
                materialTopic,
                materialFormat,
                goal,
                reminderTitle,
                reminderDescription,
                mainTaskTitle,
                mainHabit,
                mainCardStudy
        );
    }
}
