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

    public void updateQuestion(String newQuestion) {
        if (newQuestion == null || newQuestion.trim().isEmpty()) {
            throw new IllegalArgumentException("Question cannot be null or empty.");
        }
        this.question = newQuestion;
    }

    public void updateAnswer(String newAnswer) {
        if (newAnswer == null || newAnswer.trim().isEmpty()) {
            throw new IllegalArgumentException("Answer cannot be null or empty.");
        }
        this.answer = newAnswer;
    }

    public void updateContent (String newQuestion, String newAnswer) {
        updateQuestion(newQuestion);
        updateAnswer(newAnswer);
    }

    @Override
    public String toString() {
        return "Card{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
