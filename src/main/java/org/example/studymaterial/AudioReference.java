package org.example.studymaterial;

import java.util.List;

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

    public static class BasicDetails {
        private String title;
        private String description;
        private String link;

        public BasicDetails(String title, String description, String link) {
            this.title = title;
            this.description = description;
            this.link = link;
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

        public boolean isValidLink() {
            return link != null && link.startsWith("http");
        }

        public String formatForDisplay() {
            return title + ": " + description + " (" + link + ")";
        }
    }

    public static class AudioAttributesConfig {
        private final AudioQuality audioQuality;
        private final boolean isDownloadable;
        private final BasicDetails basicDetails;
        private final String accessRights;
        private final String license;
        private final String language;
        private final int rating;
        private final int viewCount;
        private final int shareCount;

        private AudioAttributesConfig(Builder builder) {
            this.audioQuality = builder.audioQuality;
            this.isDownloadable = builder.isDownloadable;
            this.basicDetails = builder.basicDetails;
            this.accessRights = builder.accessRights;
            this.license = builder.license;
            this.language = builder.language;
            this.rating = builder.rating;
            this.viewCount = builder.viewCount;
            this.shareCount = builder.shareCount;
        }

        public boolean isEngaging() {
            return viewCount > 500 && shareCount > 50;
        }

        public String summarize() {
            return "Quality: " + audioQuality + ", Views: " + viewCount +
                    ", Shares: " + shareCount + ", License: " + license;
        }

        public AudioAttributes toAudioAttributes() {
            return new AudioAttributes(this);
        }

        public void validateLicense() {
            if (license == null || license.isBlank()) {
                throw new IllegalArgumentException("License must be provided.");
            }
        }

        public static class Builder {
            private AudioQuality audioQuality;
            private boolean isDownloadable;
            private BasicDetails basicDetails;
            private String accessRights;
            private String license;
            private String language;
            private int rating;
            private int viewCount;
            private int shareCount;

            public Builder setAudioQuality(AudioQuality audioQuality) {
                this.audioQuality = audioQuality;
                return this;
            }

            public Builder setDownloadable(boolean isDownloadable) {
                this.isDownloadable = isDownloadable;
                return this;
            }

            public Builder setBasicDetails(BasicDetails basicDetails) {
                this.basicDetails = basicDetails;
                return this;
            }

            public Builder setAccessRights(String accessRights) {
                this.accessRights = accessRights;
                return this;
            }

            public Builder setLicense(String license) {
                this.license = license;
                return this;
            }

            public Builder setLanguage(String language) {
                this.language = language;
                return this;
            }

            public Builder setRating(int rating) {
                this.rating = rating;
                return this;
            }

            public Builder setViewCount(int viewCount) {
                this.viewCount = viewCount;
                return this;
            }

            public Builder setShareCount(int shareCount) {
                this.shareCount = shareCount;
                return this;
            }

            public AudioAttributesConfig build() {
                return new AudioAttributesConfig(this);
            }
        }
    }

    public static class AudioAttributes {
        private final AudioQuality audioQuality;
        private final boolean isDownloadable;
        private final BasicDetails basicDetails;
        private final String accessRights;
        private final String license;
        private final String language;
        private final int rating;
        private final int viewCount;
        private final int shareCount;

        public AudioAttributes(AudioAttributesConfig config) {
            this.audioQuality = config.audioQuality;
            this.isDownloadable = config.isDownloadable; // Configuração correta
            this.basicDetails = config.basicDetails;
            this.accessRights = config.accessRights;
            this.license = config.license;
            this.language = config.language;
            this.rating = config.rating;
            this.viewCount = config.viewCount;
            this.shareCount = config.shareCount;
        }

        public boolean isPopular() {
            return rating >= 4 && viewCount > 1000;
        }
        public boolean isDownloadable() { // Método Getter
            return isDownloadable;
        }

        public AudioQuality getAudioQuality() {
            return audioQuality;
        }

        public boolean isWellLicensed() {
            return license != null && !license.isBlank();
        }

        public String display() {
            return "Title: " + basicDetails.getTitle() + ", Quality: " + audioQuality +
                    ", Views: " + viewCount + ", Downloads: " + isDownloadable;
        }

        public String getLanguage() {
            return this.language;
        }

        public int getViewCount() {
            return viewCount;
        }

        public int getShareCount() {
            return shareCount;
        }

        public int getRating() {
            return rating;
        }

    }

    public void editBasic(String title, String description, String link) {
        this.setTitle(title);
        this.setDescription(description);
        this.setLink(link);
    }

    public void editBasic(BasicDetails details) {
        editBasic(details.getTitle(), details.getDescription(), details.getLink());
    }

    public void editAudio(AudioAttributes attributes) {
        editBasic(attributes.basicDetails);
        this.setAccessRights(attributes.accessRights);
        this.setLicense(attributes.license);
        this.setAudioQuality(attributes.audioQuality);
        this.setRating(attributes.rating);
        this.setShareCount(attributes.shareCount);
    }

    public void editAudioAdapter(List<String> properties, List<Integer> intProperties, AudioQuality audioQuality, boolean isDownloadable) {
        validateProperties(properties, intProperties);

        BasicDetails basicDetails = createBasicDetails(properties);

        AudioAttributesConfig config = buildAudioAttributesConfig(properties, intProperties, audioQuality, isDownloadable, basicDetails);

        applyAudioAttributes(config);
    }

    private void validateProperties(List<String> properties, List<Integer> intProperties) {
        if (properties.size() < 6) {
            throw new IllegalArgumentException("Properties list must contain at least 6 elements (title, description, link, accessRights, license, language).");
        }
        if (intProperties.size() < 3) {
            throw new IllegalArgumentException("IntProperties list must contain at least 3 elements (rating, viewCount, shareCount).");
        }
    }

    private BasicDetails createBasicDetails(List<String> properties) {
        return new BasicDetails(properties.get(0), properties.get(1), properties.get(2));
    }

    private AudioAttributesConfig buildAudioAttributesConfig(List<String> properties, List<Integer> intProperties, AudioQuality audioQuality, boolean isDownloadable, BasicDetails basicDetails) {
        return new AudioAttributesConfig.Builder()
                .setAudioQuality(audioQuality)
                .setDownloadable(isDownloadable)
                .setBasicDetails(basicDetails)
                .setAccessRights(properties.get(3))
                .setLicense(properties.get(4))
                .setLanguage(properties.get(5)) // Configurando language
                .setRating(intProperties.get(0))
                .setViewCount(intProperties.get(1))
                .setShareCount(intProperties.get(2))
                .build();
    }

    private void applyAudioAttributes(AudioAttributesConfig config) {
        AudioAttributes attributes = config.toAudioAttributes();
        this.setLanguage(attributes.getLanguage()); // Transferindo language para AudioReference
        this.setViewCount(attributes.getViewCount());
        this.setShareCount(attributes.getShareCount());
        this.setRating(attributes.getRating());
        this.setDownloadable(attributes.isDownloadable());
        editAudio(attributes);
    }




    @Override
    public boolean isValidForCounting() {
        return true;
    }
}
