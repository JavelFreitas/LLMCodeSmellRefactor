package org.example.studymaterial;

import java.util.List;

public class AudioReference extends Reference {
    public enum AudioQuality {
        LOW, MEDIUM, HIGH, VERY_HIGH;
    }

    private AudioQuality audioQuality;

    public AudioReference(AudioQuality quality) {
        this.audioQuality = quality;
    }

    public AudioQuality getAudioQuality() {
        return audioQuality;
    }

    public static AudioQuality audioQualityAdapter(String quality) {
        return switch (quality.toLowerCase()) {
            case "low" -> AudioQuality.LOW;
            case "medium" -> AudioQuality.MEDIUM;
            case "high" -> AudioQuality.HIGH;
            case "very_high" -> AudioQuality.VERY_HIGH;
            default -> null;
        };
    }

    public void setAudioQuality(AudioQuality audioQuality) {
        this.audioQuality = audioQuality;
    }

    public void editBasic(String title, String description, String link) {
        setTitle(title);
        setDescription(description);
        setLink(link);
    }

    public void editAudio(AudioQuality audioQuality, boolean isDownloadable) {
        this.setAudioQuality(audioQuality);
        this.setDownloadable(isDownloadable);
    }

    /**
     * 📌 Método adaptador para receber listas e chamar os métodos corretos.
     */
    public void editAudioAdapter(List<String> properties, List<Integer> intProperties, AudioQuality audioQuality, boolean isDownloadable) {
        // Atualiza os atributos da superclasse Reference
        editReferenceAttributes(properties, intProperties);

        // Atualiza os atributos específicos do AudioReference
        editAudio(audioQuality, isDownloadable);
    }
}
