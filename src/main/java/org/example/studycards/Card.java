package org.example.studycards;

public class Card {
    private String question;
    private String answer;

    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    private void updateCardData(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public void setQuestion(String question) {
        updateCardData(question, this.answer);
    }

    public void setAnswer(String answer) {
        updateCardData(this.question, answer);
    }

    public void edit(String question, String answer) {
        updateCardData(question, answer);
    }

    public boolean isQuestionAnswerMatch(String guess) {
        return guess.equalsIgnoreCase(answer);
    }

    public String getCardSummary() {
        return "Question: " + question + ", Answer: " + answer;
    }
}