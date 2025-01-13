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

    // Encapsulamento do campo title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title != null && !title.isEmpty()) {
            this.title = title;
        } else {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
    }

    // Encapsulamento do campo description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null && !description.isEmpty()) {
            this.description = description;
        } else {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
    }

    // Encapsulamento do campo link
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        if (link != null && !link.isEmpty()) {
            this.link = link;
        } else {
            throw new IllegalArgumentException("Link cannot be null or empty");
        }
    }

    // Encapsulamento do campo accessRights
    public String getAccessRights() {
        return accessRights;
    }

    public void setAccessRights(String accessRights) {
        if (accessRights != null && !accessRights.isEmpty()) {
            this.accessRights = accessRights;
        } else {
            throw new IllegalArgumentException("Access rights cannot be null or empty");
        }
    }

    // Encapsulamento do campo license
    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        if (license != null && !license.isEmpty()) {
            this.license = license;
        } else {
            throw new IllegalArgumentException("License cannot be null or empty");
        }
    }

    // Encapsulamento do campo isDownloadable
    public boolean isDownloadable() {
        return isDownloadable;
    }

    public void setDownloadable(boolean downloadable) {
        this.isDownloadable = downloadable;
    }

    // Encapsulamento do campo rating
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        // Ajustando o valor de rating para estar no intervalo 0-5
        if (rating < 0) {
            this.rating = 0;  // Se o rating for menor que 0, define 0
        } else {
            this.rating = rating;  // Caso contrário, mantém o valor
        }
    }
    // Encapsulamento do campo language
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        if (language != null && !language.isEmpty()) {
            this.language = language;
        } else {
            throw new IllegalArgumentException("Language cannot be null or empty");
        }
    }

    // Encapsulamento do campo viewCount
    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        if (viewCount >= 0) {
            this.viewCount = viewCount;
        } else {
            throw new IllegalArgumentException("View count cannot be negative");
        }
    }

    // Encapsulamento do campo downloadCount
    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        if (downloadCount >= 0) {
            this.downloadCount = downloadCount;
        } else {
            throw new IllegalArgumentException("Download count cannot be negative");
        }
    }

    // Encapsulamento do campo shareCount
    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        if (shareCount >= 0) {
            this.shareCount = shareCount;
        } else {
            throw new IllegalArgumentException("Share count cannot be negative");
        }
    }
}
