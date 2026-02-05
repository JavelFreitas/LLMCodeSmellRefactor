package org.example.studysearch;

class SearchLogger {
    private SearchLog searchLog = new SearchLog("Material Search");

    public void logSearch(String text) {
        searchLog.logSearch(text);
    }

    private void logSearchHistory(String text) {
        searchLog.addSearchHistory(text);
    }

    private void logUsageCount() {
        searchLog.setNumUsages(searchLog.getNumUsages() + 1);
    }

    public String getLogName() {
        return searchLog.getLogName();
    }
}
