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
            String mix = (reference.getTitle() != null ? reference.getTitle() : "")
                    + (reference.getDescription() != null ? reference.getDescription() : "");
            if (mix.toLowerCase().contains(text.toLowerCase())) {
                response.add(reference.getTitle());
            }
        }
        return response;
    }

    public Map<String, Integer> getReferenceCountMap() {
        Map<String, Integer> response = initializeCountMap();

        for (Reference reference : references) {
            updateReferenceCount(reference, response);
        }

        setReferenceCount(response);
        return response;
    }

    private Map<String, Integer> initializeCountMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("Audio References", 0);
        map.put("Video References", 0);
        map.put("Text References", 0);
        return map;
    }

    private void updateReferenceCount(Reference reference, Map<String, Integer> countMap) {
        if (reference.getClass() == AudioReference.class) {
            updateAudioCount(countMap);
        } else if (reference.getClass() == VideoReference.class) {
            updateVideoCount(reference, countMap);
        } else if (reference.getClass() == TextReference.class) {
            updateTextCount(reference, countMap);
        }
    }

    private void updateAudioCount(Map<String, Integer> countMap) {
        Integer count = countMap.get("Audio References");
        countMap.put("Audio References", count + 1);
    }

    private void updateVideoCount(Reference reference, Map<String, Integer> countMap) {
        if (((VideoReference) reference).handleStreamAvailability()) {
            Integer count = countMap.get("Video References");
            countMap.put("Video References", count + 1);
        }
    }

    private void updateTextCount(Reference reference, Map<String, Integer> countMap) {
        if (((TextReference) reference).handleTextAccess()) {
            Integer count = countMap.get("Text References");
            countMap.put("Text References", count + 1);
        }
    }

}
