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

    protected void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
    }

    protected void updateMetadata(String title, String description, String link) {
        validateTitle(title);
        this.title = title;
        this.description = description;
        this.link = link;
    }

    protected void updateAccessControl(String accessRights, String license, boolean isDownloadable) {
        this.accessRights = accessRights;
        this.license = license;
        this.isDownloadable = isDownloadable;
    }

    protected void incrementEngagement() {
        this.viewCount++;
    }

    protected void recordDownload() {
        if (isDownloadable) {
            this.downloadCount++;
        }
    }

    protected void recordShare() {
        this.shareCount++;
    }

    protected boolean isAccessible() {
        return "Public".equals(accessRights);
    }

    protected void updateRating(int newRating) {
        if (newRating < 0 || newRating > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }
        this.rating = newRating;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsDownloadable() {
        return isDownloadable;
    }

    // Essential getters
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getLink() { return link; }
    public String getAccessRights() { return accessRights; }
    public String getLicense() { return license; }
    public boolean isDownloadable() { return isDownloadable; }
    public int getRating() { return rating; }
    public String getLanguage() { return language; }
    public int getViewCount() { return viewCount; }
    public int getDownloadCount() { return downloadCount; }
    public int getShareCount() { return shareCount; }


    protected void setStats(int rating, String language, int viewCount, int shareCount) {
        this.rating = rating;
        this.language = language;
        this.viewCount = viewCount;
        this.shareCount = shareCount;
    }
}