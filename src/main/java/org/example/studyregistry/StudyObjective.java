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

    private String motivation;

    @Override
    public String toString() {
        return "StudyObjective [title:" + title + ", description:" + description
                + (topic != null ? ", topic:" + topic : "")
                + (practicedDays != null ? ", practicedDays:" + practicedDays : "")
                + (duration != null ? ", duration:" + duration : "")
                + (objectiveInOneLine != null ? ", objective summary:" + objectiveInOneLine : "")
                + (objectiveFullDescription != null ? ", objective full description:" + objectiveFullDescription : "")
                + (motivation != null ? ", motivation:" + motivation : "") + "]";
    }

    public StudyObjective(String title, String description) {
        this.title = title;
        this.description = description;
        this.name = title;
    }

    public void handleSetRegistry(RegistryProperties properties) {
        this.id = properties.id;
        this.name = properties.name;
        this.priority = properties.priority;
        this.isActive = properties.isActive;
    }

    public void handleSetTextualInfo(TextualProperties properties) {
        this.title = properties.title;
        this.description = properties.description;
        this.topic = properties.topic;
        this.objectiveInOneLine = properties.objectiveInOneLine;
        this.objectiveFullDescription = properties.objectiveFullDescription;
        this.motivation = properties.motivation;
    }

    public void handleSetTime(TimeProperties properties) {
        this.practicedDays = properties.practicedDays;
        this.duration = properties.duration;
        this.startDate = LocalDateTime.of(properties.year, properties.month, properties.day, 0, 0);
    }

    public void handleSetObjective(RegistryProperties registryProps, TextualProperties textualProps,
            TimeProperties timeProps) {
        handleSetRegistry(registryProps);
        handleSetTextualInfo(textualProps);
        handleSetTime(timeProps);
    }

    public int handleSetObjectiveAdapter(List<Integer> intProperties, List<String> stringProperties, Double duration,
            boolean isActive) {
        RegistryProperties registryProps = RegistryProperties.builder().id(intProperties.get(0))
                .name(stringProperties.get(0)).priority(intProperties.get(1)).isActive(isActive).build();
        TextualProperties textualProps = TextualProperties.builder().title(stringProperties.get(1))
                .description(stringProperties.get(2)).topic(stringProperties.get(3))
                .objectiveInOneLine(stringProperties.get(4)).objectiveFullDescription(stringProperties.get(5))
                .motivation(stringProperties.get(6)).build();
        TimeProperties timeProps = TimeProperties.builder().practicedDays(intProperties.get(2))
                .day(intProperties.get(3)).month(intProperties.get(4)).year(intProperties.get(5)).duration(duration)
                .build();
                
        handleSetObjective(registryProps, textualProps, timeProps);

        return intProperties.get(0);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
