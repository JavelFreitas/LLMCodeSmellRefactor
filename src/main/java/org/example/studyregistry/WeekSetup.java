package org.example.studyregistry;

import java.util.Arrays;
import java.util.List;

public class WeekSetup {
  private String planName, objectiveTitle, objectiveDescription, materialTopic,
      materialFormat, goal, reminderTitle, reminderDescription,
      mainTaskTitle, mainHabit, mainCardStudy;

  private WeekSetup(Builder builder) {
    validateBuilderFields(builder);
    initializeMainFields(builder);
    initializeMaterialFields(builder);
    initializeReminderFields(builder);
    initializeTaskFields(builder);
  }

  private void initializeMainFields(Builder builder) {
    planName = builder.planName;
    objectiveTitle = builder.objectiveTitle;
    objectiveDescription = builder.objectiveDescription;
    goal = builder.goal;
  }

  private void initializeMaterialFields(Builder builder) {
    materialTopic = builder.materialTopic;
    materialFormat = builder.materialFormat;
  }

  private void initializeReminderFields(Builder builder) {
    reminderTitle = builder.reminderTitle;
    reminderDescription = builder.reminderDescription;
  }

  private void initializeTaskFields(Builder builder) {
    mainTaskTitle = builder.mainTaskTitle;
    mainHabit = builder.mainHabit;
    mainCardStudy = builder.mainCardStudy;
  }

  private void validateBuilderFields(Builder builder) {
    if (builder.planName == null || builder.planName.trim().isEmpty()) {
      throw new IllegalArgumentException("Plan name cannot be empty");
    }
    if (builder.objectiveTitle == null || builder.objectiveTitle.trim().isEmpty()) {
      throw new IllegalArgumentException("Objective title cannot be empty");
    }
    if (builder.materialTopic == null || builder.materialTopic.trim().isEmpty()) {
      throw new IllegalArgumentException("Material topic cannot be empty");
    }
  }

  public boolean isCompleteSetup() {
    return !planName.isEmpty() && !objectiveTitle.isEmpty() && !materialTopic.isEmpty();
  }

  public boolean containsTopicReference(String topic) {
    return materialTopic.toLowerCase().contains(topic.toLowerCase());
  }

  public String summarizeWeekPlan() {
    return String.format("Week Plan: %s - Objective: %s - Main Task: %s",
        planName, objectiveTitle, mainTaskTitle);
  }

  public List<String> toList() {
    return Arrays.asList(
        planName, objectiveTitle, objectiveDescription, materialTopic,
        materialFormat, goal, reminderTitle, reminderDescription,
        mainTaskTitle, mainHabit, mainCardStudy);
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

    public Builder planName(String planName) {
      this.planName = planName;
      return this;
    }

    public Builder objectiveTitle(String objectiveTitle) {
      this.objectiveTitle = objectiveTitle;
      return this;
    }

    public Builder objectiveDescription(String objectiveDescription) {
      this.objectiveDescription = objectiveDescription;
      return this;
    }

    public Builder materialTopic(String materialTopic) {
      this.materialTopic = materialTopic;
      return this;
    }

    public Builder materialFormat(String materialFormat) {
      this.materialFormat = materialFormat;
      return this;
    }

    public Builder goal(String goal) {
      this.goal = goal;
      return this;
    }

    public Builder reminderTitle(String reminderTitle) {
      this.reminderTitle = reminderTitle;
      return this;
    }

    public Builder reminderDescription(String reminderDescription) {
      this.reminderDescription = reminderDescription;
      return this;
    }

    public Builder mainTaskTitle(String mainTaskTitle) {
      this.mainTaskTitle = mainTaskTitle;
      return this;
    }

    public Builder mainHabit(String mainHabit) {
      this.mainHabit = mainHabit;
      return this;
    }

    public Builder mainCardStudy(String mainCardStudy) {
      this.mainCardStudy = mainCardStudy;
      return this;
    }

    public WeekSetup build() {
      return new WeekSetup(this);
    }
  }
}