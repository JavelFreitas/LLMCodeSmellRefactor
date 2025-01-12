package org.example.studymaterial;

import java.util.List;

public class AudioReference extends Reference {
    public enum AudioQuality {
        LOW, MEDIUM, HIGH, VERY_HIGH;
    }

    private AudioQuality audioQuality;
    private AudioAttributes audioAttributes;

    public AudioReference(AudioQuality quality) {
        this.audioQuality = quality;
        this.audioAttributes = new AudioAttributes(); // Initialize AudioAttributes
    }

    public AudioQuality getAudioQuality() {
        return audioQuality;
    }

    public static AudioQuality audioQualityAdapter(String quality) {
        return switch (quality.toLowerCase()) {
            case "low" -> AudioQuality.LOW;
            case "medium" -> AudioQuality.MEDIUM;
            case "high" -> AudioQuality.HIGH;
            case "very_high" -> AudioQuality.VERY_HIGH;
            default -> null;
        };
    }

    public void setAudioQuality(AudioQuality audioQuality) {
        this.audioQuality = audioQuality;
    }

    public void editAudio(AudioQuality audioQuality, boolean isDownloadable, String title, String description, String link, String accessRights, String license, String language, int rating, int viewCount, int shareCount) {
        editBasic(title, description, link);
        this.setAccessRights(accessRights);
        this.setLicense(license);
        this.setAudioQuality(audioQuality);
        this.audioAttributes.setRating(rating);
        this.audioAttributes.setLanguage(language);
        this.audioAttributes.setViewCount(viewCount);
        this.audioAttributes.setShareCount(shareCount);
        this.audioAttributes.setDownloadable(isDownloadable);
    }

    public void editAudioAdapter(List<String> properties, List<Integer> intProperties, AudioQuality audioQuality, boolean isDownloadable) {
        this.editAudio(audioQuality, isDownloadable, properties.get(0), properties.get(1), properties.get(2), properties.get(3), properties.get(4), properties.get(5), intProperties.get(0), intProperties.get(1), intProperties.get(2));
    }


    public void editBasic(String title, String description, String link) {
        this.setTitle(title);
        this.setDescription(description);
        this.setLink(link);
    }
}