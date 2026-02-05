package org.example.studyregistry;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StudyGoal extends Registry {
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

    public void editActiveCompleted(boolean active, boolean completed) {
        this.isActive = active;
        this.isCompleted = completed;
    }

    public String setGoalSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Goal Summary:\n\n");

        appendActiveGoalInfo(summary);
        appendCompletedGoalInfo(summary);
        appendRequirementsInfo(summary);
        appendPlanInfo(summary);
        appendObjectiveInfo(summary);

        this.summary = summary.toString();
        return summary.toString();
    }

    private void appendActiveGoalInfo(StringBuilder summary) {
        if (this.isActive) {
            summary.append("Active Goal:\n").append(goal).append("\n\n");
        }
    }

    private void appendCompletedGoalInfo(StringBuilder summary) {
        if (this.isCompleted) {
            summary.append("Completed Goal:\n").append(goal).append("\n\n");
        }
    }

    private void appendRequirementsInfo(StringBuilder summary) {
        if (this.goalRequirements != null) {
            summary.append("Requirements:\n");
            for (String requirement : this.goalRequirements) {
                summary.append(requirement).append(", ");
            }
            summary.delete(summary.length() - 2, summary.length()); // Remove last comma and space
            summary.append("\n");
        }
    }

    private void appendPlanInfo(StringBuilder summary) {
        if (this.studyPlan != null) {
            summary.append("Plan:\n");
            summary.append(this.studyPlan.toString()).append("\n");
        }
    }

    private void appendObjectiveInfo(StringBuilder summary) {
        if (this.studyObjective != null) {
            summary.append("Objective:\n");
            summary.append(this.studyObjective.toString()).append("\n");
        }
    }


    public void addRequirement(String requirement) {
        this.goalRequirements.add(requirement);
    }

    public void resetRequirements() {
        this.goalRequirements.clear();
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void toggleIsCompleted() {
        this.isCompleted = !this.isCompleted;
    }

    public LocalDateTime getLimitDate() {
        return createdDate;
    }

    public void setLimitDate(LocalDateTime limitDate) {
        this.createdDate = limitDate;
    }

    public void addDaysLimitDate(int days) {
        this.createdDate = this.createdDate.plusDays(days);
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }
}