package org.example;


import java.util.List;

public class GameLogic {
    private int currentQuestionIndex = 0; // Aktuelle Frage
    private int prizeAmount = 0;         // Aktueller Gewinn
    private List<Question> questions;   // Liste der Fragen
    private List<Integer> prizeAmounts; // Gewinnstufen

    public GameLogic(List<Question> questions) {
        this.questions = questions;
        this.prizeAmounts = List.of(500, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 125000, 250000, 500000, 1000000);
    }

    public Question getNextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            return questions.get(currentQuestionIndex++);
        }
        return null; // Keine weiteren Fragen
    }

    public boolean checkAnswer(int selectedIndex) {
        Question currentQuestion = questions.get(currentQuestionIndex - 1);
        return currentQuestion.isCorrectAnswer(selectedIndex);
    }

    public void updatePrizeAmount() {
        if (currentQuestionIndex - 1 < prizeAmounts.size()) {
            prizeAmount = prizeAmounts.get(currentQuestionIndex - 1);
            System.out.println("Your current score is: $" + prizeAmount); // Print the updated score
        }
    }

    public boolean isGameOver() {

        return currentQuestionIndex >= questions.size();
    }

    public int getPrizeAmount() {

        return prizeAmount;
    }

    public int getSafePrize() {
        if (currentQuestionIndex >= 15) return 1000000;
        if (currentQuestionIndex >= 10) return 32000;
        if (currentQuestionIndex >= 5) return 1000;
        return 0;
    }

}
