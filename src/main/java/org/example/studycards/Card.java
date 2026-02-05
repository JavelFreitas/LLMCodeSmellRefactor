package org.example.studycards;

public class Card {
    private String question;
    private String answer;

    public Card(String question, String answer) {
        validateInput(question, "Question");
        validateInput(answer, "Answer");
        this.question = question;
        this.answer = answer;
    }
    public String displayQuestion() {
        // Exibe a pergunta com formatação apropriada
        return "Q: " + question;
    }

    public boolean validateAnswer(String userAnswer) {
        // Compara a resposta dada pelo usuário com a resposta correta
        if (userAnswer == null) {
            return false;
        }
        return this.answer.equalsIgnoreCase(userAnswer.trim());
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }
    public String getQuestion() {
        return question;
    }


    public void edit(String newQuestion, String newAnswer) {
        // Atualiza os campos com validação, encapsulando a lógica de edição
        validateInput(newQuestion, "New Question");
        validateInput(newAnswer, "New Answer");
        this.question = newQuestion;
        this.answer = newAnswer;
    }
    private void validateInput(String input, String fieldName) {
        // Valida entradas para evitar valores nulos ou vazios
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty.");
        }
    }
    @Override
    public String toString() {
        // Retorna uma representação útil do Card
        return "Question: " + question;
    }
}