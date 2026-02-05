package org.example.studyregistry.dto;

public record WeekSetupInfo(String planName, String objectiveTitle, String objectiveDescription, String materialTopic,
                            String materialFormat, String goal, String reminderTitle, String reminderDescription,
                            String mainTaskTitle, String mainHabit, String mainCardStudy) { }