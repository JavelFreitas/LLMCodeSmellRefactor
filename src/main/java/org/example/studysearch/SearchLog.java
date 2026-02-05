package org.example.studysearch;

import java.util.*;

public class SearchLog {
    private final List<String> searchHistory;
    private final Map<String, Integer> searchCount;
    private boolean isLocked;
    private int numUsages;
    private final String logName;

    public SearchLog(String logName) {
        this.logName = Objects.requireNonNull(logName, "Log name cannot be null");
        this.searchHistory = new ArrayList<>();
        this.searchCount = new HashMap<>();
        this.numUsages = 0;
        this.isLocked = false;
    }

    public void addSearch(String searchTerm) {
        if (isLocked) {
            throw new IllegalStateException("Search log is locked.");
        }
        Objects.requireNonNull(searchTerm, "Search term cannot be null");

        searchHistory.add(searchTerm);
        searchCount.put(searchTerm, searchCount.getOrDefault(searchTerm, 0) + 1);
        numUsages++;
    }

    public String logAndGetName(String searchTerm) {
        addSearch(searchTerm);
        return logName;
    }

    public void addSearchHistory(String searchTerm) {
        addSearch(searchTerm);
    }

    public List<String> getSearchHistory() {
        return Collections.unmodifiableList(searchHistory);
    }

    public Map<String, Integer> getSearchCount() {
        return Collections.unmodifiableMap(searchCount);
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void lockLog() {
        isLocked = true;
    }

    public void unlockLog() {
        isLocked = false;
    }

    public int getNumUsages() {
        return numUsages;
    }

    public String getLogName() {
        return logName;
    }

    public String logSearch(String text) {
        addSearch(text);
        return "\nLogged in: " + getLogName();
    }

    public boolean hasSearched(String searchTerm) {
        return searchCount.containsKey(searchTerm);
    }

    public void resetLog() {
        if (isLocked) {
            throw new IllegalStateException("Search log is locked. Cannot reset.");
        }
        searchHistory.clear();
        searchCount.clear();
        numUsages = 0;
    }
}
