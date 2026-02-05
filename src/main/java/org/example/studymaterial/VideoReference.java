package org.example.studymaterial;

public class VideoReference extends Reference {
    private boolean isAvailable;
    private String resolution;
    private String frameRate;
    private String videoFormat;

    public VideoReference(String title, String description) {
        updateMetadata(title, description, "");
    }

    public VideoReference(boolean isAvailable, String title, String description,
                          String resolution, String frameRate, String videoFormat,
                          String accessRights) {
        updateMetadata(title, description, "");
        this.isAvailable = isAvailable;
        this.resolution = resolution;
        this.frameRate = frameRate;
        this.videoFormat = videoFormat;
        updateAccessControl(accessRights, "", false);
    }

    @Override
    public boolean isCountable() {
        return handleStreamAvailability();
    }

    public void editAvailability(boolean isAvailable, boolean isDownloadable) {
        this.isAvailable = isAvailable;
        updateAccessControl(getAccessRights(), getLicense(), isDownloadable);
    }

    public boolean handleStreamAvailability() {
        return isAvailable && isDownloadable();
    }
}
