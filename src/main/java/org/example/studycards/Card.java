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

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Updates both the question and answer of the card.
     *
     * @param question The new question.
     * @param answer   The new answer.
     */
    public void edit(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    /**
     * Checks if the card's question contains a specific keyword (case-insensitive).
     *
     * @param keyword The keyword to search for.
     * @return True if the question contains the keyword, false otherwise.
     */
    public boolean questionContains(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return false;
        }
        return question.toLowerCase().contains(keyword.toLowerCase());
    }

    /**
     * Checks if the card's answer matches a specific value (case-insensitive).
     *
     * @param value The value to compare.
     * @return True if the answer matches, false otherwise.
     */
    public boolean isAnswerMatching(String value) {
        if (value == null || value.isBlank()) {
            return false;
        }
        return answer.equalsIgnoreCase(value);
    }

    /**
     * Provides a string representation of the card in a quiz-friendly format.
     *
     * @return The formatted question and placeholder for the answer.
     */
    public String formatForQuiz() {
        return "Q: " + question + "\nA: [Answer hidden]";
    }
}
