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
    private String motivation;

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

    public void handleSetRegistry(RegistryInfo registryInfo) {
        this.id = registryInfo.getId();
        this.name = registryInfo.getName();
        this.priority = registryInfo.getPriority();
        this.isActive = registryInfo.isActive();
    }

    public void handleSetTextualInfo(TextualInfo textualInfo) {
        this.title = textualInfo.getTitle();
        this.description = textualInfo.getDescription();
        this.topic = textualInfo.getTopic();
        this.objectiveInOneLine = textualInfo.getObjectiveInOneLine();
        this.objectiveFullDescription = textualInfo.getObjectiveFullDescription();
        this.motivation = textualInfo.getMotivation();
    }

    public void handleSetTime(TimeInfo timeInfo) {
        this.practicedDays = timeInfo.getPracticedDays();
        this.startDate = timeInfo.getStartDate();
        this.duration = timeInfo.getDuration();
    }

    public void handleSetObjective(RegistryInfo registryInfo, TextualInfo textualInfo, TimeInfo timeInfo) {
        handleSetRegistry(registryInfo);
        handleSetTextualInfo(textualInfo);
        handleSetTime(timeInfo);
    }

    public int handleSetObjectiveAdapter(List<Integer> intProperties, List<String> stringProperties,
                                         Double duration, boolean isActive) {
        RegistryInfo registryInfo = new RegistryInfo(
                intProperties.get(0), stringProperties.get(0), intProperties.get(1), isActive
        );

        TextualInfo textualInfo = new TextualInfo.Builder()
                .withTitle(stringProperties.get(1))
                .withDescription(stringProperties.get(2))
                .withTopic(stringProperties.get(3))
                .withObjectiveInOneLine(stringProperties.get(4))
                .withObjectiveFullDescription(stringProperties.get(5))
                .withMotivation(stringProperties.get(6))
                .build();

        TimeInfo timeInfo = new TimeInfo(
                intProperties.get(2), intProperties.get(3), intProperties.get(4),
                intProperties.get(5), duration
        );

        handleSetObjective(registryInfo, textualInfo, timeInfo);
        return registryInfo.getId();
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
