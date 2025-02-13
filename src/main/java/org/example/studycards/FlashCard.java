package org.example.studycards;

import java.util.List;
import java.util.Random;

public class FlashCard extends StudyMethod {
    private String methodName; // 🔹 Adicionado atributo para armazenar o nome do método

    public FlashCard(String methodName) {
        super(methodName);
        this.methodName = methodName;
    }

    @Override
    public String getMethodName() {
        return this.methodName; // 🔹 Retorna o nome armazenado
    }

    @Override
    void setMethodName(String methodName) {
        this.methodName = methodName; // 🔹 Atualiza o nome armazenado
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
