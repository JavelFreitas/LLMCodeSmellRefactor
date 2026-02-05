package org.example.studycards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardManager {
    private Map<Integer, Card> cards;
    private Integer nextID = 1;

    private static CardManager instance = null;

    // Construtor privado para o padrão Singleton
    private CardManager() {
        this.cards = new HashMap<>();
    }

    // Método para obter a instância única de CardManager (Singleton)
    public static CardManager getCardManager() {
        if (instance == null) {
            instance = new CardManager();
        }
        return instance;
    }

    // Método que lida com a pesquisa de cartões
    public List<String> handleSearch(String text) {
        return searchInCards(text);
    }

    // Método para formatar o cartão
    public String formatCard(Integer id) {
        Card card = this.getCard(id);
        if (card != null) {
            return "[id: " + id + "] Question: " + card.getQuestion() + " Answer: " + card.getAnswer();
        }
        return "Card not found";
    }

    // Obtém todos os cartões como um mapa
    public Map<Integer, Card> getCardsMap() {
        return cards;
    }

    // Obtém todos os cartões
    public List<Card> getCards() {
        return new ArrayList<>(cards.values());
    }

    // Obtém uma lista de cartões a partir de uma lista de IDs
    public List<Card> getCards(List<Integer> ids) {
        List<Card> responseCards = new ArrayList<>();
        for (Integer id : ids) {
            Card card = cards.get(id);
            if (card != null) {
                responseCards.add(card);
            }
        }
        return responseCards;
    }

    // Obtém um cartão a partir do ID
    public Card getCard(Integer id) {
        return cards.get(id);
    }

    // Adiciona um novo cartão
    public Integer addCard(String question, String answer) {
        if (validateCard(question, answer)) {
            throw new IllegalArgumentException("Invalid question or answer");
        }
        Card card = new Card(question, answer);
        Integer response = nextID;
        cards.put(nextID, card);
        nextID++;
        return response;
    }

    // Remove um cartão com base no ID
    public void removeCard(Integer id) {
        cards.remove(id);
    }

    // Atualiza um cartão com base no ID
    public void updateCard(Integer id, String question, String answer) {
        if (validateCard(question, answer)) {
            throw new IllegalArgumentException("Invalid question or answer");
        }
        Card card = cards.get(id);
        card.edit(question, answer);
    }

    // Valida se a questão ou resposta do cartão é válida
    private boolean validateCard(String question, String answer) {
        return question == null || question.isEmpty() || answer == null || answer.isEmpty();
    }

    // Pesquisa nos cartões
    public List<String> searchInCards(String search) {
        List<String> responseCards = new ArrayList<>();
        for (int id : cards.keySet()) {
            Card card = cards.get(id);
            if (card.getQuestion().contains(search) || card.getAnswer().contains(search)) {
                responseCards.add(formatCard(id));
            }
        }
        return responseCards;
    }
}
