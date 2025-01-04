package org.example.studysearch;

import org.example.studycards.CardManager;
import org.example.studyplanner.HabitTracker;
import org.example.studyplanner.TodoTracker;
import org.example.studyregistry.StudyMaterial;
import org.example.studyregistry.StudyTaskManager;

import java.util.ArrayList;
import java.util.List;

public class GeneralSearch implements Search<String> {
    private SearchLog searchLog = new SearchLog("General Search");

    public GeneralSearch() {}

    @Override
    public List<String> search(String text) {
        List<String> results = new ArrayList<>();
        results.addAll(searchInCards(text));
        results.addAll(searchInHabits(text));
        results.addAll(searchInTodos(text));
        results.addAll(searchInMaterials(text));
        results.addAll(searchInRegistries(text));
        this.searchLog.addSearchHistory(text);
        this.searchLog.setNumUsages(this.searchLog.getNumUsages() + 1);
        results.add("\nLogged in: " + this.searchLog.getLogName());
        return results;
    }

    public SearchLog getSearchLog(){
        return searchLog;
    }

    private List<String> searchInCards(String text) {
        return CardManager.getCardManager().searchInCards(text);
    }

    private List<String> searchInHabits(String text) {
        return HabitTracker.getHabitTracker().searchInHabits(text);
    }

    private List<String> searchInTodos(String text) {
        return TodoTracker.getInstance().searchInTodos(text);
    }

    private List<String> searchInMaterials(String text) {
        return StudyMaterial.getStudyMaterial().searchInMaterials(text);
    }

    private List<String> searchInRegistries(String text) {
        return StudyTaskManager.getStudyTaskManager().searchInRegistries(text);
    }
}
