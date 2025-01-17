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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        if (validateField(question)) {
            throw new IllegalArgumentException("Invalid question");
        }
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        if (validateField(answer)) {
            throw new IllegalArgumentException("Invalid answer");
        }
        this.answer = answer;
    }

    public void edit(String question, String answer) {
        if (validateCard(question, answer)) {
            throw new IllegalArgumentException("Invalid question or answer");
        }
        setQuestion(question);
        setAnswer(answer);
    }

    public String format(Integer id) {
        return "[id: " + id + "] Question: " + getQuestion() + " Answer: " + getAnswer();
    }

    public boolean contains(String search) {
        return getQuestion().contains(search) || getAnswer().contains(search);
    }

    private boolean validateField(String field) {
        return field == null || field.isEmpty();
    }

    private boolean validateCard(String question, String answer) {
        return validateField(question) || validateField(answer);
    }
}