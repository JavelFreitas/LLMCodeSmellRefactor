package org.example.studysearch;

import org.example.studyregistry.StudyTaskManager;
import java.util.List;
import java.util.ArrayList;

public class RegistrySearch implements Search<String> {
    private final SearchLog searchLog;
    private final SearchService searchService;

    public RegistrySearch() {
        this(StudyTaskManager.getStudyTaskManager());
    }

    public RegistrySearch(SearchService searchService) {
        this.searchLog = new SearchLog("Registry Search");
        this.searchService = searchService;
    }

    @Override
    public List<String> search(String text) {
        List<String> results = new ArrayList<>();
        results.addAll(searchService.searchAllRegistries(text));
        results.addAll(searchLog.handleSearch(text));
        return results;
    }

    public SearchLog getSearchLog() {
        return searchLog;
    }
}