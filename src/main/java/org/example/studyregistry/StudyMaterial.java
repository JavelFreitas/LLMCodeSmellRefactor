
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

    public List<String> searchInMaterials(String text) {
        List<String> response = new ArrayList<>();
        for (Reference reference : references) {
            if (reference.matchesSearch(text)) {
                response.add(reference.getTitle());
            }
        }
        return response;
    }
    
    private void updateReferenceCount(Reference reference, Map<String, Integer> countMap) {
        if (reference.shouldBeCounted()) {
            String key = reference.getTypeCountKey();
            Integer count = countMap.get(key);
            countMap.put(key, count + 1);
        }
}
}