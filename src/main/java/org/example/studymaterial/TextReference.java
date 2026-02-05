package org.example.studymaterial;

public class TextReference extends Reference{
    private int wordCount;
    private String format;

    public TextReference(String title, String language, int wordCount, String format, String accessRights) {
        super(new ReferenceMetadata(
                title,
                null, // Assuming description is optional
                null, // Assuming link is optional
                accessRights,
                null, // Assuming license is optional
                false, // Assuming downloadable is optional
                0, // Assuming rating is optional
                language,
                0, // Assuming viewCount is optional
                0, // Assuming downloadCount is optional
                0 // Assuming shareCount is optional
        ));
        this.wordCount = wordCount;
        this.format = format;
    }

    public void editAccess(String accessRights, String format, int wordCount) {
        this.setAccessRights(accessRights);
        this.format = format;
        this.wordCount = wordCount;
    }

    public boolean handleTextAccess(){
        if(getAccessRights() != "Public"){
            return false;
        } else if (this.format != "pdf"){
            return false;
        } else if (this.wordCount == 0){
            return false;
        }
        return true;
    }

}
