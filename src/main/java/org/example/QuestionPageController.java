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


    private List<Question> questions;
    private int currentQuestionIndex = 0;

    private void loadGameOverPage(String resultText) throws IOException {
        // Load the GameOverPage FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gameover_page.fxml"));
        Parent root = loader.load();

        // Get the controller of the GameOverPage
        GameOverPageController gameOverController = loader.getController();
        gameOverController.setResultText(resultText); // Pass the result text

        // Switch to the GameOver page
        Stage stage = (Stage) questionLabel.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void initialize() throws IOException {
        QuestionLoader loader = new QuestionLoader();
        questions = loader.loadQuestions("src/main/resources/questions25TeamArbeit.csv");
        displayQuestion();
    }

    private void displayQuestion() throws IOException {
        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            questionLabel.setText(currentQuestion.getQuestionText());
            optionA.setText(currentQuestion.getOptions().get(0));
            optionB.setText(currentQuestion.getOptions().get(1));
            optionC.setText(currentQuestion.getOptions().get(2));
            optionD.setText(currentQuestion.getOptions().get(3));
        } else {
            questionLabel.setText("Game Over!");
            loadGameOverPage("You won!"); // Pass the result to GameOverPage

        }
    }

    @FXML
    private void handleOptionA() throws IOException {
        checkAnswer(0);
    }

    @FXML
    private void handleOptionB() throws IOException {
        checkAnswer(1);
    }

    @FXML
    private void handleOptionC() throws IOException {
        checkAnswer(2);
    }

    @FXML
    private void handleOptionD() throws IOException {
        checkAnswer(3);
    }


    private void checkAnswer(int selectedOption) throws IOException {
        Question currentQuestion = questions.get(currentQuestionIndex);
        if (currentQuestion.isSelectedOptionCorrect(selectedOption)) {
            currentQuestionIndex++;
            displayQuestion();
        } else {
            loadGameOverPage("You lost!"); // Pass the result to GameOverPage
        }
    }
}


