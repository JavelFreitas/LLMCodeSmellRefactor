package org.example.studysearch;

import org.example.studyregistry.StudyMaterial;
import java.util.List;

public class MaterialSearch implements Search<String> {

    @Override
    public List<String> search(String text) {
        return StudyMaterial.getStudyMaterial().search(text);
    }

    public SearchLog getSearchLog() {
        return StudyMaterial.getStudyMaterial().getSearchLog();
    }
}