package org.example.studysearch;

import java.util.*;

public class SearchLog {
    private final List<String> searchHistory;
    private final Map<String, Integer> searchCount;
    private boolean isLocked;
    private int numUsages;
    private String logName;

    public SearchLog(String logName) {
        if (logName == null || logName.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do log não pode ser vazio");
        }
        this.searchHistory = new ArrayList<>();
        this.searchCount = new HashMap<>();
        this.logName = logName;
        this.numUsages = 0;
        this.isLocked = false;
    }

    // API Compatibility Methods
    public Integer getNumUsages() {
        return getTotalSearches();
    }

    public List<String> getSearchHistory() {
        return Collections.unmodifiableList(searchHistory);
    }

    public void addSearchHistory(String searchTerm) {
        handleSearch(searchTerm);
    }

    // Core Methods
    public List<String> handleSearch(String text) {
        if (isLocked) {
            throw new IllegalStateException("Log está bloqueado para buscas");
        }
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Texto de busca não pode ser vazio");
        }

        searchHistory.add(text);
        incrementSearchCount(text);
        incrementUsages();

        List<String> results = new ArrayList<>();
        results.add("\nLogged in: " + this.logName);
        return results;
    }

    private void incrementSearchCount(String searchTerm) {
        searchCount.merge(searchTerm, 1, Integer::sum);
    }

    private void incrementUsages() {
        numUsages++;
    }

    public int getTotalSearches() {
        return numUsages;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String newLogName) {
        if (newLogName == null || newLogName.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do log não pode ser vazio");
        }
        this.logName = newLogName;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void lock() {
        this.isLocked = true;
    }

    public void unlock() {
        this.isLocked = false;
    }

    public Map<String, Integer> getSearchStatistics() {
        return Collections.unmodifiableMap(searchCount);
    }
}