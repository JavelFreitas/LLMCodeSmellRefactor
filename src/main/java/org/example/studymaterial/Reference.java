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

    protected static final int MIN_RATING = 1;
    protected static final int MAX_RATING = 20;

    // Métodos necessários para os testes
    public String getTitle() { return title; }
    public void setTitle(String title) {
        validateNotEmpty(title, "Title");
        this.title = title;
    }

    public String getDescription() { return description; }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() { return link; }
    public void setLink(String link) {
        validateNotEmpty(link, "Link");
        this.link = link;
    }

    public String getAccessRights() { return accessRights; }
    public void setAccessRights(String accessRights) {
        this.accessRights = accessRights;
    }

    public String getLicense() { return license; }
    public void setLicense(String license) {
        this.license = license;
    }

    public boolean getIsDownloadable() { return isDownloadable; }
    public void setDownloadable(boolean downloadable) {
        this.isDownloadable = downloadable;
    }

    public int getRating() { return rating; }
    public void setRating(int rating) {
        validateRating(rating);
        this.rating = rating;
    }

    public String getLanguage() { return language; }
    public void setLanguage(String language) {
        validateNotEmpty(language, "Language");
        this.language = language;
    }

    public int getViewCount() { return viewCount; }
    public void setViewCount(int viewCount) {
        validatePositiveNumber(viewCount, "View count");
        this.viewCount = viewCount;
    }

    public int getDownloadCount() { return downloadCount; }
    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public int getShareCount() { return shareCount; }
    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    private void validateNotEmpty(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " não pode estar vazio");
        }
    }

    private void validateRating(int rating) {
        if (rating < MIN_RATING || rating > MAX_RATING) {
            throw new IllegalArgumentException("Rating deve estar entre " + MIN_RATING + " e " + MAX_RATING);
        }
    }

    private void validatePositiveNumber(int value, String fieldName) {
        if (value < 0) {
            throw new IllegalArgumentException(fieldName + " deve ser positivo");
        }
    }

    protected boolean isPublicAccess() {
        return "Open".equalsIgnoreCase(this.accessRights);
    }

    // Comportamentos de Classificação
    protected abstract String getContentType();

    protected boolean isHighlyRated() {
        return rating >= (MAX_RATING * 0.8);
    }

    protected boolean needsLicenseVerification() {
        return !license.equalsIgnoreCase("Open");
    }

    // Comportamentos de Acesso
    protected boolean canBeShared() {
        return isPublicAccess() || license.equalsIgnoreCase("Shareable");
    }

    protected boolean hasRestrictedAccess() {
        return !isPublicAccess();
    }

    // Comportamentos de Monitoramento
    protected boolean isActivelyUsed() {
        return viewCount > 100 || downloadCount > 10;
    }

    protected double getPopularityScore() {
        return (viewCount * 0.5) + (downloadCount * 0.3) + (shareCount * 0.2);
    }

    protected String getContentCategory() {
        if (isHighlyRated() && isActivelyUsed()) return "PREMIUM";
        if (isActivelyUsed()) return "ACTIVE";
        return "STANDARD";
    }

    // Comportamentos de Validação de Estado
    protected boolean isComplete() {
        return title != null && description != null && link != null
                && language != null && accessRights != null && license != null;
    }

    protected boolean requiresAttention() {
        return !isComplete() || (isPublicAccess() && hasRestrictedAccess());
    }
}