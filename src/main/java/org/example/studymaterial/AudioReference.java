package org.example.studymaterial;

public class AudioReference extends Reference {
    public enum AudioQuality {
        LOW, MEDIUM, HIGH, VERY_HIGH;
    }
    private AudioQuality audioQuality;

    public AudioReference(AudioQuality quality) {
        this.audioQuality = quality;
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

    public void editAudio(AudioBuilder builder) {
        editBasic(builder.title, builder.description, builder.link);
        this.setAccessRights(builder.accessRights);
        this.setLicense(builder.license);
        this.setAudioQuality(builder.audioQuality);
        this.setLanguage(builder.language);
        this.setRating(builder.rating);
        this.setViewCount(builder.viewCount);
        this.setShareCount(builder.shareCount);
        this.setDownloadable(builder.isDownloadable);
    }

    public void editBasic(String title, String description, String link) {
        this.setTitle(title);
        this.setDescription(description);
        this.setLink(link);
    }

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

        public AudioBuilder setAudioQuality(AudioQuality audioQuality) {
            this.audioQuality = audioQuality;
            return this;
        }

        public AudioBuilder setDownloadable(boolean isDownloadable) {
            this.isDownloadable = isDownloadable;
            return this;
        }

        public AudioBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public AudioBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public AudioBuilder setLink(String link) {
            this.link = link;
            return this;
        }

        public AudioBuilder setAccessRights(String accessRights) {
            this.accessRights = accessRights;
            return this;
        }

        public AudioBuilder setLicense(String license) {
            this.license = license;
            return this;
        }

        public AudioBuilder setLanguage(String language) {
            this.language = language;
            return this;
        }

        public AudioBuilder setRating(int rating) {
            this.rating = rating;
            return this;
        }

        public AudioBuilder setViewCount(int viewCount) {
            this.viewCount = viewCount;
            return this;
        }

        public AudioBuilder setShareCount(int shareCount) {
            this.shareCount = shareCount;
            return this;
        }

        public AudioBuilder build() {
            return this;
        }
    }
}
