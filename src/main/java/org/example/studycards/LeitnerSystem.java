package org.example.studycards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeitnerSystem extends StudyMethod {
    List<Box> boxes = null;
    private CardManager cardManager; // Add CardManager instance

    public LeitnerSystem(String methodName) {
        super(methodName);
        boxes = new ArrayList<>(Arrays.asList(new Box(), new Box(), new Box(), new Box(), new Box()));
        cardManager = CardManager.getCardManager(); // Initialize CardManager
    }

    @Override
    public String getMethodName() {
        return this.methodName;
    }

    @Override
    void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String toString() {
        StringBuilder response = new StringBuilder();
        int index = 0;
        for (Box box : boxes) {
            response.append("Box ").append(index).append(": ").append(box.toString()).append("\n");
            index++;
        }
        return response.toString();
    }

    public void clearBoxes() {
        boxes.clear();
        boxes = new ArrayList<>(Arrays.asList(new Box(), new Box(), new Box(), new Box(), new Box()));
    }

    public List<Box> getBoxes() {
        return boxes;
    }

    public String getRandomCard(List<Box> otherBoxes) {
        if (otherBoxes == null || otherBoxes.isEmpty()) {
            return null;
        }

        Box allBoxes = collectCardsFromBoxes(otherBoxes);
        return retrieveAndFormatCard(allBoxes);
    }

    private Box collectCardsFromBoxes(List<Box> otherBoxes) {
        Box allBoxes = new Box();
        for (Box box : otherBoxes) {
            allBoxes.addCards(box.getCards());
        }
        return allBoxes;
    }

    private String retrieveAndFormatCard(Box allBoxes) {
        Integer randomCardId = allBoxes.getRandomCard();
        if (randomCardId == null) {
            return "No card found";
        }

        Card card = cardManager.getCard(randomCardId);
        return formatCardResponse(randomCardId, card);
    }

    private String formatCardResponse(Integer cardId, Card card) {
        return String.format("[%d] The random question was: %s | The answer is: %s",
                cardId, card.getQuestion(), card.getAnswer());
    }


    public void addCardToBox(Integer id, Integer boxId) {
        this.boxes.get(boxId).addCard(id);
    }

    public void removeCardFromBox(Integer id, Integer boxId) {
        this.boxes.get(boxId).removeCard(id);
    }

    public Card takeCardFromBox(Integer boxId) {
        Integer cardId = boxes.get(boxId).getRandomCard();
        return this.cardManager.getCard(cardId);
    }

    public void boxIdValidation(Integer boxId) throws Exception {
        if (boxId == null || boxId > (boxes.size() - 1) || boxId < 0) { // Corrected condition
            throw new Exception("Invalid box ID");
        }
    }

    public void upgradeCard(Integer cardId, Integer boxId) throws Exception {
        boxIdValidation(boxId);
        moveCardToNextBox(cardId, boxId);
    }

    private void moveCardToNextBox(Integer cardId, Integer boxId) throws Exception {
        Box refBox = boxes.get(boxId);
        if (!refBox.hasCard(cardId)) { // Corrected condition
            throw new Exception("No card Found");
        }
        refBox.removeCard(cardId);
        int nextBoxId = Math.min(boxId + 1, 4);
        boxes.get(nextBoxId).addCard(cardId);
    }

    public void downgradeCard(Integer cardId, Integer boxId) throws Exception {
        boxIdValidation(boxId);
        moveCardToPreviousBox(cardId, boxId);
    }

    private void moveCardToPreviousBox(Integer cardId, Integer boxId) throws Exception {
        Box refBox = boxes.get(boxId);
        if (!refBox.hasCard(cardId)) { // Corrected condition
            throw new Exception("No card Found");
        }
        refBox.removeCard(cardId);
        int previousBoxId = Math.max(boxId - 1, 0);
        boxes.get(previousBoxId).addCard(cardId);
    }

}