package org.example.studymaterial;

import java.util.List;

public class AudioReference extends Reference {
    public enum AudioQuality { LOW, MEDIUM, HIGH, VERY_HIGH }
    private AudioQuality audioQuality;

    public AudioReference(AudioQuality quality) {
        this.audioQuality = quality;
    }

    public void editBasic(String title, String description, String link) {
        updateMetadata(title, description, link);
    }

    public void editAudioAdapter(List<String> properties, List<Integer> intProperties,
                                 AudioQuality audioQuality, boolean isDownloadable) {
        editAudio(audioQuality, isDownloadable,
                properties.get(0),    // title
                properties.get(1),    // description
                properties.get(2),    // link
                properties.get(3),    // accessRights
                properties.get(4),    // license
                properties.get(5),    // language
                intProperties.get(0), // rating
                intProperties.get(1), // viewCount
                intProperties.get(2)  // shareCount
        );
    }

    public void editAudio(AudioQuality audioQuality, boolean isDownloadable,
                          String title, String description, String link,
                          String accessRights, String license, String language,
                          int rating, int viewCount, int shareCount) {
        editBasic(title, description, link);
        updateAccessControl(accessRights, license, isDownloadable);
        setStats(rating, language, viewCount, shareCount);
        this.audioQuality = audioQuality;
    }

    public static AudioQuality audioQualityAdapter(String quality) {
        return switch (quality.toLowerCase()) {
            case "low" -> AudioQuality.LOW;
            case "medium" -> AudioQuality.MEDIUM;
            case "high" -> AudioQuality.HIGH;
            case "very_high" -> AudioQuality.VERY_HIGH;
            default -> throw new IllegalArgumentException("Invalid audio quality");
        };
    }

    public AudioQuality getAudioQuality() {
        return audioQuality;
    }
}
