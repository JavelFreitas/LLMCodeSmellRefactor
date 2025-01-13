package org.example.studyregistry;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StudyGoal extends Registry{
    private String goal;
    private List<String> goalRequirements;
    private Boolean isCompleted;
    private LocalDateTime createdDate;
    private Double goalCompletion;
    private StudyObjective studyObjective;
    private StudyPlan studyPlan;
    private String summary;

    public StudyGoal(String name, StudyObjective objective, StudyPlan plan) {
        this.name = name;
        this.studyObjective = objective;
        this.studyPlan = plan;
        goalRequirements = new ArrayList<>();
    }

    public void editActiveCompleted(boolean active, boolean completed){
        this.isActive = active;
        this.isCompleted = completed;
    }

    public String setGoalSummary() {
        StringBuilder summary = new StringBuilder();
        appendHeader(summary, "Goal Summary:");

        appendConditionalSection(summary, this.isActive, "Active Goal:", goal);
        appendConditionalSection(summary, this.isCompleted, "Completed Goal:", goal);
        appendCollectionIfNotNull(summary, "Requirements:", this.goalRequirements);
        appendObjectIfNotNull(summary, "Plan:", this.studyPlan);
        appendObjectIfNotNull(summary, "Objective:", this.studyObjective);

        this.summary = summary.toString();
        return summary.toString();
    }

    private void appendHeader(StringBuilder summary, String header) {
        summary.append(header).append("\n\n");
    }

    private void appendConditionalSection(StringBuilder summary, boolean condition, String title, Object content) {
        if (condition) {
            appendSection(summary, title, content.toString());
        }
    }

    private void appendCollectionIfNotNull(StringBuilder summary, String title, Collection<String> collection) {
        if (collection != null) {
            summary.append(title).append("\n");
            for (String item : collection) {
                summary.append(item).append(", ");
            }
            summary.append("\n");
        }
    }

    private void appendObjectIfNotNull(StringBuilder summary, String title, Object obj) {
        if (obj != null) {
            appendSection(summary, title, obj.toString());
        }
    }

    private void appendSection(StringBuilder summary, String title, String content) {
        summary.append(title).append("\n").append(content).append("\n\n");
    }


    public void addRequirement(String requirement){
        this.goalRequirements.add(requirement);
    }

    public void resetRequirements(){
        this.goalRequirements.clear();
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void toggleIsCompleted(){
        this.isCompleted = !this.isCompleted;
    }

    public LocalDateTime getLimitDate() {
        return createdDate;
    }

    public void setLimitDate(LocalDateTime limitDate) {
        this.createdDate = limitDate;
    }

    public void addDaysLimitDate(int days){
        this.createdDate = this.createdDate.plusDays(days);
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }
}
