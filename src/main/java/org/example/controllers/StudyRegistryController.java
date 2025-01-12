package org.example.controllers;

import org.example.studymaterial.AudioReference;
import org.example.studymaterial.Reference;
import org.example.studymaterial.TextReference;
import org.example.studymaterial.VideoReference;
import org.example.studyregistry.*;
import org.example.studyregistry.StudyObjective.RegistryDetails;
import org.example.studyregistry.StudyObjective.TextualInfo;
import org.example.studyregistry.StudyObjective.TimeDetails;
import org.example.studyregistry.StudyPlan.StepConfiguration;
import org.example.studyregistry.StudyPlan.StepDetails;
import org.example.studyregistry.StudyPlan.StepMetadata;
import org.example.studyregistry.StudyTaskManager.ObjectiveDetails;
import org.example.studyregistry.StudyTaskManager.MaterialDetails;
import org.example.studyregistry.StudyTaskManager.WeekPlan;
import org.example.studyregistry.StudyTaskManager.ReminderDetails;;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.controllers.MainController.getInput;
import static org.example.controllers.MainController.validateInput;

public class StudyRegistryController {
    StudyTaskManager studyTaskManager = StudyTaskManager.getStudyTaskManager();
    StudyMaterial studyMaterial = StudyMaterial.getStudyMaterial();
    private Map<String, Runnable> actions = new HashMap<>();

    public StudyRegistryController() {
        assignActions();
    }

    void assignActions(){
        actions.put("1", this::handleAddStudyTask);
        actions.put("2", this::handleAddStudyGoal);
        actions.put("3", this::handleAddStudyMaterial);
        actions.put("4", this::handleAddStudyObjective);
        actions.put("5", this::handleAddStudyPlan);
        actions.put("6", this::handleSetUpWeek);
        actions.put("7", this::handleGetWeekResponsibilities);
    }

    private void handleMethodHeader(String header){
        System.out.println("~~~~" + header + "~~~~\n");
    }

    private Task getStudyTaskInfo(){
        System.out.println("Type the following info: title, description, author \n");
        String title = getInput();
        String description = getInput();
        String author = getInput();
        return new Task(title, description, author, LocalDateTime.now());
    }

    private void handleAddStudyTask(){
        Task task = getStudyTaskInfo();
        studyTaskManager.addRegistry(task);
    }

    private void handleSetObjective(StudyObjective objective) {
    handleMethodHeader("(Study Objective Edit)");
    RegistryDetails registryDetails = getRegistryDetailsFromInput();
    TextualInfo textualInfo = getTextualInfoFromInput();
    TimeDetails timeDetails = getTimeDetailsFromInput();

    objective.handleSetObjective(registryDetails, textualInfo, timeDetails);
}
private TextualInfo getTextualInfoFromInput() {
    System.out.println("Type the following info for Textual Info: title, description, topic, objectiveInOneLine, objectiveFullDescription, motivation\n");
    String title = getInput();
    String description = getInput();
    String topic = getInput();
    String objectiveInOneLine = getInput();
    String objectiveFullDescription = getInput();
    String motivation = getInput();
    return new StudyObjective.TextualInfo(title, description, topic, objectiveInOneLine, objectiveFullDescription, motivation);
}
private TimeDetails getTimeDetailsFromInput() {
    System.out.println("Type the following info for Time Details: practicedDays, day, month, year, duration\n");
    int practicedDays = Integer.parseInt(getInput());
    int day = Integer.parseInt(getInput());
    int month = Integer.parseInt(getInput());
    int year = Integer.parseInt(getInput());
    double duration = Double.parseDouble(getInput());
    return new TimeDetails(practicedDays, day, month, year, duration);
}


private RegistryDetails getRegistryDetailsFromInput() {
    System.out.println("Type the following info for Registry Details: id, name, priority, isActive\n");
    int id = Integer.parseInt(getInput());
    String name = getInput();
    int priority = Integer.parseInt(getInput());
    boolean isActive = Boolean.parseBoolean(getInput());
    return new StudyObjective.RegistryDetails(id, name, priority, isActive);
}


    private StudyObjective getStudyObjectiveInfo(){
        handleMethodHeader("(Study Objective Creation)");
        System.out.println("Type the following info: title, description \n");
        String title = getInput();
        String description = getInput();
        StudyObjective studyObjective = new StudyObjective(title, description);
        handleSetObjective(studyObjective);
        studyTaskManager.addRegistry(studyObjective);
        return studyObjective;
    }

