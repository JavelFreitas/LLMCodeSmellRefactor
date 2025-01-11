package org.example.studyregistry;

import org.example.studyregistry.dtos.StudyObjectiveDetails;

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

    public void handleSetTextualInfo(String title, String description, String topic,String objectiveInOneLine, String objectiveFullDescription, String motivation){
        this.title=title;
        this.description=description;
        this.topic=topic;
        this.objectiveInOneLine=objectiveInOneLine;
        this.objectiveFullDescription=objectiveFullDescription;
        this.motivation=motivation;
    }

    public void handleSetTime(Integer practicedDays, int day, int month, int year, Double duration){
        this.practicedDays=practicedDays;
        this.duration=duration;
        this.startDate= LocalDateTime.of(year, month, day, 0, 0);
    }


    public void handleSetObjective(StudyObjectiveDetails objectiveDetails) {
        handleSetRegistry(objectiveDetails.getId(), objectiveDetails.getName(), objectiveDetails.getPriority(), objectiveDetails.isActive());
        handleSetTextualInfo(objectiveDetails.getTitle(), objectiveDetails.getDescription(), objectiveDetails.getTopic(),
                objectiveDetails.getObjectiveInOneLine(), objectiveDetails.getObjectiveFullDescription(), objectiveDetails.getMotivation());
        handleSetTime(objectiveDetails.getPracticedDays(), objectiveDetails.getDay(), objectiveDetails.getMonth(),
                objectiveDetails.getYear(), objectiveDetails.getDuration());
    }
    public int handleSetObjectiveAdapter(List<Integer> intProperties, List<String> stringProperties, Double duration, boolean isActive) {
        // Using the Builder pattern to construct the StudyObjectiveDetails object from lists
        StudyObjectiveDetails objectiveDetails = new StudyObjectiveDetails.Builder()
                .withId(intProperties.get(0))
                .withPriority(intProperties.get(1))
                .withPracticedDays(intProperties.get(2))
                // Set the DateTime fields using the withDateTime method
                .withDateTime(intProperties.get(3), intProperties.get(4), intProperties.get(5)) // day, month, year
                .withName(stringProperties.get(0))
                .withTitle(stringProperties.get(1))
                .withDescription(stringProperties.get(2))
                .withTopic(stringProperties.get(3))
                .withObjectiveInOneLine(stringProperties.get(4))
                .withObjectiveFullDescription(stringProperties.get(5))
                .withMotivation(stringProperties.get(6))
                .withDuration(duration)
                .withIsActive(isActive)
                .build();

        // Call the handleSetObjective method using the constructed objectiveDetails
        handleSetObjective(objectiveDetails);

        // Returning the ID after processing
        return intProperties.get(0);
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
