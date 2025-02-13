package org.example.studysearch;

import java.util.*;

public class SearchLog {
    private final List<String> searchHistory;
    private final Map<String, Integer> searchCount;
    private boolean isLocked;
    private int numUsages; // 🔹 Mantido int para consistência
    private final String logName; // 🔹 Imutável para garantir que o nome do log nunca mude

    public SearchLog(String logName) {
        this.logName = Objects.requireNonNull(logName, "Log name cannot be null"); // 🔹 Prevenção de null
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

    // 🔹 Método atualizado para incluir a funcionalidade combinada de registrar a busca e retornar o nome do log
    public String logAndGetName(String searchTerm) {
        addSearch(searchTerm);
        return logName;
    }

    // 🔹 Mantido para compatibilidade com testes antigos
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