    private StudyPlan getStudyPlanInfo(){
        handleMethodHeader("(Study Plan Creation)");
        System.out.println("Type the following info: name \n");
        String name = getInput();
        StudyObjective studyObjective = getStudyObjectiveInfo();
        StudyPlan plan = new StudyPlan(name, studyObjective,  new ArrayList<>());
        studyTaskManager.addRegistry(plan);
        return plan;
    }
    private void handleSetSteps(StudyPlan studyPlan) {
        handleMethodHeader("(Study Plan Edit)");
    
        // Coletar informações para configuração e metadados
        List<String> stringProperties = collectStringProperties();
        StepMetadata metadata = collectStepMetadata();
    
        // Construir StepDetails e atribuir os passos
        StepDetails details = buildStepDetails(stringProperties, metadata);
        studyPlan.assignSteps(details);
    }
    
    private List<String> collectStringProperties() {
        System.out.println("Type the following info for Step Configuration: firstStep, resetStudyMechanism, consistentStep, seasonalSteps, basicSteps, mainObjectiveTitle, mainGoalTitle, mainMaterialTopic, mainTask");
        List<String> stringProperties = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            stringProperties.add(getInput());
        }
        return stringProperties;
    }
    
    private StepMetadata collectStepMetadata() {
        System.out.println("Type the following info for Step Metadata: numberOfSteps, isImportant, days to complete");
        int numberOfSteps = Integer.parseInt(getInput());
        boolean isImportant = Boolean.parseBoolean(getInput());
        int daysToComplete = Integer.parseInt(getInput());
    
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusDays(daysToComplete);
    
        return new StepMetadata(numberOfSteps, isImportant, startDate, endDate);
    }
    
    private StepDetails buildStepDetails(List<String> stringProperties, StepMetadata metadata) {
        StepConfiguration configuration = StepConfiguration.buildFromProperties(stringProperties);
        return new StepDetails(configuration, metadata);
    }
    


    private StudyGoal getStudyGoalInfo(){
        handleMethodHeader("(Study Goal Creation)");
        System.out.println("Type the following info: name \n");
        String name = getInput();
        StudyPlan studyPlan = getStudyPlanInfo();
        handleSetSteps(studyPlan);
        StudyObjective studyObjective = studyPlan.getObjective();
        return new StudyGoal(name, studyObjective, studyPlan);
    }

    private void handleAddStudyGoal(){
        StudyGoal goal = getStudyGoalInfo();
        studyTaskManager.addRegistry(goal);
    }

    private void editAudio(AudioReference audioReference) {
        handleMethodHeader("(Audio Edit)");
        System.out.println("Type the following info: AudioReference.AudioQuality audioQuality, boolean isDownloadable, " +
                "String title, String description, String link, String accessRights, String license, String language, int rating, " +
                "int viewCount, int shareCount \n");
    
        AudioReference.AudioQuality quality = AudioReference.audioQualityAdapter(getInput());
    
        AudioReference.BasicDetails basicDetails = new AudioReference.BasicDetails(
                getInput(), // Title
                getInput(), // Description
                getInput()  // Link
        );
    
        AudioReference.AudioAttributesConfig config = new AudioReference.AudioAttributesConfig.Builder()
                .setAudioQuality(quality)
                .setDownloadable(Boolean.parseBoolean(getInput())) // isDownloadable
                .setBasicDetails(basicDetails)
                .setAccessRights(getInput())  // Access Rights
                .setLicense(getInput())       // License
                .setLanguage(getInput())      // Language
                .setRating(Integer.parseInt(getInput()))          // Rating
                .setViewCount(Integer.parseInt(getInput()))       // View Count
                .setShareCount(Integer.parseInt(getInput()))      // Share Count
                .build();
    
        AudioReference.AudioAttributes attributes = new AudioReference.AudioAttributes(config);
        audioReference.editAudio(attributes);
    }
    

    private AudioReference addAudioReference(){
        handleMethodHeader("(Audio Reference Creation)");
        System.out.println("Type the following info: Audio Quality ( LOW | MEDIUM | HIGH | VERY_HIGH) \n");
        AudioReference audioReference = new AudioReference(AudioReference.audioQualityAdapter(getInput()));
        editAudio(audioReference);
        return audioReference;
    }

    private VideoReference addVideoReference(){
        handleMethodHeader("(Video Reference Creation)");
        System.out.println("Type the following info: boolean isAvailable, String title, " +
                "String description, String resolution, String frameRate, String videoFormat, String accessRights \n");
        return new VideoReference(Boolean.parseBoolean(getInput()), getInput(), getInput(), getInput(), getInput(),
                getInput(), getInput());
    }

    private TextReference addTextReference(){
        handleMethodHeader("(Text Reference Creation)");
        System.out.println("Type the following info:  String title, String language, int wordCount, String format, String accessRights \n");
        return new TextReference(getInput(), getInput(), Integer.parseInt(getInput()), getInput(),
                getInput());
    }

    private Reference addStudyMaterial(){
        handleMethodHeader("(Study Material Creation)");
        System.out.println("Type the following info: ( AUDIO | VIDEO | TEXT ) \n");
        String type = getInput();
        return switch (type.toLowerCase()) {
            case "audio" -> addAudioReference();
            case "video" -> addVideoReference();
            case "text" -> addTextReference();
            default -> null;
        };
    }

    private void handleAddStudyMaterial(){
        Reference reference = addStudyMaterial();
        if(reference != null){
            studyMaterial.addReference(reference);
        }
    }

    private void handleAddStudyObjective(){
        getStudyObjectiveInfo();
    }

    private void handleAddStudyPlan(){
        getStudyPlanInfo();
        System.out.println("Study Plan Added");
    }

    private void getWeekInfo() {
        System.out.println("(Study Task Manager Week Set Up)");
    
        // Coleta informações para criar o WeekPlan
        String planName = getPlanNameFromInput();
        ObjectiveDetails objectiveDetails = getObjectiveDetailsFromInput();
        MaterialDetails materialDetails = getMaterialDetailsFromInput();
        ReminderDetails reminderDetails = getReminderDetailsFromInput();
        String mainTaskTitle = getMainTaskTitleFromInput();
        String mainHabit = getMainHabitFromInput();
        String mainCardStudy = getMainCardStudyFromInput();
    
        // Cria o WeekPlan
        WeekPlan weekPlan = new WeekPlan(planName, objectiveDetails, materialDetails, reminderDetails, mainTaskTitle, mainHabit, mainCardStudy);
    
        // Configura a semana no StudyTaskManager
        studyTaskManager.setUpWeek(weekPlan);
    }
    
    private String getPlanNameFromInput() {
        System.out.println("Enter the Plan Name:");
        return getInput();
    }
    
    private ObjectiveDetails getObjectiveDetailsFromInput() {
        System.out.println("Enter the Objective Details: title and description");
        String objectiveTitle = getInput();
        String objectiveDescription = getInput();
        return new ObjectiveDetails(objectiveTitle, objectiveDescription);
    }
    
    private MaterialDetails getMaterialDetailsFromInput() {
        System.out.println("Enter the Material Details: topic and format");
        String materialTopic = getInput();
        String materialFormat = getInput();
        return new MaterialDetails(materialTopic, materialFormat);
    }
    
    private ReminderDetails getReminderDetailsFromInput() {
        System.out.println("Enter the Reminder Details: title and description");
        String reminderTitle = getInput();
        String reminderDescription = getInput();
        return new ReminderDetails(reminderTitle, reminderDescription);
    }
    
    private String getMainTaskTitleFromInput() {
        System.out.println("Enter the Main Task Title:");
        return getInput();
    }
    
    private String getMainHabitFromInput() {
        System.out.println("Enter the Main Habit:");
        return getInput();
    }
    
    private String getMainCardStudyFromInput() {
        System.out.println("Enter the Main Card Study:");
        return getInput();
    }
    

    private void handleSetUpWeek(){
        getWeekInfo();
    }

    private void handleGetWeekResponsibilities(){
        List<String> responsibilities = studyTaskManager.getWeekResponsibilities();
        System.out.println(String.join(", ", responsibilities));
    }

    public void handleRegistryInput(){
        try{
            while(true){
                controllerOptions();
                String response = validateInput(actions);
                if(response == null) {return;}
                actions.get(response).run();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void controllerOptions(){
        System.out.println("""
                0 - return
                1 - add study task
                2 - add study goal
                3 - add study material (audio, video, text)
                4 - add study objective
                5 - add study plan
                6 - set up week
                7 - get week responsibilities
               """);
    }
}
