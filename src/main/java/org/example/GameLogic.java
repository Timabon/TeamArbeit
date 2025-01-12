package org.example;

import java.util.ArrayList;
import java.util.List;


public class GameLogic {

    // Tracks the current question number
    private int currentQuestionIndex = 0;

    // Keeps the player's prize amount
    private int prizeAmount = 0;

    // Predefined prize amounts for each question
    private List<Integer> prizeAmounts;

    // List of all the questions
    private List<Question> questions;

    // Constructor to set up the game logic
    public GameLogic(List<Question> questions) {
        // Initialize questions and prize amounts
        this.questions = questions;

        // Example prize amounts (you can change these)
        this.prizeAmounts = new ArrayList<>();
        prizeAmounts.add(100);  // Question 1
        prizeAmounts.add(200);  // Question 2
        prizeAmounts.add(300);  // Question 3
        prizeAmounts.add(500);  // Question 4
        prizeAmounts.add(1000); // Question 5
    }

    /**
     * Get the next question.
     * @return The next question, or null if no more questions are left.
     */
    public Question getNextQuestion() {
        // Check if we have more questions
        if (currentQuestionIndex < questions.size()) {
            // Return the current question and move to the next
            return questions.get(currentQuestionIndex++);
        } else {
            // No more questions
            return null;
        }
    }

    /**
     * Check if the selected answer is correct.
     * @param selectedIndex The index of the selected answer (0, 1, 2, or 3).
     * @return true if the answer is correct, false otherwise.
     */
    public boolean checkAnswer(int selectedIndex) {
        // Get the current question (the one just answered)
        Question currentQuestion = questions.get(currentQuestionIndex - 1);

        // Compare the selected answer with the correct answer
        return currentQuestion.isCorrectAnswer(selectedIndex);
    }

    /**
     * Update the prize amount for a correct answer.
     */
    public void updatePrizeAmount() {
        // Make sure we don't go out of bounds
        if (currentQuestionIndex - 1 < prizeAmounts.size()) {
            // Add the prize amount for the current question
            prizeAmount = prizeAmounts.get(currentQuestionIndex - 1);
        }
    }

    /**
     * Check if the game is over.
     * @return true if there are no more questions, false otherwise.
     */
    public boolean isGameOver() {
        // Game is over if there are no more questions
        return currentQuestionIndex >= questions.size();
    }

    /**
     * Get the current prize amount.
     * @return The current prize amount.
     */
    public int getPrizeAmount() {
        return prizeAmount;
    }
}
