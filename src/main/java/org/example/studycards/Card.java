package org.example.studycards;

import java.util.Objects;

public class Card {
    private String question;
    private String answer;

    public Card(String question, String answer) {
        validateInput(question, answer);
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        validateInput(question, this.answer); // Valida o novo valor da pergunta
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        validateInput(this.question, answer);
        this.answer = answer;
    }

    public void edit(String newQuestion, String newAnswer) {
        validateInput(newQuestion, newAnswer);
        this.question = newQuestion;
        this.answer = newAnswer;
    }

    private void validateInput(String question, String answer) {
        if (question == null || question.isBlank()) {
            throw new IllegalArgumentException("Question cannot be null or blank.");
        }
        if (answer == null || answer.isBlank()) {
            throw new IllegalArgumentException("Answer cannot be null or blank.");
        }
    }

    public boolean isCorrectAnswer(String userAnswer) {
        return Objects.equals(this.answer.trim().toLowerCase(), userAnswer.trim().toLowerCase());
    }

    @Override
    public String toString() {
        return "Question: " + question + "\nAnswer: " + answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(question, card.question) && Objects.equals(answer, card.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, answer);
    }
}