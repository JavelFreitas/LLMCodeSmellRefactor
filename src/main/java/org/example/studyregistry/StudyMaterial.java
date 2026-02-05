package org.example.studyregistry;
import org.example.studymaterial.AudioReference;
import org.example.studymaterial.Reference;
import org.example.studymaterial.TextReference;
import org.example.studymaterial.VideoReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.example.studysearch.SearchLog;

public class StudyMaterial {
    private List<Reference> references;
    private static StudyMaterial studyMaterial;
    private Map<String, Integer> referenceCount;
    private SearchLog searchLog;

    private StudyMaterial() {
        references = new ArrayList<Reference>();
        searchLog = new SearchLog("Material Search");
    }

    public List<String> search(String text) {
        List<String> results = new ArrayList<>();
        results.addAll(searchInMaterials(text));
        results.add("\nLogged in: " + searchLog.getLogName());
        return results;
    }

    public SearchLog getSearchLog() {
        return searchLog;
    }

    public static StudyMaterial getStudyMaterial(){
        if(studyMaterial == null){
            studyMaterial = new StudyMaterial();
        }
        return studyMaterial;
    }

    public void addReference(Reference ref){
        references.add(ref);
    }

    List<Reference> getReferences(){
        return references;
    }

    public List<Reference> getTypeReference(Reference type){
        List<Reference> response = new ArrayList<>();
        for(Reference reference : references){
            if(reference.getClass() == type.getClass()){
                response.add(reference);
            }
        }
        return response;
    }

    public void setReferenceCount(Map<String, Integer> referenceCount) {
        this.referenceCount = referenceCount;
    }

    public List<String> searchInMaterials(String text){
        List<String> response = new ArrayList<>();
        for(Reference reference : references){
            String mix = (reference.getTitle() != null ? reference.getTitle() : "") + (reference.getDescription() != null ? reference.getDescription() : "");
            if (mix.toLowerCase().contains(text.toLowerCase())){
                response.add(reference.getTitle());
            }
        }
        return response;
    }

    public Map<String, Integer> getReferenceCountMap() {
        Map<String, Integer> response = createEmptyReferenceMap();
        countAllReferences(response);
        setReferenceCount(response);
        return response;
    }

    private Map<String, Integer> createEmptyReferenceMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("Audio References", 0);
        map.put("Video References", 0);
        map.put("Text References", 0);
        return map;
    }

    private void countAllReferences(Map<String, Integer> map) {
        for (Reference reference : references) {
            processReferenceCount(reference, map);
        }
    }

    private void processReferenceCount(Reference reference, Map<String, Integer> map) {
        if (reference instanceof AudioReference) {
            incrementCount("Audio References", map);
        } else if (reference instanceof VideoReference) {
            processVideoReference((VideoReference) reference, map);
        } else if (reference instanceof TextReference) {
            processTextReference((TextReference) reference, map);
        }
    }

    private void processVideoReference(VideoReference reference, Map<String, Integer> map) {
        if (reference.handleStreamAvailability()) {
            incrementCount("Video References", map);
        }
    }

    private void processTextReference(TextReference reference, Map<String, Integer> map) {
        if (reference.handleTextAccess()) {
            incrementCount("Text References", map);
        }
    }

    private void incrementCount(String key, Map<String, Integer> map) {
        map.put(key, map.get(key) + 1);
    }

    private Map<String, Integer> initializeCountMap() {
        Map<String, Integer> response = new HashMap<>();
        response.put("Audio References", 0);
        response.put("Video References", 0);
        response.put("Text References", 0);
        return response;
    }

    private void updateReferenceCount(Reference reference, Map<String, Integer> countMap) {
        if (reference.getClass() == AudioReference.class) {
            incrementAudioCount(countMap);
        } else if (reference.getClass() == VideoReference.class) {
            incrementVideoCount(reference, countMap);
        } else if (reference.getClass() == TextReference.class) {
            incrementTextCount(reference, countMap);
        }
    }

    private void incrementAudioCount(Map<String, Integer> countMap) {
        Integer audioCount = countMap.get("Audio References");
        countMap.put("Audio References", audioCount + 1);
    }

    private void incrementVideoCount(Reference reference, Map<String, Integer> countMap) {
        if (((VideoReference) reference).handleStreamAvailability()) {
            Integer videoCount = countMap.get("Video References");
            countMap.put("Video References", videoCount + 1);
        }
    }

    private void incrementTextCount(Reference reference, Map<String, Integer> countMap) {
        if (((TextReference) reference).handleTextAccess()) {
            Integer textCount = countMap.get("Text References");
            countMap.put("Text References", textCount + 1);
        }
    }

}
