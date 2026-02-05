package org.example.studymaterial;

public class VideoReference extends Reference {
    private boolean isAvailable;
    private String resolution;
    private String frameRate;
    private String videoFormat;

    public VideoReference(String title, String description) {
        this(true, title, description, null, null, null, null); // Default constructor
    }

    public VideoReference(boolean isAvailable, String title, String description,
                          String resolution, String frameRate, String videoFormat,
                          String accessRights) {

        super(new ReferenceMetadata(
                title,
                description,
                null, // Assuming link is optional
                accessRights,
                null, // Assuming license is optional
                false, // Assuming downloadable is optional
                0, // Assuming rating is optional
                null, // Assuming language is optional
                0, // Assuming viewCount is optional
                0, // Assuming downloadCount is optional
                0 // Assuming shareCount is optional
        ));

        this.isAvailable = isAvailable;
        this.resolution = resolution;
        this.frameRate = frameRate;
        this.videoFormat = videoFormat;
    }

    public void editAvailability(boolean isAvailable, boolean isDownloadable){
        this.isAvailable = isAvailable;
        this.setDownloadable(isDownloadable);
    }

    public boolean handleStreamAvailability(){
        if(!isAvailable){
            return false;
        } else if(!this.getIsDownloadable()){
            return false;
        }
        return true;

    }
}
