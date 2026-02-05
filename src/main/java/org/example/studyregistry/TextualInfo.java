package org.example.studyregistry;

public class TextualInfo {
    private static final int MAX_TITLE_LENGTH = 100;
    private static final int MAX_DESCRIPTION_LENGTH = 500;

    private final String title;
    private final String description;
    private final String topic;
    private final String objectiveInOneLine;
    private final String objectiveFullDescription;
    private final String motivation;

    private TextualInfo(Builder builder) {
        this.title = validateTitle(builder.title);
        this.description = validateDescription(builder.description);
        this.topic = builder.topic;
        this.objectiveInOneLine = builder.objectiveInOneLine;
        this.objectiveFullDescription = builder.objectiveFullDescription;
        this.motivation = builder.motivation;
    }

    private String validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (title.length() > MAX_TITLE_LENGTH) {
            throw new IllegalArgumentException("Title exceeds maximum length of " + MAX_TITLE_LENGTH);
        }
        return title.trim();
    }

    private String validateDescription(String description) {
        if (description != null && description.length() > MAX_DESCRIPTION_LENGTH) {
            throw new IllegalArgumentException("Description exceeds maximum length of " + MAX_DESCRIPTION_LENGTH);
        }
        return description;
    }

    public String getFormattedObjective() {
        return String.format("%s: %s", title, objectiveInOneLine);
    }

    public String getDetailedInformation() {
        StringBuilder sb = new StringBuilder()
                .append("Title: ").append(title)
                .append("\nTopic: ").append(topic != null ? topic : "N/A")
                .append("\nDescription: ").append(description != null ? description : "N/A")
                .append("\nObjective: ").append(objectiveInOneLine != null ? objectiveInOneLine : "N/A")
                .append("\nDetailed Description: ").append(objectiveFullDescription != null ? objectiveFullDescription : "N/A")
                .append("\nMotivation: ").append(motivation != null ? motivation : "N/A");
        return sb.toString();
    }

    public boolean containsKeyword(String keyword) {
        return (title != null && title.toLowerCase().contains(keyword.toLowerCase())) ||
                (description != null && description.toLowerCase().contains(keyword.toLowerCase())) ||
                (topic != null && topic.toLowerCase().contains(keyword.toLowerCase()));
    }

    // Getters remain the same
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getTopic() { return topic; }
    public String getObjectiveInOneLine() { return objectiveInOneLine; }
    public String getObjectiveFullDescription() { return objectiveFullDescription; }
    public String getMotivation() { return motivation; }

    public static class Builder {
        private String title;
        private String description;
        private String topic;
        private String objectiveInOneLine;
        private String objectiveFullDescription;
        private String motivation;

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withTopic(String topic) {
            this.topic = topic;
            return this;
        }

        public Builder withObjectiveInOneLine(String objectiveInOneLine) {
            this.objectiveInOneLine = objectiveInOneLine;
            return this;
        }

        public Builder withObjectiveFullDescription(String objectiveFullDescription) {
            this.objectiveFullDescription = objectiveFullDescription;
            return this;
        }

        public Builder withMotivation(String motivation) {
            this.motivation = motivation;
            return this;
        }

        public TextualInfo build() {
            return new TextualInfo(this);
        }
    }

    @Override
    public String toString() {
        return getDetailedInformation();
    }
}
