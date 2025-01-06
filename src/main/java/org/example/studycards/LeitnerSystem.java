package org.example.studycards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeitnerSystem extends StudyMethod {
    List<Box> boxes = null;

    public LeitnerSystem(String methodName) {
        super(methodName);
        initializeBoxes();
    }

    private void initializeBoxes() {
        boxes = new ArrayList<>(Arrays.asList(new Box(), new Box(), new Box(), new Box(), new Box()));
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
        return buildBoxesRepresentation();
    }

    private String buildBoxesRepresentation() {
        StringBuilder response = new StringBuilder();
        int index = 0;
        for (Box box : boxes) {
            response.append("Box ").append(index).append(": ").append(box.toString()).append("\n");
            index++;
        }
        return response.toString();
    }

    public void clearBoxes() {
        initializeBoxes();
    }

    public List<Box> getBoxes() {
        return boxes;
    }

    public String getRandomCard(List<Box> otherBoxes) {
        if (otherBoxes == null || otherBoxes.isEmpty()) {
            return "No card found";
        }

        Box combinedBox = combineAllCards(otherBoxes);
        Integer randomCard = combinedBox.getRandomCard();
        return randomCard != null ? buildRandomCardResponse(randomCard) : "No card found";
    }

    private Box combineAllCards(List<Box> otherBoxes) {
        Box combinedBox = new Box();
        for (Box box : otherBoxes) {
            combinedBox.addCards(box.getCards());
        }
        return combinedBox;
    }

    private String buildRandomCardResponse(Integer randomCard) {
        CardManager manager = CardManager.getCardManager();
        Card card = manager.getCard(randomCard);
        return "[" + randomCard + "] The random question was: " + card.getQuestion() +
                " | The answer is: " + card.getAnswer();
    }

    public void addCardToBox(Integer id, Integer boxId) {
        boxes.get(boxId).addCard(id);
    }

    public void removeCardFromBox(Integer id, Integer boxId) {
        boxes.get(boxId).removeCard(id);
    }

    public Card takeCardFromBox(Integer boxId) {
        Integer cardId = boxes.get(boxId).getRandomCard();
        return this.cardManager.getCard(cardId);
    }

    public void boxIdValidation(Integer boxId) throws Exception {
        if (boxId == null || boxId > (boxes.size() - 1) || boxId < 0) {
            throw new Exception("Invalid box ID");
        }
    }

    public void upgradeCard(Integer cardId, Integer boxId) throws Exception {
        processCardMovement(cardId, boxId, 1);
    }

    public void downgradeCard(Integer cardId, Integer boxId) throws Exception {
        processCardMovement(cardId, boxId, -1);
    }

    private void processCardMovement(Integer cardId, Integer boxId, int direction) throws Exception {
        boxIdValidation(boxId);

        Box refBox = boxes.get(boxId);
        if (!refBox.hasCard(cardId)) {
            throw new Exception("No card Found");
        }

        refBox.removeCard(cardId);
        int newBoxId = Math.min(Math.max(boxId + direction, 0), boxes.size() - 1);
        boxes.get(newBoxId).addCard(cardId);
    }
}
