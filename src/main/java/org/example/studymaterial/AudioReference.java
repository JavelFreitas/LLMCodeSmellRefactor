package org.example.studymaterial;

import java.util.List;

public class AudioReference extends Reference {
    public static class AudioParameters {
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

        private AudioParameters(Builder builder) {
            validateParameters(builder);
            initializeFromBuilder(builder);
        }

        private void validateParameters(Builder builder) {
            if (builder.title == null || builder.title.isEmpty()) {
                throw new IllegalArgumentException("Title cannot be empty");
            }
        }

        private void initializeFromBuilder(Builder builder) {
            initializeBasicProperties(builder);
            initializeMetadata(builder);
            initializeStatistics(builder);
        }

        private void initializeBasicProperties(Builder builder) {
            this.audioQuality = builder.audioQuality;
            this.isDownloadable = builder.isDownloadable;
            this.title = builder.title;
            this.description = builder.description;
        }

        private void initializeMetadata(Builder builder) {
            this.link = builder.link;
            this.accessRights = builder.accessRights;
            this.license = builder.license;
            this.language = builder.language;
        }

        private void initializeStatistics(Builder builder) {
            this.rating = builder.rating;
            this.viewCount = builder.viewCount;
            this.shareCount = builder.shareCount;
        }

        public boolean isHighQuality() {
            return audioQuality == AudioQuality.HIGH || audioQuality == AudioQuality.VERY_HIGH;
        }

        public boolean isPopular() {
            return viewCount > 1000 && rating >= 4;
        }

        public String getFormattedDescription() {
            return String.format("%s [%s] - Quality: %s", title, language, audioQuality);
        }

        public double getPopularityScore() {
            return (viewCount * 0.4) + (rating * 0.4) + (shareCount * 0.2);
        }

        public static class Builder {
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

            public Builder audioQuality(AudioQuality audioQuality) {
                this.audioQuality = audioQuality;
                return this;
            }

            public Builder isDownloadable(boolean isDownloadable) {
                this.isDownloadable = isDownloadable;
                return this;
            }

            public Builder title(String title) {
                this.title = title;
                return this;
            }

            public Builder description(String description) {
                this.description = description;
                return this;
            }

            public Builder link(String link) {
                this.link = link;
                return this;
            }

            public Builder accessRights(String accessRights) {
                this.accessRights = accessRights;
                return this;
            }

            public Builder license(String license) {
                this.license = license;
                return this;
            }

            public Builder language(String language) {
                this.language = language;
                return this;
            }

            public Builder rating(int rating) {
                this.rating = rating;
                return this;
            }

            public Builder viewCount(int viewCount) {
                this.viewCount = viewCount;
                return this;
            }

            public Builder shareCount(int shareCount) {
                this.shareCount = shareCount;
                return this;
            }

            public AudioParameters build() {
                return new AudioParameters(this);
            }
        }

        // Getters
        public AudioQuality getAudioQuality() {
            return audioQuality;
        }

        public boolean isDownloadable() {
            return isDownloadable;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getLink() {
            return link;
        }

        public String getAccessRights() {
            return accessRights;
        }

        public String getLicense() {
            return license;
        }

        public String getLanguage() {
            return language;
        }

        public int getRating() {
            return rating;
        }

        public int getViewCount() {
            return viewCount;
        }

        public int getShareCount() {
            return shareCount;
        }

    }

    // Rest of AudioReference class...
    public void editAudio(AudioParameters params) {
        editBasic(params.getTitle(), params.getDescription(), params.getLink());
        this.setAccessRights(params.getAccessRights());
        this.setLicense(params.getLicense());
        this.setAudioQuality(params.getAudioQuality());
        editVideoAttributes(params.getRating(), params.getLanguage(),
                params.getViewCount(), params.getShareCount(), params.isDownloadable());
    }

    public void editAudioAdapter(List<String> properties, List<Integer> intProperties,
            AudioQuality audioQuality, boolean isDownloadable) {
        AudioParameters params = new AudioParameters.Builder()
                .audioQuality(audioQuality)
                .isDownloadable(isDownloadable)
                .title(properties.get(0))
                .description(properties.get(1))
                .link(properties.get(2))
                .accessRights(properties.get(3))
                .license(properties.get(4))
                .language(properties.get(5))
                .rating(intProperties.get(0))
                .viewCount(intProperties.get(1))
                .shareCount(intProperties.get(2))
                .build();

        editAudio(params);
    }

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

    @Override
    public boolean shouldBeCounted() {
        return true;
    }

}