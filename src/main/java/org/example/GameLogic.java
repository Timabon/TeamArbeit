package org.example;


import java.util.List;

public class GameLogic {
    private int currentQuestionIndex = 0; // Current question
    private int prizeAmount = 0;         // Current profit
    private List<Question> questions;   // list of questions
    private List<Integer> prizeAmounts; // prize levels

    public GameLogic(List<Question> questions) {
        this.questions = questions;
        this.prizeAmounts = List.of(500, 1000, 2000, 3000, 5000, 7500, 10000, 15000, 20000, 30000, 40000, 50000, 75000, 100000, 150000, 200000, 250000, 300000, 400000, 500000, 600000, 700000, 800000, 900000, 1000000);
    }

    public Question getNextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            return questions.get(currentQuestionIndex++);
        }
        return null; // No further questions
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
