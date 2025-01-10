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
        initializeBoxes();
    }

    public List<Box> getBoxes() {
        return boxes;
    }

    public String getRandomCard(List<Box> otherBoxes) {
        if (otherBoxes == null || otherBoxes.isEmpty()) {
            return null;
        }

        Integer randomCardId = getRandomCardFromBoxes(otherBoxes);
        if (randomCardId == null) {
            return "No card found";
        }

        return formatCardResponse(randomCardId);
    }

    private Integer getRandomCardFromBoxes(List<Box> otherBoxes) {
        Box allBoxes = new Box();
        for (Box box : otherBoxes) {
            allBoxes.addCards(box.getCards());
        }
        return allBoxes.getRandomCard();
    }

    private String formatCardResponse(Integer cardId) {
        CardManager manager = CardManager.getCardManager();
        Card card = manager.getCard(cardId);
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

    private void validateBoxId(Integer boxId) throws Exception {
        if (boxId == null || boxId > (boxes.size() - 1) || boxId <= 0) {
            throw new Exception("Invalid box ID");
        }
    }



    private int getTargetBoxIndex(Integer currentBox, boolean isUpgrade) {
        return isUpgrade ?
                Math.min(currentBox + 1, 4) :
                Math.max(currentBox - 1, 0);
    }

    public void upgradeCard(Integer cardId, Integer boxId) throws Exception {
        moveCard(cardId, boxId, true);
    }

    public void downgradeCard(Integer cardId, Integer boxId) throws Exception {
        moveCard(cardId, boxId, false);
    }

    private void moveCard(Integer cardId, Integer boxId, boolean isUpgrade) throws Exception {
        validateBoxId(boxId);
        Box sourceBox = boxes.get(boxId);
        sourceBox.validateCard(cardId);

        sourceBox.removeCard(cardId);
        int targetBoxIndex = getTargetBoxIndex(boxId, isUpgrade);
        boxes.get(targetBoxIndex).addCard(cardId);
    }

    public String getRandomCardFromBoxFormatted() {
        String response = "";
        response += getMethodName();
        response += getRandomCard(getBoxes());
        return response;
    }
}