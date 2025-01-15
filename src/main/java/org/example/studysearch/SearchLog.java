package org.example.studysearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchLog {
    private final SearchHistoryManager searchHistoryManager;
    private Map<String, Integer> searchCount;
    private boolean isLocked;
    private Integer numUsages;
    private String logName;

    public SearchLog(String logName) {
        searchHistoryManager = new SearchHistoryManager();
        searchCount = new HashMap<>();
        this.logName = logName;
        numUsages = 0;
        isLocked = false;
    }

    public void addSearchHistory(String searchTerm) {
        searchHistoryManager.addSearchHistory(searchTerm);
    }

    public List<String> getSearchHistory() {
        return searchHistoryManager.getSearchHistory();
    }

    public Map<String, Integer> getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(Map<String, Integer> searchCount) {
        this.searchCount = searchCount;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public Integer getNumUsages() {
        return numUsages;
    }

    public void setNumUsages(Integer numUsages) {
        this.numUsages = numUsages;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public void logSearch(String searchTerm) {
        addSearchHistory(searchTerm);
        setNumUsages(getNumUsages() + 1);
    }

    public String logSearchAndGetMessage(String searchTerm) {
        if (isLocked) {
            throw new IllegalStateException("Cannot add search when log is locked");
        }
        addSearchHistory(searchTerm);
        return "\nLogged in: " + this.logName;
    }
}

class SearchHistoryManager {
    private final List<String> searchHistory;

    public SearchHistoryManager() {
        searchHistory = new ArrayList<>();
    }

    public void addSearchHistory(String searchTerm) {
        this.searchHistory.add(searchTerm);
    }

    public List<String> getSearchHistory() {
        return searchHistory;
    }
}