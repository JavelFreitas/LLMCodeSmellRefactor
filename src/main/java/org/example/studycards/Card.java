package org.example.studycards;

public class Card {
    private String question;
    private String answer;

    public Card(String question, String answer) {
        if (question == null || question.isEmpty()) {
            throw new IllegalArgumentException("Question cannot be empty");
        }
        if (answer == null || answer.isEmpty()) {
            throw new IllegalArgumentException("Answer cannot be empty");
        }
        this.question = question;
        this.answer = answer;
    }

    // Método para definir a questão
    public void setQuestion(String question) {
        if (question == null || question.isEmpty()) {
            throw new IllegalArgumentException("Question cannot be empty");
        }
        this.question = question;
    }

    // Método para definir a resposta
    public void setAnswer(String answer) {
        if (answer == null || answer.isEmpty()) {
            throw new IllegalArgumentException("Answer cannot be empty");
        }
        this.answer = answer;
    }

    // Método que retorna a questão
    public String getQuestion() {
        return question;
    }

    // Método que retorna a resposta
    public String getAnswer() {
        return answer;
    }

    // Método que retorna a questão formatada, agora com comportamento de lógica
    public String getFormattedQuestion() {
        return "Q: " + question;
    }

    // Método que retorna a resposta formatada com comportamento adicional
    public String getFormattedAnswer() {
        return "A: " + answer;
    }

    // Edita a questão e resposta, garantindo que elas sempre estejam válidas
    public void edit(String question, String answer) {
        if (question == null || question.isEmpty()) {
            throw new IllegalArgumentException("Question cannot be empty");
        }
        if (answer == null || answer.isEmpty()) {
            throw new IllegalArgumentException("Answer cannot be empty");
        }
        this.question = question;
        this.answer = answer;
    }

    // Método para comparar a questão com outra, o que pode ser útil para buscas ou modificações
    public boolean isQuestionSimilar(String otherQuestion) {
        return question.equalsIgnoreCase(otherQuestion);
    }

    // Método que retorna o cartão formatado com o ID, mas com um comportamento mais interessante
    public String formatForDisplay(Integer id) {
        return String.format("[ID: %d] %s %s", id, getFormattedQuestion(), getFormattedAnswer());
    }

    // Método para verificar se a questão ou resposta estão vazias
    public boolean isValid() {
        return !(question == null || question.isEmpty() || answer == null || answer.isEmpty());
    }
}
