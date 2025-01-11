package org.example.studyregistry;

public class ObjectiveSetParams {
    private Long id;
    private Integer priority;
    private Integer practicedDays;
    private int day;
    private int month;
    private int year;
    private String name;
    private String title;
    private String description;
    private String topic;
    private String objectiveInOneLine;
    private String objectiveFullDescription;
    private String motivation;
    private Double duration;
    private boolean isActive;

    public ObjectiveSetParams(Long id, Integer priority, Integer practicedDays, int day, int month, int year,
                              String name, String title, String description, String topic,
                              String objectiveInOneLine, String objectiveFullDescription,
                              String motivation, Double duration, boolean isActive) {
        this.id = id;
        this.priority = priority;
        this.practicedDays = practicedDays;
        this.day = day;
        this.month = month;
        this.year = year;
        this.name = name;
        this.title = title;
        this.description = description;
        this.topic = topic;
        this.objectiveInOneLine = objectiveInOneLine;
        this.objectiveFullDescription = objectiveFullDescription;
        this.motivation = motivation;
        this.duration = duration;
        this.isActive = isActive;
    }

    // Getters
    public Long getId() { return id; }
    public Integer getPriority() { return priority; }
    public Integer getPracticedDays() { return practicedDays; }
    public int getDay() { return day; }
    public int getMonth() { return month; }
    public int getYear() { return year; }
    public String getName() { return name; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getTopic() { return topic; }
    public String getObjectiveInOneLine() { return objectiveInOneLine; }
    public String getObjectiveFullDescription() { return objectiveFullDescription; }
    public String getMotivation() { return motivation; }
    public Double getDuration() { return duration; }
    public boolean isActive() { return isActive; }
}
