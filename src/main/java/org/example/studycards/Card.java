package org.example.studycards;

public class Card {
    private String question;
    private String answer;
    private int attempts;
    private int correctAnswers;

    public Card(String question, String answer) {
        validateAndSetInputs(question, answer);
        this.attempts = 0;
        this.correctAnswers = 0;
    }

    private void validateAndSetInputs(String question, String answer) {
        if (question == null || question.trim().isEmpty()) {
            throw new IllegalArgumentException("Question cannot be empty");
        }
        if (answer == null || answer.trim().isEmpty()) {
            throw new IllegalArgumentException("Answer cannot be empty");
        }
        this.question = question.trim();
        this.answer = answer.trim();
    }

    public void setQuestion(String question) {
        if (question == null || question.trim().isEmpty()) {
            throw new IllegalArgumentException("Question cannot be empty");
        }
        this.question = question.trim();
    }

    public void setAnswer(String answer) {
        if (answer == null || answer.trim().isEmpty()) {
            throw new IllegalArgumentException("Answer cannot be empty");
        }
        this.answer = answer.trim();
    }

    public void edit(String question, String answer) {
        validateAndSetInputs(question, answer);
    }

    public boolean checkAnswer(String providedAnswer) {
        if (providedAnswer == null) {
            return false;
        }
        attempts++;
        boolean isCorrect = answer.equalsIgnoreCase(providedAnswer.trim());
        if (isCorrect) {
            correctAnswers++;
        }
        return isCorrect;
    }

    public double getSuccessRate() {
        return attempts == 0 ? 0.0 : (double) correctAnswers / attempts * 100;
    }

    public boolean needsReview() {
        return getSuccessRate() < 75.0;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public int getAttempts() {
        return attempts;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }
}