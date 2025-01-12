package org.example.studyregistry;

import org.example.studymaterial.Reference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudyTaskManager {
    private static StudyTaskManager instance;
    private StudyMaterial studyMaterial = StudyMaterial.getStudyMaterial();
    List<Registry> registryList;
    List<String> weekResponsibilities = List.of();

    private StudyTaskManager(){
        this.registryList = new ArrayList<Registry>();
    }

    public static StudyTaskManager getStudyTaskManager(){
        if (instance == null) {
            instance = new StudyTaskManager();
        }
        return instance;
    }

    public List<String> getWeekResponsibilities() {
        return weekResponsibilities;
    }

    public void setUpWeek(WeekPlan weekPlan){
        this.weekResponsibilities = new ArrayList<>();
        this.weekResponsibilities.addAll(Arrays.asList(weekPlan.getPlanName(), weekPlan.getObjectiveTitle(), weekPlan.getObjectiveDescription(), weekPlan.getMaterialTopic(), weekPlan.getMaterialFormat(), weekPlan.getGoal(), weekPlan.getReminderTitle(), weekPlan.getReminderDescription(), weekPlan.getMainTaskTitle(), weekPlan.getMainHabit(), weekPlan.getMainCardStudy()));
    }

    public void handleSetUpWeek(List<String> stringProperties){
        WeekPlan weekPlan = new WeekPlan(stringProperties.get(0), stringProperties.get(1), stringProperties.get(2), stringProperties.get(3),
                stringProperties.get(4), stringProperties.get(5), stringProperties.get(6), stringProperties.get(7),
                stringProperties.get(8), stringProperties.get(9), stringProperties.get(10));
        setUpWeek(weekPlan);
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

class WeekPlan {
    private String planName;
    private String objectiveTitle;
    private String objectiveDescription;
    private String materialTopic;
    private String materialFormat;
    private String goal;
    private String reminderTitle;
    private String reminderDescription;
    private String mainTaskTitle;
    private String mainHabit;
    private String mainCardStudy;

    public WeekPlan(String planName, String objectiveTitle, String objectiveDescription, String materialTopic,
                    String materialFormat, String goal, String reminderTitle, String reminderDescription,
                    String mainTaskTitle, String mainHabit, String mainCardStudy) {
        this.planName = planName;
        this.objectiveTitle = objectiveTitle;
        this.objectiveDescription = objectiveDescription;
        this.materialTopic = materialTopic;
        this.materialFormat = materialFormat;
        this.goal = goal;
        this.reminderTitle = reminderTitle;
        this.reminderDescription = reminderDescription;
        this.mainTaskTitle = mainTaskTitle;
        this.mainHabit = mainHabit;
        this.mainCardStudy = mainCardStudy;
    }

    // Getters for all fields
    public String getPlanName() { return planName; }
    public String getObjectiveTitle() { return objectiveTitle; }
    public String getObjectiveDescription() { return objectiveDescription; }
    public String getMaterialTopic() { return materialTopic; }
    public String getMaterialFormat() { return materialFormat; }
    public String getGoal() { return goal; }
    public String getReminderTitle() { return reminderTitle; }
    public String getReminderDescription() { return reminderDescription; }
    public String getMainTaskTitle() { return mainTaskTitle; }
    public String getMainHabit() { return mainHabit; }
    public String getMainCardStudy() { return mainCardStudy; }
}
