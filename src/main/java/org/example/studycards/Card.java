package org.example.studycards;

public class Card {
    private String question;
    private String answer;

    public Card(String question, String answer) {
        validateInputs(question, answer);
        this.question = question;
        this.answer = answer;
    }

    private void validateInputs(String question, String answer) {
        if (question == null || question.trim().isEmpty()) {
            throw new IllegalArgumentException("Question cannot be empty");
        }
        if (answer == null || answer.trim().isEmpty()) {
            throw new IllegalArgumentException("Answer cannot be empty");
        }
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        validateInputs(question, this.answer);
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        validateInputs(this.question, answer);
        this.answer = answer;
    }

    public void edit(String question, String answer) {
        validateInputs(question, answer);
        setQuestion(question);
        setAnswer(answer);
    }

    public boolean checkAnswer(String attempt) {
        return this.answer.equalsIgnoreCase(attempt.trim());
    }

    public boolean containsText(String searchText) {
        if (searchText == null || searchText.trim().isEmpty()) {
            return false;
        }
        return question.toLowerCase().contains(searchText.toLowerCase()) ||
                answer.toLowerCase().contains(searchText.toLowerCase());
    }
}