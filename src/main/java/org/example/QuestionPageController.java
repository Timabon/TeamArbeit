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

    public void initialize() throws IOException {
        // Load questions and initialize GameLogic
        QuestionLoader loader = new QuestionLoader();
        List<Question> questions = loader.loadQuestions("questions.json");
        gameLogic = new GameLogic(questions);//inicialisation of GameLogic

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
            loadGameOverPage("You won!"); // Game over - user wins
        }
    }

    private void loadGameOverPage(String resultText) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gameover_page.fxml"));
        Parent root = loader.load();

        GameOverPageController gameOverController = loader.getController();
        gameOverController.setResultText(resultText);

        Stage stage = (Stage) questionLabel.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
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
            displayQuestion();
        } else {
            loadGameOverPage("You lost!"); // Game over - user loses
        }
    }
}
