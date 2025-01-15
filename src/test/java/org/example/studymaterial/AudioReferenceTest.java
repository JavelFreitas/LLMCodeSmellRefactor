package org.example.studymaterial;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AudioReferenceTest {
    AudioReference audioReference = new AudioReference(AudioReference.AudioQuality.LOW);

    @BeforeEach
    void setUp() {
        List<String> properties = List.of("Title Test", "Description Test", "Link Test", "AccessRights Test", "License Test", "Language Test");
        List<Integer> intProperties = List.of(10, 2000, 500);
        editAudioReferenceTest(properties, intProperties, AudioReference.AudioQuality.LOW, true);
    }

    void editAudioReferenceTest(List<String> properties, List<Integer> intProperties, AudioReference.AudioQuality quality, boolean isDownloadable) {
        // Create an AudioBuilder and populate it with provided values
        AudioReference.AudioBuilder builder = new AudioReference.AudioBuilder()
                .setAudioQuality(quality)
                .setDownloadable(isDownloadable)
                .setTitle(properties.get(0))       // Title
                .setDescription(properties.get(1)) // Description
                .setLink(properties.get(2))        // Link
                .setAccessRights(properties.get(3)) // Access Rights
                .setLicense(properties.get(4))     // License
                .setLanguage(properties.get(5))    // Language
                .setRating(intProperties.get(0))   // Rating
                .setViewCount(intProperties.get(1)) // View Count
                .setShareCount(intProperties.get(2)) // Share Count
                .build();

        // Apply the changes to the audioReference instance
        this.audioReference.editAudio(builder);
    }


    @Test
    @Order(1)
    @DisplayName("Edit Audio String Properties Adapter Test")
    void editAudioStringPropertiesAdapter() {
        assertEquals("AccessRights Test", audioReference.getAccessRights());
        assertEquals("Description Test", audioReference.getDescription());
        assertEquals("Language Test", audioReference.getLanguage());
        assertEquals("License Test", audioReference.getLicense());
        assertEquals("Title Test", audioReference.getTitle());
        assertEquals("Link Test", audioReference.getLink());

    }

    @Test
    @Order(2)
    @DisplayName("Edit Audio Integer, Boolean And Quality Properties Adapter Test")
    void editAudioIntegerBooleanQualityAdapter() {
        assertEquals(AudioReference.AudioQuality.LOW, audioReference.getAudioQuality());
        assertEquals(2000, audioReference.getViewCount());
        assertEquals(500, audioReference.getShareCount());
        assertEquals(10, audioReference.getRating());
        assertTrue(audioReference.getIsDownloadable());
    }

    public void changeEditTest(){
        List<String> properties = List.of("Title2 Test", "Description2 Test", "Link2 Test", "AccessRights2 Test", "License2 Test", "Language2 Test");
        List<Integer> intProperties = List.of(20, 4000, 1000);
        editAudioReferenceTest(properties, intProperties, AudioReference.AudioQuality.VERY_HIGH, false);
    }


    @Test
    @Order(3)
    @DisplayName("Change Edit Audio String Properties Adapter Test")
    void changeEditAudioIntegerAndBooleanAdapter() {
        changeEditTest();
        assertEquals("Title2 Test", audioReference.getTitle());
        assertEquals("Description2 Test", audioReference.getDescription());
        assertEquals("Link2 Test", audioReference.getLink());
        assertEquals("AccessRights2 Test", audioReference.getAccessRights());
        assertEquals("License2 Test", audioReference.getLicense());
        assertEquals("Language2 Test", audioReference.getLanguage());
    }

    @Test
    @Order(4)
    @DisplayName("Change Edit Audio Integer, Boolean And Quality Properties Adapter Test")
    void changeEditAudioIntegerBooleanQualityAdapter() {
        changeEditTest();
        assertEquals(AudioReference.AudioQuality.VERY_HIGH, audioReference.getAudioQuality());
        assertEquals(4000, audioReference.getViewCount());
        assertEquals(1000, audioReference.getShareCount());
        assertEquals(20, audioReference.getRating());
        assertFalse(audioReference.getIsDownloadable());
    }
}