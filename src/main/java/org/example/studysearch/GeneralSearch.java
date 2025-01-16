package org.example.studysearch;

import org.example.studycards.CardManager;
import org.example.studyplanner.HabitTracker;
import org.example.studyplanner.TodoTracker;
import org.example.studyregistry.StudyMaterial;
import org.example.studyregistry.StudyTaskManager;
import java.util.ArrayList;
import java.util.List;

public class GeneralSearch implements Search<String> {
    private final SearchLog searchLog;

    public GeneralSearch() {
        this.searchLog = new SearchLog("General Search");
    }

    @Override
    public List<String> search(String text) {
        List<String> results = new ArrayList<>();
        results.addAll(CardManager.getCardManager().searchInCards(text));
        results.addAll(HabitTracker.getHabitTracker().searchInHabits(text));
        results.addAll(TodoTracker.getInstance().searchInTodos(text));
        results.addAll(StudyMaterial.getStudyMaterial().searchInMaterials(text));
        results.addAll(StudyTaskManager.getStudyTaskManager().searchInRegistries(text));
        results.addAll(searchLog.handleSearch(text));
        return results;
    }

    public SearchLog getSearchLog() {
        return searchLog;
    }
}