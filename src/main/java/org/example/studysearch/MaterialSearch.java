package org.example.studysearch;

import org.example.studyregistry.StudyMaterial;

import java.util.ArrayList;
import java.util.List;

public class MaterialSearch implements Search<String>{


    private SearchLog searchLog = new SearchLog("Material Search");

    public MaterialSearch() {}

    @Override
    public List<String> search(String text) {
        List<String> results = StudyMaterial.getStudyMaterial().searchInMaterials(text);
        searchLog.addSearchHistory(text);
        searchLog.setNumUsages(searchLog.getNumUsages() + 1);
        results.add("\nLogged in: " + searchLog.getLogName());
        return results;
    }

    public SearchLog getSearchLog() {
        return searchLog;
    }

}
