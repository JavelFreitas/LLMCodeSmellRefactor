package org.example.studycards;

import java.util.List;
import java.util.Random;

public class FlashCard extends StudyMethod {
    private String methodName;

    public FlashCard(String methodName) {
        super(methodName);
        this.methodName = methodName;
    }

    @Override
    public String getMethodName() {
        return this.methodName;
    }

    @Override
    void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public int randomFlashCard(){
        List<Card> cards = cardManager.getCards();
        if (cards.isEmpty()) {
            System.out.println("No cards found");
            return -1;
        }

        Random random = new Random();

        return random.nextInt(cards.size() - 1 + 1) + 1;
    }
}
