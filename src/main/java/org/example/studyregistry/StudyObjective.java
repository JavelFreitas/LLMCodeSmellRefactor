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
    private String motivation;

    // Getters
    public String getTitle() { return title; }
    public String getTopic() { return topic; }
    public Integer getPracticedDays() { return practicedDays; }
    public LocalDateTime getStartDate() { return startDate; }
    public Double getDuration() { return duration; }
    public String getObjectiveInOneLine() { return objectiveInOneLine; }
    public String getObjectiveFullDescription() { return objectiveFullDescription; }
    public String getMotivation() { return motivation; }

    // Setters
    public void setTitle(String title) { this.title = title; }
    public void setTopic(String topic) { this.topic = topic; }
    public void setPracticedDays(Integer practicedDays) { this.practicedDays = practicedDays; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }
    public void setObjectiveInOneLine(String objectiveInOneLine) { this.objectiveInOneLine = objectiveInOneLine; }
    public void setObjectiveFullDescription(String objectiveFullDescription) { this.objectiveFullDescription = objectiveFullDescription; }
    public void setMotivation(String motivation) { this.motivation = motivation; }
    public void setDuration(Double duration) { this.duration = duration; }

    // Métodos herdados de Registry
    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    @Override
    public void setPriority(Integer priority) {
        super.setPriority(priority);
    }

    @Override
    public void setActive(boolean isActive) {
        super.setActive(isActive);
    }

    @Override
    public String toString(){
        return "StudyObjective [title:" + title + ", description:" + description + 
               (topic != null ? ", topic:" + topic : "") +
               (practicedDays != null ? ", practicedDays:" + practicedDays : "") + 
               (duration != null ? ", duration:" + duration : "") +
               (objectiveInOneLine != null ? ", objective summary:" + objectiveInOneLine : "") + 
               (objectiveFullDescription != null ? ", objective full description:" + objectiveFullDescription : "") +
               (motivation != null ? ", motivation:" + motivation : "") + "]";
    }

    public StudyObjective(String title, String description) {
        this.title = title;
        this.description = description;
        setName(title);
    }

    public void handleSetRegistry(Long id, String name, Integer priority, boolean isActive){
        setId(id);
        setName(name);
        setPriority(priority);
        setActive(isActive);
    }

    public void handleSetTextualInfo(String title, String description, String topic, 
                                     String objectiveInOneLine, String objectiveFullDescription, 
                                     String motivation){
        this.title = title;
        this.description = description;
        this.topic = topic;
        this.objectiveInOneLine = objectiveInOneLine;
        this.objectiveFullDescription = objectiveFullDescription;
        this.motivation = motivation;
    }

    public void handleSetTime(Integer practicedDays, int day, int month, int year, Double duration){
        this.practicedDays = practicedDays;
        this.duration = duration;
        this.startDate = LocalDateTime.of(year, month, day, 0, 0);
    }

    // Método refatorado para aceitar um objeto de parâmetros
    public void handleSetObjective(ObjectiveSetParams params) {
        handleSetRegistry(params.getId(), params.getName(), params.getPriority(), params.isActive());
        handleSetTextualInfo(params.getTitle(), params.getDescription(), params.getTopic(),
                            params.getObjectiveInOneLine(), params.getObjectiveFullDescription(),
                            params.getMotivation());
        handleSetTime(params.getPracticedDays(), params.getDay(), params.getMonth(), 
                     params.getYear(), params.getDuration());
    }

    // Método adaptador ajustado para usar o objeto de parâmetros
    public int handleSetObjectiveAdapter(List<Integer> intProperties, 
                                        List<String> stringProperties, 
                                        Double duration, 
                                        boolean isActive){
        if(intProperties.size() < 6 || stringProperties.size() < 7){
            throw new IllegalArgumentException("Listas de propriedades não contêm elementos suficientes.");
        }

        try {
            Long id = Long.valueOf(intProperties.get(0)) ;
            Integer priority = intProperties.get(1);
            Integer practicedDays = intProperties.get(2);
            int day = intProperties.get(3);
            int month = intProperties.get(4);
            int year = intProperties.get(5);

            String name = stringProperties.get(0);
            String title = stringProperties.get(1);
            String description = stringProperties.get(2);
            String topic = stringProperties.get(3);
            String objectiveInOneLine = stringProperties.get(4);
            String objectiveFullDescription = stringProperties.get(5);
            String motivation = stringProperties.get(6);

            ObjectiveSetParams params = new ObjectiveSetParams(
                id, priority, practicedDays, day, month, year,
                name, title, description, topic, objectiveInOneLine, 
                objectiveFullDescription, motivation, duration, isActive
            );

            handleSetObjective(params);

            return id.intValue();
        } catch (NumberFormatException e) {
            System.out.println("Erro na conversão de tipos: " + e.getMessage());
            return -1;
        } catch (IllegalArgumentException e) {
            System.out.println("Erro na configuração do objetivo: " + e.getMessage());
            return -1;
        }
    }

    // Outros métodos permanecem inalterados
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
