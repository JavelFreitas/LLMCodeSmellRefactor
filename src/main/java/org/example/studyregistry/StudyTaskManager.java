package org.example.studyregistry;

import org.example.studymaterial.Reference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class StudyTaskManager {
    private static StudyTaskManager instance;
    private StudyMaterial studyMaterial = StudyMaterial.getStudyMaterial();
    List<Registry> registryList;
    private WeekSetupData currentWeekSetup;

    private StudyTaskManager(){
        this.registryList = new ArrayList<>();
    }

    public static StudyTaskManager getStudyTaskManager(){
        if (instance == null) {
            instance = new StudyTaskManager();
        }
        return instance;
    }

    public WeekSetupData getCurrentWeekSetup() {
        return this.currentWeekSetup ;
    }

    public void setUpWeek(WeekSetupData weekSetupData){
        this.currentWeekSetup = weekSetupData;
    }

    public void handleSetUpWeek(List<String> stringProperties){
        if (stringProperties.size() != 11) {
            throw new IllegalArgumentException("Invalid number of properties for week setup.");
        }

        WeekSetupData weekSetupData = new WeekSetupData(
                stringProperties.get(0), stringProperties.get(1), stringProperties.get(2),
                stringProperties.get(3), stringProperties.get(4), stringProperties.get(5),
                stringProperties.get(6), stringProperties.get(7), stringProperties.get(8),
                stringProperties.get(9), stringProperties.get(10)
        );
        setUpWeek(weekSetupData);
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
