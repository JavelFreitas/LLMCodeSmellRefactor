package org.example.studysearch;

import org.example.studycards.CardManager;
import org.example.studyplanner.HabitTracker;
import org.example.studyplanner.TodoTracker;
import org.example.studyregistry.StudyTaskManager;

import java.util.ArrayList;
import java.util.List;

public class RegistrySearch implements Search<String> {
    private SearchLog searchLog = new SearchLog("Registry Search");

    public RegistrySearch() {}

    @Override
    public List<String> search(String text) {
        List<String> results = new ArrayList<>();
        results.addAll(CardManager.getCardManager().search(text));
        results.addAll(HabitTracker.getHabitTracker().search(text));
        results.addAll(TodoTracker.getInstance().search(text));
        results.addAll(StudyTaskManager.getStudyTaskManager().searchInRegistries(text));
        this.searchLog.addSearch(text);
        results.add("\nLogged in: " + this.searchLog.getLogName());
        return results;
    }

    public SearchLog getSearchLog() {
        return searchLog;
    }
}