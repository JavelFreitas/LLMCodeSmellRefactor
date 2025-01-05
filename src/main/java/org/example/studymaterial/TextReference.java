package org.example.studymaterial;

public class TextReference extends Reference {
    private int wordCount;
    private String format;

    public TextReference(String title, String language, int wordCount, String format, String accessRights) {
        updateMetadata(title, null, null); // Set description to null initially
        this.wordCount = wordCount;
        this.format = format;
        updateAccessControl(accessRights, "", false);
    }

    public void editAccess(String accessRights, String format, int wordCount) {
        updateAccessControl(accessRights, "", false);
        this.format = format;
        this.wordCount = wordCount;
    }

    public boolean handleTextAccess() {
        return "Public".equals(getAccessRights()) &&
                "pdf".equals(format) &&
                wordCount > 0;
    }
}
