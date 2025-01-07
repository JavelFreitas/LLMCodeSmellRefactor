package org.example.studymaterial;

import java.util.HashMap;
import java.util.Map;

public abstract class Reference {
    private Map<String, Object> fields = new HashMap<>();

    // Setters
    public void setTitle(String title) {
        fields.put("title", title);
    }

    public void setDescription(String description) {
        fields.put("description", description);
    }

    public void setLink(String link) {
        fields.put("link", link);
    }

    public void setAccessRights(String accessRights) {
        fields.put("accessRights", accessRights);
    }

    public void setLicense(String license) {
        fields.put("license", license);
    }

    public void setDownloadable(boolean downloadable) {
        fields.put("isDownloadable", downloadable);
    }

    public void setRating(int rating) {
        fields.put("rating", rating);
    }

    public void setLanguage(String language) {
        fields.put("language", language);
    }

    public void setViewCount(int viewCount) {
        fields.put("viewCount", viewCount);
    }

    public void setDownloadCount(int downloadCount) {
        fields.put("downloadCount", downloadCount);
    }

    public void setShareCount(int shareCount) {
        fields.put("shareCount", shareCount);
    }

    // Getters
    public String getTitle() {
        return (String) fields.get("title");
    }

    public String getDescription() {
        return (String) fields.get("description");
    }

    public String getLink() {
        return (String) fields.get("link");
    }

    public String getAccessRights() {
        return (String) fields.get("accessRights");
    }

    public String getLicense() {
        return (String) fields.get("license");
    }

    public boolean getIsDownloadable() {
        return (boolean) fields.get("isDownloadable");
    }

    public int getRating() {
        return (int) fields.get("rating");
    }

    public String getLanguage() {
        return (String) fields.get("language");
    }

    public int getViewCount() {
        return (int) fields.get("viewCount");
    }

    public int getDownloadCount() {
        return (int) fields.get("downloadCount");
    }

    public int getShareCount() {
        return (int) fields.get("shareCount");
    }
}
