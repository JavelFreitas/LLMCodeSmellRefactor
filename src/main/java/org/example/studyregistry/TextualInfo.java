package org.example.studyregistry;

public class TextualInfo {
    private String title;
    private String description;
    private String topic;
    private String objectiveInOneLine;
    private String objectiveFullDescription;
    private String motivation;

    public TextualInfo(String title, String description, String topic,
                       String objectiveInOneLine, String objectiveFullDescription,
                       String motivation) {
        validateFields(title, description);
        this.title = title;
        this.description = description;
        this.topic = topic;
        this.objectiveInOneLine = objectiveInOneLine;
        this.objectiveFullDescription = objectiveFullDescription;
        this.motivation = motivation;
    }

    private void validateFields(String title, String description) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
    }

    public String formatSummary() {
        return String.format("%s: %s [%s]",
                title,
                objectiveInOneLine != null ? objectiveInOneLine : description,
                topic != null ? topic : "No topic"
        );
    }

    public String getDetailedDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(title).append("\n");
        sb.append("Description: ").append(description).append("\n");
        if (topic != null) sb.append("Topic: ").append(topic).append("\n");
        if (objectiveFullDescription != null) sb.append("Full Objective: ").append(objectiveFullDescription).append("\n");
        if (motivation != null) sb.append("Motivation: ").append(motivation);
        return sb.toString();
    }

    public boolean matchesSearch(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) return false;
        searchTerm = searchTerm.toLowerCase();
        return title.toLowerCase().contains(searchTerm) ||
                description.toLowerCase().contains(searchTerm) ||
                (topic != null && topic.toLowerCase().contains(searchTerm));
    }

    // Manter apenas getters essenciais
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getTopic() { return topic; }
    public String getObjectiveInOneLine() { return objectiveInOneLine; }
    public String getObjectiveFullDescription() { return objectiveFullDescription; }
    public String getMotivation() { return motivation; }
}