package org.example.studyregistry;

public class ObjectiveDetails {
    private final String topic;
    private final String objectiveInOneLine;
    private final String objectiveFullDescription;
    private final String motivation;

    public ObjectiveDetails(String topic, String objectiveInOneLine, String objectiveFullDescription, String motivation) {
        this.topic = topic;
        this.objectiveInOneLine = objectiveInOneLine;
        this.objectiveFullDescription = objectiveFullDescription;
        this.motivation = motivation;
    }

    public String getTopic() {
        return topic;
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
}
