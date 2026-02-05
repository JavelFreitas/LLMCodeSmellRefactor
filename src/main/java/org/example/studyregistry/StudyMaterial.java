package org.example.studyregistry;
import org.example.studymaterial.AudioReference;
import org.example.studymaterial.Reference;
import org.example.studymaterial.TextReference;
import org.example.studymaterial.VideoReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudyMaterial {
    List<Reference> references;
    private static StudyMaterial studyMaterial;
    private Map<String, Integer> referenceCount;

    private StudyMaterial() {
        references = new ArrayList<Reference>();
    }

    public static StudyMaterial getStudyMaterial() {
        if (studyMaterial == null) {
            studyMaterial = new StudyMaterial();
        }
        return studyMaterial;
    }

    public void addReference(Reference ref) {
        references.add(ref);
    }

    List<Reference> getReferences() {
        return references;
    }

    public List<Reference> getTypeReference(Reference type) {
        List<Reference> response = new ArrayList<>();
        for (Reference reference : references) {
            if (reference.getClass() == type.getClass()) {
                response.add(reference);
            }
        }
        return response;
    }

    public void setReferenceCount(Map<String, Integer> referenceCount) {
        this.referenceCount = referenceCount;
    }

    public List<String> searchInMaterials(String text) {
        List<String> response = new ArrayList<>();
        for (Reference reference : references) {
            String mix = (reference.getTitle() != null ? reference.getTitle() : "") + (reference.getDescription() != null ? reference.getDescription() : "");
            if (mix.toLowerCase().contains(text.toLowerCase())) {
                response.add(reference.getTitle());
            }
        }
        return response;
    }

    public Map<String, Integer> getReferenceCountMap() {
        Map<String, Integer> response = new HashMap<>();
        initializeReferenceCount(response);

        response.putAll(countAudioReferences());
        response.putAll(countVideoReferences());
        response.putAll(countTextReferences());

        setReferenceCount(response);
        return response;
    }

    private void initializeReferenceCount(Map<String, Integer> response) {
        response.put("Audio References", 0);
        response.put("Video References", 0);
        response.put("Text References", 0);
    }

    private Map<String, Integer> countAudioReferences() {
        int audioCount = 0;

        for (Reference reference : references) {
            if (reference.getClass() == AudioReference.class) {
                audioCount++;
            }
        }

        Map<String, Integer> audioCounts = new HashMap<>();
        audioCounts.put("Audio References", audioCount);
        return audioCounts;
    }

    private Map<String, Integer> countVideoReferences() {
        int videoCount = 0;

        for (Reference reference : references) {
            if (reference.getClass() == VideoReference.class && ((VideoReference) reference).handleStreamAvailability()) {
                videoCount++;
            }
        }

        Map<String, Integer> videoCounts = new HashMap<>();
        videoCounts.put("Video References", videoCount);
        return videoCounts;
    }

    private Map<String, Integer> countTextReferences() {
        int textCount = 0;

        for (Reference reference : references) {
            if (reference.getClass() == TextReference.class && ((TextReference) reference).handleTextAccess()) {
                textCount++;
            }
        }

        Map<String, Integer> textCounts = new HashMap<>();
        textCounts.put("Text References", textCount);
        return textCounts;
    }
}
