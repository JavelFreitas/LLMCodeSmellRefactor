package org.example.studysearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SearchLog {
    private List<String> searchHistory;
    private Map<String, Integer> searchCount;
    private boolean isLocked;
    private int numUsages; // Use int para consistência
    private final String logName; // 🔹 Tornado final para imutabilidade

    public SearchLog(String logName) {
        this.logName = Objects.requireNonNull(logName, "Log name cannot be null"); // 🔹 Impedindo logName nulo
        searchHistory = new ArrayList<>();
        searchCount = new HashMap<>();
        numUsages = 0;
        isLocked = false;
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

    // 🔹 Método restaurado para compatibilidade com os testes
    public void addSearchHistory(String searchTerm) {
        addSearch(searchTerm);
    }

    public List<String> getSearchHistory() {
        return Collections.unmodifiableList(searchHistory); // 🔹 Retorna uma cópia imutável
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
