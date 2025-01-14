package org.example.studysearch;

import java.util.List;

public class GeneralSearch implements Search<String> {
    private SearchLog searchLog;

    public GeneralSearch() {
        this.searchLog = new SearchLog("General Search");
    }

    @Override
    public List<String> search(String text) {
        return searchLog.handleSearch(text);
    }

    public SearchLog getSearchLog() {
        return searchLog;
    }
}