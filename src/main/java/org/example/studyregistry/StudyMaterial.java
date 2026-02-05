package org.example.studyregistry;
import org.example.studymaterial.AudioReference;
import org.example.studymaterial.Reference;
import org.example.studymaterial.TextReference;
import org.example.studymaterial.VideoReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudyMaterial{
    List<Reference> references;
    private static StudyMaterial studyMaterial;
    private Map<String, Integer> referenceCount;

    private StudyMaterial(){
        references = new ArrayList<Reference>();
    }

    public static StudyMaterial getStudyMaterial(){
        if(studyMaterial == null){
            studyMaterial = new StudyMaterial();
        }
        return studyMaterial;
    }

    public List<String> handleSearch(String text) {
        return searchInMaterials(text);
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
        Map<String, Integer> response = initializeResponseMap();

        for (Reference reference : references) {
            processReference(response, reference);
        }

        setReferenceCount(response);
        return response;
    }

    private void processReference(Map<String, Integer> response, Reference reference) {
        if (reference instanceof AudioReference) {
            incrementCount(response, "Audio References");
        } else if (reference instanceof VideoReference && ((VideoReference) reference).handleStreamAvailability()) {
            incrementCount(response, "Video References");
        } else if (reference instanceof TextReference && ((TextReference) reference).handleTextAccess()) {
            incrementCount(response, "Text References");
        }
    }

    private Map<String, Integer> initializeResponseMap() {
        Map<String, Integer> response = new HashMap<>();
        response.put("Audio References", 0);
        response.put("Video References", 0);
        response.put("Text References", 0);
        return response;
    }

    private void incrementCount(Map<String, Integer> response, String key) {
        response.put(key, response.get(key) + 1);
    }


}
