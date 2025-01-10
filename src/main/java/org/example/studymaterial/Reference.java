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

    // New Behavior: Calculate popularity based on interaction counts
    public double calculatePopularity() {
        return (viewCount * 0.5) + (downloadCount * 0.3) + (shareCount * 0.2);
    }

    // New Behavior: Determine if the reference is highly rated
    public boolean isHighlyRated() {
        return rating >= 4;
    }

    // New Behavior: Generate a brief summary of the reference
    public String generateSummary() {
        return String.format(
                "Reference Summary:\nTitle: %s\nDescription: %s\nRating: %d\nLanguage: %s\nPopularity: %.2f",
                title, description, rating, language, calculatePopularity()
        );
    }

    // New Behavior: Check if the reference is shareable
    public boolean isShareable() {
        return shareCount > 0;
    }

    // New Behavior: Increment view count and track
    public void incrementView() {
        this.viewCount++;
    }

    // New Behavior: Increment download count with validation
    public void download() {
        if (!isDownloadable) {
            throw new UnsupportedOperationException("This reference is not downloadable.");
        }
        this.downloadCount++;
    }

    // New Behavior: Share the reference and track share count
    public void share() {
        this.shareCount++;
    }

    // Additional Behavior: Update multiple attributes together
    public void updateReference(String title, String description, String link, boolean isDownloadable) {
        setTitle(title);
        setDescription(description);
        setLink(link);
        setDownloadable(isDownloadable);
    }

    // Additional Behavior: Reset all counters
    public void resetCounters() {
        this.viewCount = 0;
        this.downloadCount = 0;
        this.shareCount = 0;
    }

    // Additional Behavior: Combine and summarize interactions
    public String interactionSummary() {
        return String.format(
                "Interactions Summary:\nViews: %d\nDownloads: %d\nShares: %d\nPopularity Score: %.2f",
                viewCount, downloadCount, shareCount, calculatePopularity()
        );
    }

    // Additional Behavior: Evaluate overall performance based on interactions and rating
    public String evaluatePerformance() {
        String performance = isHighlyRated() ? "Highly Rated" : "Needs Improvement";   
        return String.format(
                "Performance Evaluation:\n%s\nPopularity Score: %.2f",
                performance, calculatePopularity()
        );
    }
}
