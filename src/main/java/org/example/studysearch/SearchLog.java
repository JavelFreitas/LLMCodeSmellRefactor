package org.example.studysearch;

import java.util.ArrayList;
import java.util.List;

public class SearchLog {
    private final List<String> searchHistory;
    private Integer numUsages;
    private final String logName;

    public SearchLog(String logName) {
        searchHistory = new ArrayList<>();
        this.logName = logName;
        numUsages = 0;
    }
    public void addSearchHistory(String searchHistory) {
        this.searchHistory.add(searchHistory);
    }
    public List<String> getSearchHistory() {
        return searchHistory;
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

    public void incrementUsage() {
            this.numUsages++;
        }

    public String logSearchAndIncrement(String text) {
            addSearchHistory(text);
            incrementUsage();
            return "\nLogged in: " + this.logName;
        }
    }