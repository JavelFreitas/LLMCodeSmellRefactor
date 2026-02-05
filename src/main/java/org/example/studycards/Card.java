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

    // Setter remains public for the tests, but now we have control
    public void setQuestion(String question) {
        // Here you can add validation or other logic if needed
        if (question == null || question.isEmpty()) {
            throw new IllegalArgumentException("Question cannot be null or empty"); // Example validation
        }
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    // Setter remains public for the tests, but now we have control
    public void setAnswer(String answer) {
        // Here you can add validation or other logic if needed
        if (answer == null || answer.isEmpty()) {
            throw new IllegalArgumentException("Answer cannot be null or empty"); // Example validation
        }
        this.answer = answer;
    }

    public void edit(String question, String answer) {
        setQuestion(question);
        setAnswer(answer);
    }

    public boolean isCorrectAnswer(String providedAnswer) {
        return this.answer.equalsIgnoreCase(providedAnswer);
    }

    public String buildCardResponse(Integer randomCard) {
        String response = "[" + randomCard + "] ";
        response += "The random question was: " + this.question + " | ";
        response += "The answer is: " + this.answer;
        return response;
    }

    @Override
    public String toString() {
        return "Question: " + question + "\nAnswer: " + answer;
    }
}