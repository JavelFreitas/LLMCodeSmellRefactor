package org.example.studyregistry;

import org.example.studycards.CardManager;
import org.example.studyplanner.HabitTracker;
import org.example.studyplanner.TodoTracker;
import org.example.studysearch.SearchService;
import java.util.ArrayList;
import java.util.List;

public class StudyTaskManager implements SearchService {
    private static StudyTaskManager instance;
    private List<String> weekResponsibilities;
    private List<Registry> registryList;

    private StudyTaskManager() {
        weekResponsibilities = new ArrayList<>();
        registryList = new ArrayList<>();
    }

    public static StudyTaskManager getStudyTaskManager() {
        if (instance == null) {
            instance = new StudyTaskManager();
        }
        return instance;
    }

    @Override
    public List<String> searchAllRegistries(String text) {
        List<String> results = new ArrayList<>();
        results.addAll(CardManager.getCardManager().searchInCards(text));
        results.addAll(HabitTracker.getHabitTracker().searchInHabits(text));
        results.addAll(TodoTracker.getInstance().searchInTodos(text));
        results.addAll(searchInRegistries(text));
        return results;
    }

    public List<String> getWeekResponsibilities() {
        return weekResponsibilities;
    }

    public void setUpWeek(WeekSetup setup) {
        this.weekResponsibilities = new ArrayList<>(setup.toList());
    }

    public void handleSetUpWeek(List<String> stringProperties) {
        setUpWeek(WeekSetup.fromList(stringProperties));
    }


    public void addRegistry(Registry registry){
        registryList.add(registry);
    }
    public void removeRegistry(Registry registry){
        registryList.remove(registry);
    }
    public List<Registry> getRegistryList(){
        return registryList;
    }

    public List<String> searchInRegistries(String text){
        List<String> response = new ArrayList<>();
        for(Registry registry : registryList){
            String mix = (registry.getName() != null ? registry.getName() : "");
            if (mix.toLowerCase().contains(text.toLowerCase())){
                response.add(registry.getName());
            }
        }
        return response;
    }

}
