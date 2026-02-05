package org.example.studyregistry;

public class TextualProperties {
    public final String title;
    public final String description;
    public final String topic;
    public final String objectiveInOneLine;
    public final String objectiveFullDescription;
    public final String motivation;

    private TextualProperties(Builder builder) {
        this.title = builder.title;
        this.description = builder.description;
        this.topic = builder.topic;
        this.objectiveInOneLine = builder.objectiveInOneLine;
        this.objectiveFullDescription = builder.objectiveFullDescription;
        this.motivation = builder.motivation;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String title;
        private String description;
        private String topic;
        private String objectiveInOneLine;
        private String objectiveFullDescription;
        private String motivation;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder topic(String topic) {
            this.topic = topic;
            return this;
        }

        public Builder objectiveInOneLine(String objectiveInOneLine) {
            this.objectiveInOneLine = objectiveInOneLine;
            return this;
        }

        public Builder objectiveFullDescription(String objectiveFullDescription) {
            this.objectiveFullDescription = objectiveFullDescription;
            return this;
        }

        public Builder motivation(String motivation) {
            this.motivation = motivation;
            return this;
        }

        public TextualProperties build() {
            return new TextualProperties(this);
        }
    }
}