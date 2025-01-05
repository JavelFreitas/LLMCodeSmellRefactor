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
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("O título não pode ser nulo ou em branco.");
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
        if (rating < 0 || rating > 20) {
            throw new IllegalArgumentException("A classificação deve estar entre 0 e 20.");
        }
        this.rating = rating;
    }

    public void setDownloadCount(int downloadCount) {
        if (downloadCount < 0) {
            throw new IllegalArgumentException("O número de downloads não pode ser negativo.");
        }
        this.downloadCount = downloadCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        if (shareCount < 0) {
            throw new IllegalArgumentException("O número de compartilhamentos não pode ser negativo.");
        }
        this.shareCount = shareCount;
    }

    public String getLanguage() {
        return language;
    }

    private static void validate(String language, int viewCount, int downloadCount, int shareCount) {
        if (language == null || language.isBlank()) {
            throw new IllegalArgumentException("O idioma não pode ser nulo ou em branco.");
        }
        if (viewCount < 0) {
            throw new IllegalArgumentException("O número de visualizações não pode ser negativo.");
        }
        if (downloadCount < 0) {
            throw new IllegalArgumentException("O número de downloads não pode ser negativo.");
        }
        if (shareCount < 0) {
            throw new IllegalArgumentException("O número de compartilhamentos não pode ser negativo.");
        }
    }

    public void setLanguage(String language) {
        if (language == null || language.isBlank()) {
            throw new IllegalArgumentException("O idioma não pode ser nulo ou em branco.");
        }
        this.language = language;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        if (viewCount < 0) {
            throw new IllegalArgumentException("O número de visualizações não pode ser negativo.");
        }
        this.viewCount = viewCount;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    @Override
    public String toString() {
        return "Reference{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", accessRights='" + accessRights + '\'' +
                ", license='" + license + '\'' +
                ", isDownloadable=" + isDownloadable +
                ", rating=" + rating +
                ", language='" + language + '\'' +
                ", viewCount=" + viewCount +
                ", downloadCount=" + downloadCount +
                ", shareCount=" + shareCount +
                '}';
    }
}
