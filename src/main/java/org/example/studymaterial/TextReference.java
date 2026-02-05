package org.example.studymaterial;

public class TextReference extends Reference {
    private int wordCount;
    private String format;

    public TextReference(String title, String language, int wordCount, String format, String accessRights) {
        this.wordCount = wordCount;
        this.format = format;
        this.setTitle(title);
        this.setLanguage(language);
        this.setAccessRights(accessRights);
    }

    @Override
    protected String getContentType() {
        return "TEXT";
    }

    public int getWordCount() {
        return wordCount;
    }

    public String getFormat() {
        return format;
    }

    public void editAccess(String accessRights, String format, int wordCount) {
        this.setAccessRights(accessRights);
        this.format = format;
        this.wordCount = wordCount;
    }

    public boolean handleTextAccess() {
        return "Public".equals(getAccessRights())
                && "pdf".equals(this.format)
                && this.wordCount > 0;
    }
}