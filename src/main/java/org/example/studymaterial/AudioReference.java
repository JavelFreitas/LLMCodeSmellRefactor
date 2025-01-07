package org.example.studymaterial;

import java.util.List;

public class AudioReference extends Reference {
    public enum AudioQuality { LOW, MEDIUM, HIGH, VERY_HIGH }
    private AudioQuality audioQuality;

    // Make constructor public again since we need to create instances
    public AudioReference(AudioQuality quality) {
        this.audioQuality = quality;
    }

    public static Builder builder() {
        return new Builder();
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

        private static final int TITLE_INDEX = 0;
        private static final int DESCRIPTION_INDEX = 1;
        private static final int LINK_INDEX = 2;
        private static final int ACCESS_RIGHTS_INDEX = 3;
        private static final int LICENSE_INDEX = 4;
        private static final int LANGUAGE_INDEX = 5;

        private static final int RATING_INDEX = 0;
        private static final int VIEW_COUNT_INDEX = 1;
        private static final int SHARE_COUNT_INDEX = 2;

        private void setStringProperties(List<String> properties) {
            this.title = properties.get(TITLE_INDEX);
            this.description = properties.get(DESCRIPTION_INDEX);
            this.link = properties.get(LINK_INDEX);
            this.accessRights = properties.get(ACCESS_RIGHTS_INDEX);
            this.license = properties.get(LICENSE_INDEX);
            this.language = properties.get(LANGUAGE_INDEX);
        }

        private void setIntegerProperties(List<Integer> intProperties) {
            this.rating = intProperties.get(RATING_INDEX);
            this.viewCount = intProperties.get(VIEW_COUNT_INDEX);
            this.shareCount = intProperties.get(SHARE_COUNT_INDEX);
        }

        public Builder fromLists(List<String> properties, List<Integer> intProperties,
                                 AudioQuality audioQuality, boolean isDownloadable) {
            this.audioQuality = audioQuality;
            this.isDownloadable = isDownloadable;
            setStringProperties(properties);
            setIntegerProperties(intProperties);
            return this;
        }

        // Regular builder methods
        public Builder audioQuality(AudioQuality quality) {
            this.audioQuality = quality;
            return this;
        }

        public Builder downloadable(boolean isDownloadable) {
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

        public void updateExisting(AudioReference reference) {
            reference.editBasic(title, description, link);
            reference.updateAccessControl(accessRights, license, isDownloadable);
            reference.setStats(rating, language, viewCount, shareCount);
            reference.audioQuality = this.audioQuality;
        }

        public AudioReference build() {
            AudioReference reference = new AudioReference(audioQuality);
            updateExisting(reference);
            return reference;
        }
    }



    public void editBasic(String title, String description, String link) {
        updateMetadata(title, description, link);
    }

    public void editAudioAdapter(List<String> properties, List<Integer> intProperties,
                                 AudioQuality audioQuality, boolean isDownloadable) {
        Builder builder = builder()
                .fromLists(properties, intProperties, audioQuality, isDownloadable);
        builder.updateExisting(this);
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
