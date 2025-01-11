package org.example.studymaterial.dtos;

import java.util.Objects;

public final class AudioMetadata {

    private final String title;
    private final String description;
    private final String link;
    private final String accessRights;
    private final String license;
    private final String language;

    // Constructor to initialize fields
    public AudioMetadata(String title, String description, String link, String accessRights, String license, String language) {
        this.title = Objects.requireNonNull(title, "Title cannot be null");
        this.description = Objects.requireNonNull(description, "Description cannot be null");
        this.link = Objects.requireNonNull(link, "Link cannot be null");
        this.accessRights = Objects.requireNonNull(accessRights, "Access rights cannot be null");
        this.license = Objects.requireNonNull(license, "License cannot be null");
        this.language = Objects.requireNonNull(language, "Language cannot be null");
    }

    // Behavior: Check if the metadata is complete (not empty) and valid
    public boolean isValid() {
        return !title.isEmpty() && !description.isEmpty() && !link.isEmpty();
    }

    // Behavior: Return formatted details of the metadata
    public String getFormattedDetails() {
        return String.format("Title: %s, Description: %s, Link: %s, Access Rights: %s, License: %s, Language: %s",
                title, description, link, accessRights, license, language);
    }

    // Behavior: A method to compare two AudioMetadata objects based on title and link (e.g., for deduplication)
    public boolean isEqualTo(AudioMetadata other) {
        return this.title.equals(other.title) && this.link.equals(other.link);
    }

    // Getters (to support access, can be useful if necessary for other parts of the application)
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getLink() { return link; }
    public String getAccessRights() { return accessRights; }
    public String getLicense() { return license; }
    public String getLanguage() { return language; }
}
