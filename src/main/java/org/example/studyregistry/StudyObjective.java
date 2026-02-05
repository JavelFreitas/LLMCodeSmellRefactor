package org.example.studyregistry;

import java.time.LocalDateTime;
import java.util.List;

public class StudyObjective extends Registry{
    private String title;
    private String description;
    private String topic;
    private Integer practicedDays;
    private LocalDateTime startDate;
    private Double duration;
    private String objectiveInOneLine;
    private String objectiveFullDescription;

    public String getTitle() {
        return title;
    }

    public String getTopic() {
        return topic;
    }

    public Integer getPracticedDays() {
        return practicedDays;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public Double getDuration() {
        return duration;
    }

    public String getObjectiveInOneLine() {
        return objectiveInOneLine;
    }

    public String getObjectiveFullDescription() {
        return objectiveFullDescription;
    }

    public String getMotivation() {
        return motivation;
    }

    private String motivation;

    @Override
    public String toString(){
        return "StudyObjective [title:" + title + ", description:" + description + (topic != null ? ", topic:" + topic : "")
                + (practicedDays != null ? ", practicedDays:" + practicedDays : "") + (duration != null ? ", duration:" + duration : "")
                + (objectiveInOneLine != null ? ", objective summary:" + objectiveInOneLine : "") + (objectiveFullDescription != null ? ", objective full description:" + objectiveFullDescription : "")
                + (motivation != null ? ", motivation:" + motivation : "") + "]";
    }
    public StudyObjective(String title, String description) {
        this.title = title;
        this.description = description;
        this.name = title;
    }

    public void handleSetRegistry(Integer id, String name, Integer priority, boolean isActive){
        this.id=id;
        this.name=name;
        this.priority=priority;
        this.isActive=isActive;
    }

    public void handleSetTextualInfo(String title, String description, ObjectiveDetails details) {
        this.title = title;
        this.description = description;
        this.topic = details.getTopic();
        this.objectiveInOneLine = details.getObjectiveInOneLine();
        this.objectiveFullDescription = details.getObjectiveFullDescription();
        this.motivation = details.getMotivation();
    }

    public void handleSetObjective(Integer id, Integer priority, Integer practicedDays, int day, int month, int year, String name, String title, String description, ObjectiveDetails details, Double duration, boolean isActive){
        handleSetRegistry(id, name, priority, isActive);
        handleSetTextualInfo(title, description, details);
        handleSetTime(practicedDays, day, month, year, duration);
    }

    public void handleSetTime(Integer practicedDays, int day, int month, int year, Double duration){
        this.practicedDays=practicedDays;
        this.duration=duration;
        this.startDate= LocalDateTime.of(year, month, day, 0, 0);
    }

    public void handleSetObjective(Integer id, Integer priority, Integer practicedDays, int day, int month, int year,  ObjectiveDetails details){
        handleSetRegistry(id, name, priority, isActive);
        handleSetTextualInfo(title, description,details);
        handleSetTime(practicedDays, day, month, year, duration);
    }

    public int handleSetObjectiveAdapter(List<Integer> intProperties, List<String> stringProperties, Double duration, boolean isActive) {
        ObjectiveDetails details = new ObjectiveDetails(
                stringProperties.get(3), stringProperties.get(4), stringProperties.get(5), stringProperties.get(6));
        handleSetObjective(intProperties.get(0), intProperties.get(1), intProperties.get(2), intProperties.get(3), intProperties.get(4), intProperties.get(5),
                stringProperties.get(0), stringProperties.get(1), stringProperties.get(2), details, duration, isActive);
        return intProperties.get(0);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
