package org.example.studysearch;

import java.util.*;

public class SearchLog {
    private final List<String> searchHistory;
    private final Map<String, Integer> searchCount;
    private boolean isLocked;
    private Integer numUsages;
    private String logName;

    public SearchLog(String logName) {
        if (logName == null || logName.trim().isEmpty()) {
            throw new IllegalArgumentException("Log name cannot be empty");
        }
        this.searchHistory = new ArrayList<>();
        this.searchCount = new HashMap<>();
        this.logName = logName;
        this.numUsages = 0;
        this.isLocked = false;
    }

    public void addSearchHistory(String searchTerm) {
        if (isLocked) {
            throw new IllegalStateException("Cannot add search to locked log");
        }
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            throw new IllegalArgumentException("Search term cannot be empty");
        }
        searchHistory.add(searchTerm);
        searchCount.merge(searchTerm, 1, Integer::sum);
    }

    public List<String> getSearchHistory() {
        return Collections.unmodifiableList(searchHistory);
    }

    public void setSearchHistory(List<String> searchHistory) {
        if (isLocked) {
            throw new IllegalStateException("Cannot modify locked log");
        }
        this.searchHistory.clear();
        if (searchHistory != null) {
            this.searchHistory.addAll(searchHistory);
        }
    }

    public Map<String, Integer> getSearchCount() {
        return Collections.unmodifiableMap(searchCount);
    }

    public void setSearchCount(Map<String, Integer> searchCount) {
        if (isLocked) {
            throw new IllegalStateException("Cannot modify locked log");
        }
        this.searchCount.clear();
        if (searchCount != null) {
            this.searchCount.putAll(searchCount);
        }
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
        if (isLocked) {
            throw new IllegalStateException("Cannot modify locked log");
        }
        this.numUsages = numUsages;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        if (logName == null || logName.trim().isEmpty()) {
            throw new IllegalArgumentException("Log name cannot be empty");
        }
        this.logName = logName;
    }

    // Added business methods
    public int getSearchFrequency(String searchTerm) {
        return searchCount.getOrDefault(searchTerm, 0);
    }

    public void clearHistory() {
        if (isLocked) {
            throw new IllegalStateException("Cannot clear locked log");
        }
        searchHistory.clear();
        searchCount.clear();
        numUsages = 0;
    }


    public void logSearch(String searchTerm, List<String> results) {
        if (isLocked) {
            throw new IllegalStateException("Cannot add search to locked log");
        }
        addSearchHistory(searchTerm);
        setNumUsages(getNumUsages() + 1);
        results.add("\nLogged in: " + getLogName());
    }

}
