package org.example.studysearch;

import java.util.List;

public class RegistrySearch implements Search<String> {
    private SearchLog searchLog = new SearchLog("Registry Search");

    public RegistrySearch() {}

    @Override
    public List<String> search(String text) {
        // Delegating the search and logging responsibility to SearchLog
        return searchLog.performSearchWithLogging(text);
    }

    public SearchLog getSearchLog() {
        return searchLog;
    }
}
