package org.example.studyregistry;

import org.example.studymaterial.AudioReference;
import org.example.studymaterial.Reference;
import org.example.studymaterial.TextReference;
import org.example.studymaterial.VideoReference;

import java.util.*;

public class StudyMaterial {
    List<Reference> references;
    private static StudyMaterial studyMaterial;
    private Map<String, Integer> referenceCount;
    private List<ReferenceCounter> counters;

    private StudyMaterial() {
        references = new ArrayList<>();
        initializeCounters();
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
        Map<String, Integer> response = initializeReferenceCountMap();

        for (Reference reference : references) {
            for (ReferenceCounter counter : counters) {
                if (counter.supports(reference)) {
                    counter.incrementCount(response, reference);
                }
            }
        }

        setReferenceCount(response);
        return response;
    }

    private Map<String, Integer> initializeReferenceCountMap() {
        Map<String, Integer> referenceCountMap = new HashMap<>();
        referenceCountMap.put("Audio References", 0);
        referenceCountMap.put("Video References", 0);
        referenceCountMap.put("Text References", 0);
        return referenceCountMap;
    }

    private void initializeCounters() {
        counters = new ArrayList<>();
        counters.add(new AudioReferenceCounter());
        counters.add(new VideoReferenceCounter());
        counters.add(new TextReferenceCounter());
    }

    interface ReferenceCounter {
        boolean supports(Reference reference);

        void incrementCount(Map<String, Integer> map, Reference reference);
    }

    static class AudioReferenceCounter implements ReferenceCounter {
        @Override
        public boolean supports(Reference reference) {
            return reference instanceof AudioReference;
        }

        @Override
        public void incrementCount(Map<String, Integer> map, Reference reference) {
            map.put("Audio References", map.get("Audio References") + 1);
        }
    }

    static class VideoReferenceCounter implements ReferenceCounter {
        @Override
        public boolean supports(Reference reference) {
            return reference instanceof VideoReference;
        }

        @Override
        public void incrementCount(Map<String, Integer> map, Reference reference) {
            if (((VideoReference) reference).handleStreamAvailability()) {
                map.put("Video References", map.get("Video References") + 1);
            }
        }
    }

    static class TextReferenceCounter implements ReferenceCounter {
        @Override
        public boolean supports(Reference reference) {
            return reference instanceof TextReference;
        }

        @Override
        public void incrementCount(Map<String, Integer> map, Reference reference) {
            if (((TextReference) reference).handleTextAccess()) {
                map.put("Text References", map.get("Text References") + 1);
            }
        }
    }
}