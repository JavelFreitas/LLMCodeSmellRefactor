package org.example.controllers;

import org.example.studymaterial.AudioReference;
import org.example.studymaterial.Reference;
import org.example.studymaterial.TextReference;
import org.example.studymaterial.VideoReference;
import org.example.studyregistry.*;

import java.time.LocalDateTime;
import java.util.*;

import static org.example.controllers.MainController.getInput;
import static org.example.controllers.MainController.validateInput;

public class StudyRegistryController {
    StudyTaskManager studyTaskManager = StudyTaskManager.getStudyTaskManager();
    StudyMaterial studyMaterial = StudyMaterial.getStudyMaterial();
    private Map<String, Runnable> actions = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

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

    private void handleAddStudyTask() {
        System.out.println("Adding Study Task...");
    }

    private void handleAddStudyGoal() {
        System.out.println("Adding Study Goal...");
    }

    private void handleAddStudyMaterial() {
        System.out.println("Adding Study Material...");
    }

    private void handleAddStudyObjective() {
        System.out.println("Adding Study Objective...");
    }

    private void handleAddStudyPlan() {
        System.out.println("Adding Study Plan...");
    }

    private void handleSetUpWeek() {
        System.out.println("Setting Up Study Week...");
    }

    private void handleGetWeekResponsibilities() {
        System.out.println("Getting Week Responsibilities...");
    }

    private void handleMethodHeader(String header){
        System.out.println("~~~~" + header + "~~~~\n");
    }

    private String getUserInput(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

    public static void controllerOptions(){
        System.out.println("""
                0 - return
                1 - add study task
                2 - add study goal
                3 - add study material
                4 - add study objective
                5 - add study plan
                6 - set up week
                7 - get week responsibilities
               """);
    }
}
