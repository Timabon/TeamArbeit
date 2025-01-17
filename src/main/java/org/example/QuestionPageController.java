package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class QuestionPageController {

    @FXML
    private Label questionLabel;
    @FXML
    private Button optionA;
    @FXML
    private Button optionB;
    @FXML
    private Button optionC;
    @FXML
    private Button optionD;

    private GameLogic gameLogic; // Instance of GameLogic

    private boolean isFirstQuestion = true; // Flag to track if it's the first question

    public void initialize() throws IOException {
        // Load questions and initialize GameLogic
        QuestionLoader loader = new QuestionLoader();
        List<Question> questions = loader.loadQuestions("questions.json");
        gameLogic = new GameLogic(questions);//initialization of GameLogic

        displayQuestion();
    }

    private void displayQuestion() throws IOException {
        Question currentQuestion = gameLogic.getNextQuestion();

        if (currentQuestion != null) {
            questionLabel.setText(currentQuestion.getQuestionText());
            optionA.setText(currentQuestion.getOptions().get(0));
            optionB.setText(currentQuestion.getOptions().get(1));
            optionC.setText(currentQuestion.getOptions().get(2));
            optionD.setText(currentQuestion.getOptions().get(3));
        } else {
            loadGameOverPage(true); // Game over - user wins
        }
    }

public void loadGameOverPage(boolean isWin) throws IOException {
    loadGameOverPage(isWin, false); // Overloaded method for backward compatibility
}
    private void loadGameOverPage(boolean isWin, boolean firstQuestionWrong) throws IOException {
    Stage stage = (Stage) questionLabel.getScene().getWindow(); // Get the current stage
        int prizeAmount = gameLogic.getPrizeAmount(); // Always use the accumulated prize amount
        //int safePrize = gameLogic.getSafePrize(); // Get the safe prize amount


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gameover_page.fxml"));
        Scene gameOverScene = new Scene(loader.load());

        // Get the controller
        GameOverPageController controller = loader.getController();

        // Pass data to the controller
        controller.setScore(prizeAmount);

        // Determine result message
        String resultMessage = firstQuestionWrong
                ? "Game Over. You Lost!"
                : isWin
                ? "Congratulations! You Won! Your Prize: $" + prizeAmount
                : "Game Over! Your Prize: $" + prizeAmount;

        controller.setResultText(resultMessage);

        // Set the scene
        stage.setScene(gameOverScene);
    }

    @FXML
    private void handleOptionA() throws IOException {
        handleAnswer(0);
    }

    @FXML
    private void handleOptionB() throws IOException {
        handleAnswer(1);
    }

    @FXML
    private void handleOptionC() throws IOException {
        handleAnswer(2);
    }

    @FXML
    private void handleOptionD() throws IOException {
        handleAnswer(3);
    }

    private void handleAnswer(int selectedOption) throws IOException {
        boolean isCorrect = gameLogic.checkAnswer(selectedOption);

        if (isCorrect) {
            gameLogic.updatePrizeAmount(); // Update prize on correct answer

            // If it's the first question and correct, toggle the flag
            if (isFirstQuestion) {
                isFirstQuestion = false;
            }

            displayQuestion(); // Move to the next question
        } else {
            // If the first question is wrong, show "You lost" with score 0
            if (isFirstQuestion) {
                loadGameOverPage(false, true); // Game over, wrong on the first question
            } else {
                loadGameOverPage(false, false); // Game over, wrong after the first question
            }
        }
    }
}
