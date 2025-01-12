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
        Map<String, Integer> response = initializeReferenceCountMap();

        for (Reference reference : references) {
            processReference(reference, response);
        }

        setReferenceCount(response);
        return response;
    }

    private void processReference(Reference reference, Map<String, Integer> response) {
        if (reference instanceof AudioReference) {
            incrementCount(response, "Audio References");
        } else if (reference instanceof VideoReference) {
            processVideoReference((VideoReference) reference, response);
        } else if (reference instanceof TextReference) {
            processTextReference((TextReference) reference, response);
        }
    }

    private void processVideoReference(VideoReference reference, Map<String, Integer> response) {
        if (reference.handleStreamAvailability()) {
            incrementCount(response, "Video References");
        }
    }

    private void processTextReference(TextReference reference, Map<String, Integer> response) {
        if (reference.handleTextAccess()) {
            incrementCount(response, "Text References");
        }
    }

    private Map<String, Integer> initializeReferenceCountMap() {
        Map<String, Integer> response = new HashMap<>();
        response.put("Audio References", 0);
        response.put("Video References", 0);
        response.put("Text References", 0);
        return response;
    }

    private void incrementCount(Map<String, Integer> map, String key) {
        map.put(key, map.get(key) + 1);
    }



}
