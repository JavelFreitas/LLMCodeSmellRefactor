package org.example.studymaterial;

public abstract class Reference {
    private ReferenceMetadata metadata;

    public Reference(ReferenceMetadata metadata) {
        this.metadata = metadata;
    }

    public void setTitle(String title) {
        metadata = new ReferenceMetadata(
                title,
                metadata.description(),
                metadata.link(),
                metadata.accessRights(),
                metadata.license(),
                metadata.isDownloadable(),
                metadata.rating(),
                metadata.language(),
                metadata.viewCount(),
                metadata.downloadCount(),
                metadata.shareCount()
        );
    }

    public String getTitle() {
        return metadata.title();
    }

    public void setDescription(String description) {
        metadata = new ReferenceMetadata(
                metadata.title(),
                description,
                metadata.link(),
                metadata.accessRights(),
                metadata.license(),
                metadata.isDownloadable(),
                metadata.rating(),
                metadata.language(),
                metadata.viewCount(),
                metadata.downloadCount(),
                metadata.shareCount()
        );
    }

    public String getDescription() {
        return metadata.description();
    }

    public void setLink(String link) {
        metadata = new ReferenceMetadata(
                metadata.title(),
                metadata.description(),
                link,
                metadata.accessRights(),
                metadata.license(),
                metadata.isDownloadable(),
                metadata.rating(),
                metadata.language(),
                metadata.viewCount(),
                metadata.downloadCount(),
                metadata.shareCount()
        );
    }

    public String getLink() {
        return metadata.link();
    }

    public String getAccessRights() {
        return metadata.accessRights();
    }

    public void setAccessRights(String accessRights) {
        metadata = new ReferenceMetadata(
                metadata.title(),
                metadata.description(),
                metadata.link(),
                accessRights,
                metadata.license(),
                metadata.isDownloadable(),
                metadata.rating(),
                metadata.language(),
                metadata.viewCount(),
                metadata.downloadCount(),
                metadata.shareCount()
        );
    }

    public String getLicense() {
        return metadata.license();
    }

    public void setLicense(String license) {
        metadata = new ReferenceMetadata(
                metadata.title(),
                metadata.description(),
                metadata.link(),
                metadata.accessRights(),
                license,
                metadata.isDownloadable(),
                metadata.rating(),
                metadata.language(),
                metadata.viewCount(),
                metadata.downloadCount(),
                metadata.shareCount()
        );
    }

    public boolean getIsDownloadable() {
        return metadata.isDownloadable();
    }

    public void setDownloadable(boolean downloadable) {
        metadata = new ReferenceMetadata(
                metadata.title(),
                metadata.description(),
                metadata.link(),
                metadata.accessRights(),
                metadata.license(),
                downloadable,
                metadata.rating(),
                metadata.language(),
                metadata.viewCount(),
                metadata.downloadCount(),
                metadata.shareCount()
        );
    }

    public int getRating() {
        return metadata.rating();
    }

    public void setRating(int rating) {
        metadata = new ReferenceMetadata(
                metadata.title(),
                metadata.description(),
                metadata.link(),
                metadata.accessRights(),
                metadata.license(),
                metadata.isDownloadable(),
                rating,
                metadata.language(),
                metadata.viewCount(),
                metadata.downloadCount(),
                metadata.shareCount()
        );
    }

    public String getLanguage() {
        return metadata.language();
    }

    public void setLanguage(String language) {
        metadata = new ReferenceMetadata(
                metadata.title(),
                metadata.description(),
                metadata.link(),
                metadata.accessRights(),
                metadata.license(),
                metadata.isDownloadable(),
                metadata.rating(),
                language,
                metadata.viewCount(),
                metadata.downloadCount(),
                metadata.shareCount()
        );
    }

    public int getViewCount() {
        return metadata.viewCount();
    }

    public void setViewCount(int viewCount) {
        metadata = new ReferenceMetadata(
                metadata.title(),
                metadata.description(),
                metadata.link(),
                metadata.accessRights(),
                metadata.license(),
                metadata.isDownloadable(),
                metadata.rating(),
                metadata.language(),
                viewCount,
                metadata.downloadCount(),
                metadata.shareCount()
        );
    }

    public int getDownloadCount() {
        return metadata.downloadCount();
    }

    public void setDownloadCount(int downloadCount) {
        metadata = new ReferenceMetadata(
                metadata.title(),
                metadata.description(),
                metadata.link(),
                metadata.accessRights(),
                metadata.license(),
                metadata.isDownloadable(),
                metadata.rating(),
                metadata.language(),
                metadata.viewCount(),
                downloadCount,
                metadata.shareCount()
        );
    }

    public int getShareCount() {
        return metadata.shareCount();
    }

    public void setShareCount(int shareCount) {
        metadata = new ReferenceMetadata(
                metadata.title(),
                metadata.description(),
                metadata.link(),
                metadata.accessRights(),
                metadata.license(),
                metadata.isDownloadable(),
                metadata.rating(),
                metadata.language(),
                metadata.viewCount(),
                metadata.downloadCount(),
                shareCount
        );
    }
}

record ReferenceMetadata(
        String title,
        String description,
        String link,
        String accessRights,
        String license,
        boolean isDownloadable,
        int rating,
        String language,
        int viewCount,
        int downloadCount,
        int shareCount
) {}
