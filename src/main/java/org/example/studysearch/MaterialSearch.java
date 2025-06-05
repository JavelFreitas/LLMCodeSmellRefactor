package org.example.studysearch;

import org.example.studyregistry.StudyMaterial;
import java.util.List;
import java.util.ArrayList;

public class MaterialSearch implements Search<String> {
    private final SearchLog searchLog;

    public MaterialSearch() {
        this.searchLog = new SearchLog("Material Search");
    }

    @Override
    public List<String> search(String text) {
        List<String> results = new ArrayList<>();
        results.addAll(StudyMaterial.getStudyMaterial().searchInMaterials(text));
        results.addAll(searchLog.handleSearch(text));
        return results;
    }

    public SearchLog getSearchLog() {
        return searchLog;
    }
}