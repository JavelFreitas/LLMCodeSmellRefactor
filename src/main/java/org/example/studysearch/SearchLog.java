package org.example.studysearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchLog {
    private List<String> searchHistory;
    private Map<String, Integer> searchCount;
    private boolean isLocked;
    private Integer numUsages;
    private String logName;

    public SearchLog(String logName) {
        searchHistory = new ArrayList<>();
        searchCount = new HashMap<>();
        this.logName = logName;
        numUsages = 0;
        isLocked = false;
    }

    // Adds a search term to the history and increments its count
    public void addSearchHistory(String searchTerm) {
        if (isLocked) {
            throw new IllegalStateException("SearchLog is locked. Cannot modify it.");
        }

        searchHistory.add(searchTerm);
        incrementSearchCount(searchTerm);
        incrementUsage();
    }

    // Retrieves the most frequently searched term
    public String getMostFrequentSearch() {
        return searchCount.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    // Increments the count for a search term
    private void incrementSearchCount(String searchTerm) {
        searchCount.put(searchTerm, searchCount.getOrDefault(searchTerm, 0) + 1);
    }

    // Increments the number of usages
    private void incrementUsage() {
        numUsages++;
    }

    public List<String> getSearchHistory() {
        return new ArrayList<>(searchHistory); // Return a defensive copy
    }

    public Map<String, Integer> getSearchCount() {
        return new HashMap<>(searchCount); // Return a defensive copy
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void lock() {
        isLocked = true;
    }

    public void unlock() {
        isLocked = false;
    }

    public Integer getNumUsages() {
        return numUsages;
    }

    public String getLogName() {
        return logName;
    }

    public void resetLog() {
        if (isLocked) {
            throw new IllegalStateException("SearchLog is locked. Cannot reset.");
        }

        searchHistory.clear();
        searchCount.clear();
        numUsages = 0;
    }
}
