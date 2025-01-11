package org.example.studysearch;

import org.example.studycards.CardManager;
import org.example.studyplanner.HabitTracker;
import org.example.studyplanner.TodoTracker;
import org.example.studyregistry.StudyMaterial;
import org.example.studyregistry.StudyTaskManager;

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

    // Move the logic from the 'GeneralSearch' class here
    public List<String> handleSearch(String text,
                                     CardManager cardManager,
                                     HabitTracker habitTracker,
                                     TodoTracker todoTracker,
                                     StudyMaterial studyMaterial,
                                     StudyTaskManager studyTaskManager) {

        List<String> results = new ArrayList<>();
        results.addAll(cardManager.searchInCards(text));
        results.addAll(habitTracker.searchInHabits(text));
        results.addAll(todoTracker.searchInTodos(text));
        results.addAll(studyMaterial.searchInMaterials(text));
        results.addAll(studyTaskManager.searchInRegistries(text));

        // Log the search query and update the history
        this.addSearchHistory(text);

        // Add additional result to indicate the log name
        results.add("\nLogged in: " + this.logName);

        return results;
    }

    // This method is responsible for adding a search query to history and updating usage count
    public void addSearchHistory(String searchQuery) {
        this.searchHistory.add(searchQuery);
        this.searchCount.put(searchQuery, this.searchCount.getOrDefault(searchQuery, 0) + 1);
        this.numUsages++;
    }

    // Getter and setter methods for search history and other fields
    public List<String> getSearchHistory() {
        return searchHistory;
    }

    public void setSearchHistory(List<String> searchHistory) {
        this.searchHistory = searchHistory;
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
}
