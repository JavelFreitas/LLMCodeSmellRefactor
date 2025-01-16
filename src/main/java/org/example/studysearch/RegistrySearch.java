package org.example.studysearch;

import org.example.studycards.CardManager;
import org.example.studyplanner.HabitTracker;
import org.example.studyplanner.TodoTracker;
import org.example.studyregistry.StudyTaskManager;

import java.util.ArrayList;
import java.util.List;

public class RegistrySearch implements Search<String>{
    private SearchLog searchLog = new SearchLog("Registry Search");
    public RegistrySearch(){}

    @Override
    public List<String> search(String text) {
        return handleRegistrySearch(text);
    }

    public SearchLog getSearchLog() {
        return searchLog;
    }


    public List<String> handleRegistrySearch(String text) {
        List<String> results = new ArrayList<>();

        results.addAll(CardManager.getCardManager().handleSearch(text));
        results.addAll(HabitTracker.getHabitTracker().handleSearch(text));
        results.addAll(TodoTracker.getInstance().handleSearch(text));
        results.addAll(StudyTaskManager.getStudyTaskManager().handleSearch(text));

        results.add(searchLog.generateLogEntry(text));
        return results;
    }

}
