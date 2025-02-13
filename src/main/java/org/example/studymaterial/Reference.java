package org.example.studymaterial;

import java.util.List;
import java.util.Map;

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
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }
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

    public void setAccessRights(String accessRights) {
        this.accessRights = accessRights;
    }

    public String getAccessRights() {
        return accessRights;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getLicense() {
        return license;
    }

    public void setDownloadable(boolean downloadable) {
        isDownloadable = downloadable;
    }

    public boolean isDownloadable() {
        return isDownloadable;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public void setLanguage(String language) {
        if (language == null || language.isBlank()) {
            throw new IllegalArgumentException("Language cannot be empty.");
        }
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void incrementViewCount() {
        this.viewCount++;
    }

    public void setViewCount(int viewCount) {
        if (viewCount < 0) {
            throw new IllegalArgumentException("View count cannot be negative.");
        }
        this.viewCount = viewCount;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void incrementDownloadCount() {
        this.downloadCount++;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void incrementShareCount() {
        this.shareCount++;
    }

    public void setShareCount(int shareCount) {
        if (shareCount < 0) {
            throw new IllegalArgumentException("Share count cannot be negative.");
        }
        this.shareCount = shareCount;
    }

    public String getReferenceSummary() {
        return "Title: " + title + "\nDescription: " + description + "\nLink: " + link;
    }

    public boolean isPopular() {
        return this.viewCount > 1000 || this.downloadCount > 500;
    }

    public boolean isHighlyRated() {
        return this.rating >= 4;
    }

    public boolean isPubliclyAccessible() {
        return "Public".equals(this.accessRights);
    }

    public void share() {
        this.incrementShareCount();
        // Lógica adicional para compartilhar a referência
    }

    public void download() {
        if (!this.isDownloadable) {
            throw new IllegalStateException("This reference is not downloadable.");
        }
        this.incrementDownloadCount();
        // Lógica adicional para realizar o download
    }

    private boolean isValidLicense(String license) {
        return List.of("MIT", "GPL", "Apache").contains(license);
    }

    public abstract String getType();

    public abstract void incrementCount(Map<String, Integer> counts);
}