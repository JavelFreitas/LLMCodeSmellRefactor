package org.example.studyregistry;

import java.time.LocalDateTime;
import java.time.DateTimeException;
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
    private String motivation;

    // Existing getters
    public String getTitle() { return title; }
    public String getTopic() { return topic; }
    public Integer getPracticedDays() { return practicedDays; }
    public LocalDateTime getStartDate() { return startDate; }
    public Double getDuration() { return duration; }
    public String getObjectiveInOneLine() { return objectiveInOneLine; }
    public String getObjectiveFullDescription() { return objectiveFullDescription; }
    public String getMotivation() { return motivation; }

    @Override
    public String toString() {
        return "StudyObjective [title:" + title + ", description:" + description + (topic != null ? ", topic:" + topic : "")
                + (practicedDays != null ? ", practicedDays:" + practicedDays : "") + (duration != null ? ", duration:" + duration : "")
                + (objectiveInOneLine != null ? ", objective summary:" + objectiveInOneLine : "") + (objectiveFullDescription != null ? ", objective full description:" + objectiveFullDescription : "")
                + (motivation != null ? ", motivation:" + motivation : "") + "]";
    }

    // Builder class
    public static class StudyObjectiveBuilder {
        private String title;
        private String description;
        private String topic;
        private Integer practicedDays;
        private LocalDateTime startDate;
        private Double duration;
        private String objectiveInOneLine;
        private String objectiveFullDescription;
        private String motivation;

        public StudyObjectiveBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public StudyObjectiveBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public StudyObjectiveBuilder setTopic(String topic) {
            this.topic = topic;
            return this;
        }

        public StudyObjectiveBuilder setPracticedDays(Integer practicedDays) {
            this.practicedDays = practicedDays;
            return this;
        }

        public StudyObjectiveBuilder setStartDate(LocalDateTime startDate) {
            if (startDate != null) {
                try {
                    // Validar data antes de atribuí-la
                    startDate.toLocalDate(); // Isso garantirá que a data seja válida
                    this.startDate = startDate;
                } catch (DateTimeException e) {
                    throw new IllegalArgumentException("Invalid start date: " + e.getMessage());
                }
            }
            return this;
        }

        public StudyObjectiveBuilder setDuration(Double duration) {
            this.duration = duration;
            return this;
        }

        public StudyObjectiveBuilder setObjectiveInOneLine(String objectiveInOneLine) {
            this.objectiveInOneLine = objectiveInOneLine;
            return this;
        }

        public StudyObjectiveBuilder setObjectiveFullDescription(String objectiveFullDescription) {
            this.objectiveFullDescription = objectiveFullDescription;
            return this;
        }

        public StudyObjectiveBuilder setMotivation(String motivation) {
            this.motivation = motivation;
            return this;
        }

        public StudyObjective build() {
            StudyObjective studyObjective = new StudyObjective(this.title, this.description);
            studyObjective.topic = this.topic;
            studyObjective.practicedDays = this.practicedDays;
            studyObjective.startDate = this.startDate;
            studyObjective.duration = this.duration;
            studyObjective.objectiveInOneLine = this.objectiveInOneLine;
            studyObjective.objectiveFullDescription = this.objectiveFullDescription;
            studyObjective.motivation = this.motivation;
            return studyObjective;
        }
    }

    // Constructor for StudyObjective
    public StudyObjective(String title, String description) {
        this.title = title;
        this.description = description;
        this.name = title;
    }

    // Refactor handleSetObjectiveAdapter method to use the builder pattern
    public int handleSetObjectiveAdapter(List<Integer> intProperties, List<String> stringProperties, Double duration, boolean isActive) {
        try {
            // Verifique se a data fornecida é válida
            LocalDateTime startDate = LocalDateTime.of(intProperties.get(3), intProperties.get(4), intProperties.get(5), 0, 0);

            // Se a data for válida, construa o StudyObjective
            StudyObjective studyObjective = new StudyObjectiveBuilder()
                    .setTitle(stringProperties.get(0))
                    .setDescription(stringProperties.get(1))
                    .setTopic(stringProperties.get(2))
                    .setObjectiveInOneLine(stringProperties.get(3))
                    .setObjectiveFullDescription(stringProperties.get(4))
                    .setMotivation(stringProperties.get(5))
                    .setPracticedDays(intProperties.get(0))
                    .setStartDate(startDate) // Usando a data validada
                    .setDuration(duration)
                    .build();

            handleSetRegistry(intProperties.get(0), stringProperties.get(0), intProperties.get(1), isActive);
            return intProperties.get(0);
        } catch (DateTimeException e) {
            System.out.println("Invalid date provided: " + e.getMessage());
            return -1; // Retorna -1 se a data for inválida
        }
    }

    // Other existing methods
    public void handleSetRegistry(Integer id, String name, Integer priority, boolean isActive) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.isActive = isActive;
    }

    public void handleSetTextualInfo(String title, String description, String topic, String objectiveInOneLine, String objectiveFullDescription, String motivation) {
        this.title = title;
        this.description = description;
        this.topic = topic;
        this.objectiveInOneLine = objectiveInOneLine;
        this.objectiveFullDescription = objectiveFullDescription;
        this.motivation = motivation;
    }

    public void handleSetTime(Integer practicedDays, int day, int month, int year, Double duration) {
        this.practicedDays = practicedDays;
        this.duration = duration;
        this.startDate = LocalDateTime.of(year, month, day, 0, 0);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void handleSetObjective(StudyObjective objective) {
        // Lógica para processar o novo StudyObjective
        // Por exemplo, atualizando propriedades ou realizando ações específicas
        this.title = objective.getTitle();
        this.description = objective.getDescription();
        this.topic = objective.getTopic();
        this.practicedDays = objective.getPracticedDays();
        this.startDate = objective.getStartDate();
        this.duration = objective.getDuration();
        this.objectiveInOneLine = objective.getObjectiveInOneLine();
        this.objectiveFullDescription = objective.getObjectiveFullDescription();
        this.motivation = objective.getMotivation();
    }
}
