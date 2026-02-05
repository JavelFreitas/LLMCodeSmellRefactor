package org.example.studysearch;

import org.example.studyregistry.StudyMaterial;

import java.util.ArrayList;
import java.util.List;

public class MaterialSearch implements Search<String> {

    private final SearchLogger searchLogger = new SearchLogger();

    private SearchLog searchLog;

    // ... outros métodos ...

    @Override
    public List<String> search(String text) {
        List<String> results = searchInMaterials(text);
        searchLogger.logSearch(text);
        results.add("\nLogged in: " + searchLogger.getLogName());
        return results;
    }

    public SearchLog getSearchLog() {
        return searchLog;
    }

    private List<String> searchInMaterials(String text) {
        List<String> results = new ArrayList<>();
        results.addAll(StudyMaterial.getStudyMaterial().searchInMaterials(text));
        return results;
    }

}
