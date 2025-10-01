package org.example.studycards;

public class Card {
    private String question;
    private String answer;

    public Card(String question, String answer) {
        if (validateCard(question, answer)) {
            throw new IllegalArgumentException("Invalid question or answer");
        }
        this.question = question;
        this.answer = answer;
    }

    private boolean validateCard(String question, String answer) {
        return question == null || answer == null
                || question.trim().isEmpty() || answer.trim().isEmpty();
    }

    public boolean matches(String searchTerm) {
        if (searchTerm == null)
            return false;
        String term = searchTerm.toLowerCase();
        return question.toLowerCase().contains(term)
                || answer.toLowerCase().contains(term);
    }

    public boolean isValid() {
        return !validateCard(this.question, this.answer);
    }

    public void edit(String question, String answer) {
        if (validateCard(question, answer)) {
            throw new IllegalArgumentException("Invalid question or answer");
        }
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
    this.answer = answer;
}
}