package org.example.studymaterial;

import java.util.List;

public class AudioReference extends Reference {
    public enum AudioQuality {
        LOW, MEDIUM, HIGH, VERY_HIGH;
    }
    private AudioQuality audioQuality;

    @Override
    protected String getContentType() {
        return "AUDIO";
    }

    public AudioReference(AudioQuality quality){
        this.audioQuality = quality;
    }

    // Builder interno
    public static class AudioBuilder {
        private AudioQuality audioQuality;
        private boolean isDownloadable;
        private String title;
        private String description;
        private String link;
        private String accessRights;
        private String license;
        private String language;
        private int rating;
        private int viewCount;
        private int shareCount;

        public AudioBuilder() {}

        public AudioBuilder audioQuality(AudioQuality audioQuality) {
            this.audioQuality = audioQuality;
            return this;
        }

        public AudioBuilder downloadable(boolean isDownloadable) {
            this.isDownloadable = isDownloadable;
            return this;
        }

        public AudioBuilder title(String title) {
            this.title = title;
            return this;
        }

        public AudioBuilder description(String description) {
            this.description = description;
            return this;
        }

        public AudioBuilder link(String link) {
            this.link = link;
            return this;
        }

        public AudioBuilder accessRights(String accessRights) {
            this.accessRights = accessRights;
            return this;
        }

        public AudioBuilder license(String license) {
            this.license = license;
            return this;
        }

        public AudioBuilder language(String language) {
            this.language = language;
            return this;
        }

        public AudioBuilder rating(int rating) {
            this.rating = rating;
            return this;
        }

        public AudioBuilder viewCount(int viewCount) {
            this.viewCount = viewCount;
            return this;
        }

        public AudioBuilder shareCount(int shareCount) {
            this.shareCount = shareCount;
            return this;
        }
    }

    public void editAudio(AudioBuilder builder) {
        editBasic(builder.title, builder.description, builder.link);
        this.setAccessRights(builder.accessRights);
        this.setLicense(builder.license);
        this.setAudioQuality(builder.audioQuality);
        editVideoAttributes(builder.rating, builder.language, builder.viewCount,
                builder.shareCount, builder.isDownloadable);
    }

    public void editAudioAdapter(List<String> properties, List<Integer> intProperties,
                                 AudioQuality audioQuality, boolean isDownloadable) {
        AudioBuilder builder = new AudioBuilder()
                .audioQuality(audioQuality)
                .downloadable(isDownloadable)
                .title(properties.get(0))
                .description(properties.get(1))
                .link(properties.get(2))
                .accessRights(properties.get(3))
                .license(properties.get(4))
                .language(properties.get(5))
                .rating(intProperties.get(0))
                .viewCount(intProperties.get(1))
                .shareCount(intProperties.get(2));

        editAudio(builder);
    }

    private void editVideoAttributes(int rating, String language, int viewCount,
                                     int shareCount, boolean isDownloadable) {
        this.setRating(rating);
        this.setShareCount(shareCount);
        this.setViewCount(viewCount);
        this.setDownloadable(isDownloadable);
        this.setLanguage(language);
    }

    public void editBasic(String title, String description, String link) {
        this.setTitle(title);
        this.setDescription(description);
        this.setLink(link);
    }

    // Getters e Setters existentes
    public AudioQuality getAudioQuality() {
        return audioQuality;
    }

    public void setAudioQuality(AudioQuality audioQuality) {
        this.audioQuality = audioQuality;
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
}