package org.example.studymaterial;

public class AudioAttributes {
    private int rating;
    private String language;
    private int viewCount;
    private int shareCount;
    private boolean isDownloadable;

    public AudioAttributes() {}

    public AudioAttributes(int rating, String language, int viewCount, int shareCount, boolean isDownloadable) {
        this.rating = rating;
        this.language = language;
        this.viewCount = viewCount;
        this.shareCount = shareCount;
        this.isDownloadable = isDownloadable;
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

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    public boolean isDownloadable() {
        return isDownloadable;
    }

    public void setDownloadable(boolean downloadable) {
        isDownloadable = downloadable;
    }
}
