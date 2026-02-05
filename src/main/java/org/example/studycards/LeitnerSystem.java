package org.example.studycards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeitnerSystem {
    private String methodName;
    private List<Box> boxes;
    private CardManager cardManager;

    public LeitnerSystem(String methodName) {
        this.methodName = methodName;
        this.cardManager = CardManager.getCardManager();
        this.boxes = new ArrayList<>(Arrays.asList(new Box(), new Box(), new Box(), new Box(), new Box()));
    }

    public String getMethodName() {
        return this.methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getRandomCardFromBoxFormatted() {
        String response = "";
        response += getMethodName();
        List<Box> boxes = getBoxes();
        response += getRandomCard(boxes);
        return response;
    }

    public String toString(){
        StringBuilder response = new StringBuilder();
        int index = 0;
        for(Box box : boxes){
            response.append("Box ").append(index).append(": ").append(box.toString()).append("\n");
            index++;
        }
        return response.toString();
    }

    public void clearBoxes(){
        boxes.clear();
        boxes = new ArrayList<>(Arrays.asList(new Box(), new Box(), new Box(), new Box(), new Box()));
    }

    public List<Box> getBoxes() {
        return boxes;
    }

    public String getRandomCard(List<Box> otherBoxes) {
        if (isInvalidBoxes(otherBoxes)) {
            return null;
        }

        Box allBoxes = combineBoxes(otherBoxes);
        Integer randomCardId = allBoxes.getRandomCard();

        if (randomCardId == null) {
            return "No card found";
        }

        return formatCardResponse(randomCardId);
    }

    private boolean isInvalidBoxes(List<Box> boxes) {
        return boxes == null || boxes.isEmpty();
    }

    private Box combineBoxes(List<Box> otherBoxes) {
        Box allBoxes = new Box();
        for (Box box : otherBoxes) {
            allBoxes.addCards(box.getCards());
        }
        return allBoxes;
    }

    private String formatCardResponse(Integer cardId) {
        Card card = cardManager.getCard(cardId);
        return String.format("[%d] The random question was: %s | The answer is: %s",
                cardId, card.getQuestion(), card.getAnswer());
    }
    public void addCardToBox(Integer id, Integer boxId) {
        this.boxes.get(boxId).addCard(id);
    }

    public void removeCardFromBox(Integer id, Integer boxId) {
        this.boxes.get(boxId).removeCard(id);
    }

    public Card takeCardFromBox(Integer boxId){
        Integer cardId = boxes.get(boxId).getRandomCard();
        return this.cardManager.getCard(cardId);
    }

    public void boxIdValidation(Integer boxId) throws Exception {
        if(boxId == null || boxId > (boxes.size()-1) || boxId <= 0){
            throw new Exception("Invalid box ID");
        }
    }

    public void upgradeCard(Integer cardId, Integer boxId) throws Exception {
        boxIdValidation(boxId);

        Box refBox = boxes.get(boxId);
        if(refBox.hasCard(cardId)){
            throw new Exception("No card Found");
        }
        refBox.removeCard(cardId);
        boxes.get(Math.min(boxId + 1, 4)).addCard(cardId);
    }

    public void downgradeCard(Integer cardId, Integer boxId) throws Exception {
        boxIdValidation(boxId);

        Box refBox = boxes.get(boxId);
        if(refBox.hasCard(cardId)){
            throw new Exception("No card Found");
        }
        refBox.removeCard(cardId);
        boxes.get(Math.max(boxId - 1, 0)).addCard(cardId);
    }

    public void handleInsertCard(int id, int box) {
        addCardToBox(id, box);
    }

    public void handleRemoveCard(int id, int box) {
        removeCardFromBox(id, box);
    }

    public void handleViewBoxes() {
        System.out.println(this.toString());
    }

    public void handleUpgradeCard(int id, int box) {
        try {
            upgradeCard(id, box);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleDowngradeCard(int id, int box) {
        try {
            downgradeCard(id, box);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleGetRandomCard() {
        try {
            String response = getMethodName();
            response += getRandomCard(getBoxes());
            System.out.println(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
