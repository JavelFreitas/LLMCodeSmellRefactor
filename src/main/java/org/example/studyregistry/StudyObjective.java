package org.example.studyregistry;

import java.time.LocalDateTime;
import java.util.List;

public class StudyObjective extends Registry {
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

    public String getDescription() {
        return description;
    }

    private String motivation;

    @Override
    public String toString() {
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

    public void handleSetRegistry(RegistryProperties props) {
        this.id = props.getId();
        this.name = props.getName();
        this.priority = props.getPriority();
        this.isActive = props.isActive();
    }

    public void handleSetTextualInfo(TextualInfo info) {
        this.title = info.getTitle();
        this.description = info.getDescription();
        this.topic = info.getTopic();
        this.objectiveInOneLine = info.getObjectiveInOneLine();
        this.objectiveFullDescription = info.getObjectiveFullDescription();
        this.motivation = info.getMotivation();
    }

    public void handleSetTime(TimeInfo time) {
        this.practicedDays = time.getPracticedDays();
        this.duration = time.getDuration();
        this.startDate = LocalDateTime.of(time.getYear(), time.getMonth(), time.getDay(), 0, 0);
    }

    public void handleSetObjective(RegistryProperties registryProps, TextualInfo textualInfo, TimeInfo timeInfo) {
        handleSetRegistry(registryProps);
        handleSetTextualInfo(textualInfo);
        handleSetTime(timeInfo);
    }

    public int handleSetObjectiveAdapter(List<Integer> intProperties, List<String> stringProperties, Double duration, boolean isActive) {
        RegistryProperties registryProps = new RegistryProperties(
                intProperties.get(0), stringProperties.get(0), intProperties.get(1), isActive
        );

        TextualInfo textualInfo = new TextualInfo(
                stringProperties.get(1), stringProperties.get(2), stringProperties.get(3),
                stringProperties.get(4), stringProperties.get(5), stringProperties.get(6)
        );

        TimeInfo timeInfo = new TimeInfo(
                intProperties.get(2), intProperties.get(3), intProperties.get(4),
                intProperties.get(5), duration
        );

        handleSetObjective(registryProps, textualInfo, timeInfo);
        return registryProps.getId();
    }
}