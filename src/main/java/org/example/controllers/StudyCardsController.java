package org.example.controllers;

import org.example.studycards.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.controllers.MainController.getInput;
import static org.example.controllers.MainController.validateInput;

public class StudyCardsController {
    private FlashCard flashCard;
    private LeitnerSystem leitnerSystem;
    private CardManager manager;
    private Map<String, Runnable> actions;

    public StudyCardsController() {
        this.flashCard = new FlashCard("FlashCard");
        this.leitnerSystem = new LeitnerSystem("LeitnerSystem");
        this.manager = CardManager.getCardManager();
        this.actions = new HashMap<>();
        assignActions();
    }

    public StudyCardsController(LeitnerSystem leitnerSystem) {
        this.flashCard = new FlashCard("FlashCard");
        this.leitnerSystem = leitnerSystem;
        this.manager = CardManager.getCardManager();
        this.actions = new HashMap<>();
        assignActions();
    }

    void assignActions() {
        actions.put("1", this::handleViewCards);
        actions.put("2", this::handleCreateCard);
        actions.put("3", this::handleRemoveCard);
        actions.put("4", this::handleRandomFlashCard);
        actions.put("5", this::handleInsertCardInBox);
        actions.put("6", this::handleRemoveCardFromBox);
        actions.put("7", this::handleUpgradeCardFromBox);
        actions.put("8", this::handleDowngradeCardFromBox);
        actions.put("9", this::handleViewBoxes);
        actions.put("10", this::handleGetRandomCardFromBox);
    }

    public void handleViewCards() {
        manager.handleViewCards();
    }

    public void handleViewBoxes() {
        leitnerSystem.handleViewBoxes();
    }

    public void handleRemoveCard() {
        System.out.println("Type card id:");
        manager.handleRemoveCard(getInput());
    }

    public void handleCreateCard() {
        System.out.println("Type the question: \n");
        String question = getInput();
        System.out.println("Type the answer: \n");
        String answer = getInput();
        manager.handleCreateCard(question, answer);
    }

    public void handleRandomFlashCard() {
        System.out.println("Random flash card:");
        Integer id = flashCard.randomFlashCard();
        System.out.println(manager.formatCard(id));
    }

    public void handleInsertCardInBox() {
        System.out.println("Type card id:");
        int id = Integer.parseInt(getInput());
        System.out.println("Type box(0-4):");
        int box = Integer.parseInt(getInput());
        leitnerSystem.handleInsertCard(id, box);
    }

    public void handleRemoveCardFromBox() {
        System.out.println("Type card id:");
        int id = Integer.parseInt(getInput());
        System.out.println("Type box(0-4):");
        int box = Integer.parseInt(getInput());
        leitnerSystem.handleRemoveCard(id, box);
    }

    public void handleUpgradeCardFromBox() {
        System.out.println("Type card id:");
        int id = Integer.parseInt(getInput());
        System.out.println("Type box(0-4):");
        int box = Integer.parseInt(getInput());
        leitnerSystem.handleUpgradeCard(id, box);
    }

    public void handleDowngradeCardFromBox() {
        System.out.println("Type card id:");
        int id = Integer.parseInt(getInput());
        System.out.println("Type box(0-4):");
        int box = Integer.parseInt(getInput());
        leitnerSystem.handleDowngradeCard(id, box);
    }

    // ...remaining code stays the same...

    public String getRandomCardFromBox() {
        return leitnerSystem.getRandomCardFromBoxFormatted();
    }

    public void handleGetRandomCardFromBox() {
        try {
            System.out.println(getRandomCardFromBox());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void handleCardsInput(){
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
                1 - view cards
                2 - create card
                3 - delete card
                4 - (FlashCard) Get random card
                5 - (Leitner) Insert card in box
                6 - (Leitner) Remove card from box
                7 - (Leitner) Upgrade card from box
                8 - (Leitner) Downgrade card from box
                9 - (Leitner) View boxes
                10- (Leitner) Get random card from box
               """);
    }
}
