package org.example.studycards;

public class Card {
    private String question;
    private String answer;

    private Card() {}

    public Card(String question, String answer) {
        this.setQuestion(question);
        this.setAnswer(answer);
    }

    public String getQuestion() {
        return question;
    }

    private void setQuestion(String question) {
        if (question == null || question.trim().isEmpty()) {
            throw new IllegalArgumentException("Question cannot be null or empty");
        }
        this.question = question.trim();
    }

    public String getAnswer() {
        return answer;
    }

    private void setAnswer(String answer) {
        if (answer == null || answer.trim().isEmpty()) {
            throw new IllegalArgumentException("Answer cannot be null or empty");
        }
        this.answer = answer.trim();
    }

    public void edit(String question, String answer) {
        this.setQuestion(question);
        this.setAnswer(answer);
    }

    public boolean isQuestionValid() {
        return !question.trim().isEmpty();
    }

    public boolean isAnswerValid() {
        return !answer.trim().isEmpty();
    }

    public String getCardDetails() {
        return String.format("Question: %s\nAnswer: %s", question, answer);
    }
}