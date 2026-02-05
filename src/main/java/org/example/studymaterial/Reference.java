package org.example.studymaterial;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

// Enum for access levels
enum AccessLevel {
    PUBLIC, PRIVATE, RESTRICTED, UNKNOWN
}

public abstract class Reference {
    private String title;
    private String description;
    private String link;
    private String accessRights;
    private String license;
    private boolean isDownloadable;
    private int rating;
    private String language;
    private int viewCount;
    private int downloadCount;
    private int shareCount;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public String getAccessRights() {
        return accessRights;
    }

    public void setAccessRights(String accessRights) {
        this.accessRights = accessRights;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public boolean getIsDownloadable() {
        return isDownloadable;
    }

    public void setDownloadable(boolean downloadable) {
        isDownloadable = downloadable;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    // Checks if the resource is available for download
    public boolean isAvailableForDownload() {
        return this.isDownloadable;
    }

    // Increments the view count
    public void incrementViewCount() {
        this.viewCount++;
    }

    public void incrementDownloadCount() {
        if (this.isDownloadable) {
            this.downloadCount++;
        }
    }

    public void incrementShareCount() {
        this.shareCount++;
    }

    public boolean isPopular() {
        int popularityThreshold = 100;
        return viewCount > popularityThreshold && shareCount > popularityThreshold / 2;
    }

    public boolean isRelevantToTopic(String topic) {
        return title.toLowerCase().contains(topic.toLowerCase()) ||
                description.toLowerCase().contains(topic.toLowerCase());
    }

    public String getBriefDescription() {
        String[] words = description.split("\\s+");
        return String.join(" ", Arrays.copyOfRange(words, 0, Math.min(words.length, 50)));
    }

    public double calculatePopularityScore() {
        double viewCountNormalized = (double) viewCount / 1000;
        double downloadCountNormalized = (double) downloadCount / 100;
        double shareCountNormalized = (double) shareCount / 50;
        return viewCountNormalized + downloadCountNormalized + shareCountNormalized;
    }

    public AccessLevel getAccessLevel() {
        if (accessRights.equals("Public")) {
            return AccessLevel.PUBLIC;
        } else if (accessRights.equals("Private")) {
            return AccessLevel.PRIVATE;
        } else if (accessRights.equals("Restricted")) {
            return AccessLevel.RESTRICTED;
        } else {
            return AccessLevel.UNKNOWN;
        }
    }

    public boolean hasCopyrightRestrictions() {
        List<String> restrictiveLicenses = Arrays.asList("All Rights Reserved", "Copyright Protected");
        return restrictiveLicenses.contains(license);
    }

    public boolean isCompatibleWithLanguage(String targetLanguage) {
        return language.equals(targetLanguage);
    }

    public String getInferredResourceType() {
        if (link.endsWith(".pdf")) {
            return "Document";
        } else if (link.endsWith(".mp4") || link.endsWith(".avi")) {
            return "Video";
        } else if (title.contains("Presentation") || link.endsWith(".pptx")) {
            return "Presentation";
        } else {
            return "Unknown";
        }
    }

    public boolean isOpenAccess() {
        List<String> openLicenses = Arrays.asList("CC BY", "CC BY-SA", "Public Domain");
        return openLicenses.contains(license);
    }

    public String getPrimaryLanguage() {
        return language;
    }

    public String classifyContentType() {
        if (title.contains("tutorial") || description.contains("how to")) {
            return "Tutorial";
        } else if (title.contains("paper") || description.contains("research")) {
            return "Research Paper";
        } else {
            return "Other";
        }
    }

    public double calculateEngagementScore() {
        return viewCount * 0.5 + downloadCount * 0.3 + shareCount * 0.2;
    }

    public boolean isMobileFriendly() {
        return link.contains("m.") || link.endsWith(".pdf") || link.endsWith(".epub");
    }

    public boolean isOfflineAccessible() {
        return isDownloadable;
    }

    public String getShortTitle() {
        return title.substring(0, Math.min(title.length(), 50));
    }

    public boolean isRelevantToQuery(String query) {
        return title.toLowerCase().contains(query.toLowerCase()) ||
                description.toLowerCase().contains(query.toLowerCase());
    }

    public boolean containsMultimedia() {
        return title.toLowerCase().contains("video") || title.toLowerCase().contains("image") ||
                description.toLowerCase().contains("video") || description.toLowerCase().contains("image");
    }

    public List<String> extractKeywords() {
        String text = title + " " + description;
        String[] words = text.split("\\s+");
        return Arrays.asList(words).subList(0, Math.min(words.length, 5));
    }

    public boolean isViral() {
        return shareCount > 1000 && viewCount > 10000;
    }

    public String getShortTitle(int numberOfWords) {
        String[] words = title.split("\\s+");
        return String.join(" ", Arrays.copyOfRange(words, 0, Math.min(numberOfWords, words.length)));
    }

    public boolean isTutorial() {
        return title.toLowerCase().contains("tutorial") || description.toLowerCase().contains("tutorial") ||
                title.toLowerCase().contains("how to") || description.toLowerCase().contains("how to");
    }

    public boolean isSuitableForAllAudiences() {
        return !title.toLowerCase().contains("offensive") && !description.toLowerCase().contains("offensive");
    }

    public String generateUniqueId() {
        return title.substring(0, 5) + "_" + link.hashCode();
    }

    public void editReferenceAttributes(List<String> properties, List<Integer> intProperties) {
        this.setTitle(properties.get(0));
        this.setDescription(properties.get(1));
        this.setLink(properties.get(2));
        this.setAccessRights(properties.get(3));
        this.setLicense(properties.get(4));
        this.setLanguage(properties.get(5));
        this.setRating(intProperties.get(0));
        this.setViewCount(intProperties.get(1));
        this.setShareCount(intProperties.get(2));
    }
}
