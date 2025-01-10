package org.example.studymaterial;

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

    // Constructor
    public Reference(String title, String description, String link, String accessRights,
                     String license, boolean isDownloadable, int rating, String language) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.accessRights = accessRights;
        this.license = license;
        this.isDownloadable = isDownloadable;
        this.rating = rating;
        this.language = language;
        this.viewCount = 0;
        this.downloadCount = 0;
        this.shareCount = 0;
    }

    // Encapsulated behaviors
    public void increaseViewCount() {
        this.viewCount++;
    }

    public void increaseDownloadCount() {
        if (isDownloadable) {
            this.downloadCount++;
        } else {
            throw new IllegalStateException("This reference is not downloadable.");
        }
    }

    public void increaseShareCount() {
        this.shareCount++;
    }

    public void updateRating(int newRating) {
        if (newRating >= 0 && newRating <= 5) {
            this.rating = newRating;
        } else {
            throw new IllegalArgumentException("Rating must be between 0 and 5.");
        }
    }

    public boolean canDownload() {
        return isDownloadable;
    }

    public boolean isPopular() {
        return this.viewCount > 1000 || this.downloadCount > 500;
    }

    public String getOverview() {
        return String.format("Title: %s\nDescription: %s\nLanguage: %s\nRating: %d/5\nViews: %d\nDownloads: %d\nShares: %d",
                title, description, language, rating, viewCount, downloadCount, shareCount);
    }

    // Getters and setters (kept for essential access)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public boolean isDownloadable() {
        return isDownloadable;
    }

    public void setDownloadable(boolean downloadable) {
        isDownloadable = downloadable;
    }

    public int getRating() {
        return rating;
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

    public int getDownloadCount() {
        return downloadCount;
    }

    public int getShareCount() {
        return shareCount;
    }
}
