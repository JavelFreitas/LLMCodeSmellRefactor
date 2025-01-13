
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

    // Validation methods
    protected void validateMetadata() {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (link == null || link.isEmpty()) {
            throw new IllegalArgumentException("Link cannot be empty");
        }
    }

    protected boolean isValidRating(int rating) {
        return rating >= 0 && rating <= 5;
    }

    // Content management methods
    public void updateContent(String title, String description, String link) {
        setTitle(title);
        setDescription(description);
        setLink(link);
        validateMetadata();
    }

    public void updateAccessControl(String accessRights, String license, boolean downloadable) {
        this.accessRights = accessRights;
        this.license = license;
        this.isDownloadable = downloadable;
    }

    // Search and metadata functionality
    public boolean matchesSearch(String searchText) {
        if (searchText == null || searchText.isEmpty()) {
            return false;
        }
        String content = getSearchableContent();
        return content.toLowerCase().contains(searchText.toLowerCase());
    }

    private String getSearchableContent() {
        StringBuilder content = new StringBuilder();
        if (title != null)
            content.append(title);
        if (description != null)
            content.append(" ").append(description);
        return content.toString();
    }

    public String getTypeCountKey() {
        if (this instanceof AudioReference)
            return "Audio References";
        if (this instanceof VideoReference)
            return "Video References";
        if (this instanceof TextReference)
            return "Text References";
        return "";
    }

    // Engagement tracking
    public void trackView() {
        this.viewCount++;
    }

    public void trackDownload() {
        this.downloadCount++;
        this.viewCount++;
    }

    public void trackShare() {
        this.shareCount++;
    }

    public double getEngagementScore() {
        return (viewCount * 0.4) + (rating * 0.4) + (shareCount * 0.2);
    }

    public boolean isPopular() {
        return viewCount > 1000 && rating >= 4;
    }

    // Abstract method for subclasses
    public abstract boolean shouldBeCounted();

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
}