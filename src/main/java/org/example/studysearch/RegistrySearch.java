package org.example.studysearch;

import org.example.studyregistry.StudyTaskManager;
import java.util.List;

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
        return handleRegistrySearch(text);
    }

    public SearchLog getSearchLog() {
        return searchLog;
    }

    private List<String> handleRegistrySearch(String text) {
        List<String> results = searchService.searchAllRegistries(text);
        this.searchLog.addSearchHistory(text);
        results.add("\nLogged in: " + this.searchLog.getLogName());
        return results;
    }
}