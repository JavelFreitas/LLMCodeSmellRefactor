package org.example.studymaterial.dtos;

public class AudioStatistics {
    private int rating;
    private int viewCount;
    private int shareCount;

    // Constructor, getters, and setters
    public AudioStatistics(int rating, int viewCount, int shareCount) {
        this.rating = rating;
        this.viewCount = viewCount;
        this.shareCount = shareCount;
    }

    // Getters
    public int getRating() { return rating; }
    public int getViewCount() { return viewCount; }
    public int getShareCount() { return shareCount; }
}
