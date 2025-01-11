package org.example.studymaterial;

import org.example.studymaterial.dtos.AudioMetadata;
import org.example.studymaterial.dtos.AudioStatistics;

import java.util.List;

public class AudioReference extends Reference {
    public enum AudioQuality {
        LOW, MEDIUM, HIGH, VERY_HIGH;
    }
    private AudioQuality audioQuality;

    public AudioReference(AudioQuality quality){
        this.audioQuality = quality;
    }

    public AudioQuality getAudioQuality() {
        return audioQuality;
    }

    public static AudioQuality audioQualityAdapter(String quality){
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

    public void editAudio(AudioQuality audioQuality, boolean isDownloadable, AudioMetadata metadata, AudioStatistics statistics) {
        editBasic(metadata.getTitle(), metadata.getDescription(), metadata.getLink());
        this.setAccessRights(metadata.getAccessRights());
        this.setLicense(metadata.getLicense());
        this.setAudioQuality(audioQuality);
        editVideoAttributes(statistics.getRating(), metadata.getLanguage(), statistics.getViewCount(), statistics.getShareCount(), isDownloadable);
    }

    public void editAudioAdapter(List<String> properties, List<Integer> intProperties, AudioQuality audioQuality, boolean isDownloadable) {
        AudioMetadata metadata = new AudioMetadata(
                properties.get(0), properties.get(1), properties.get(2),
                properties.get(3), properties.get(4), properties.get(5)
        );

        AudioStatistics statistics = new AudioStatistics(
                intProperties.get(0), intProperties.get(1), intProperties.get(2)
        );

        this.editAudio(audioQuality, isDownloadable, metadata, statistics);
    }

     private void editVideoAttributes(int rating, String language, int viewCount, int shareCount,boolean isDownloadable){
         this.setRating(rating);
         this.setShareCount(shareCount);
         this.setViewCount(viewCount);
         this.setDownloadable(isDownloadable);
         this.setLanguage(language);
     }

     public void editBasic(String title, String description, String link){
         this.setTitle(title);
         this.setDescription(description);
         this.setLink(link);
     }

}
