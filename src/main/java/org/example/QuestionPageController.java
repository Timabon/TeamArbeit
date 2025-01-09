package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.List;

public class QuestionPageController {

    @FXML
    private Label questionLabel;
    @FXML
    private Button option1Button;
    @FXML
    private Button option2Button;
    @FXML
    private Button option3Button;
    @FXML
    private Button option4Button;

    private List<Question> questions;
    private int currentQuestionIndex = 0;

    public void initialize() {
        QuestionLoader loader = new QuestionLoader();
        questions = loader.loadQuestions("src/main/resources/questions25TeamArbeit.csv");
        displayQuestion();
    }

    private void displayQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            questionLabel.setText(currentQuestion.getQuestionText());
            option1Button.setText(currentQuestion.getOptions().get(0));
            option2Button.setText(currentQuestion.getOptions().get(1));
            option3Button.setText(currentQuestion.getOptions().get(2));
            option4Button.setText(currentQuestion.getOptions().get(3));
        } else {
            questionLabel.setText("Game Over!");
        }
    }

    @FXML
    private void handleOption1() {
        checkAnswer(0);
    }

    @FXML
    private void handleOption2() {
        checkAnswer(1);
    }

    @FXML
    private void handleOption3() {
        checkAnswer(2);
    }

    @FXML
    private void handleOption4() {
        checkAnswer(3);
    }

    private void checkAnswer(int selectedOption) {
        Question currentQuestion = questions.get(currentQuestionIndex);
        if (currentQuestion.isSelectedOptionCorrect(selectedOption)) {
            currentQuestionIndex++;
            displayQuestion();
        } else {
            questionLabel.setText("Wrong answer! Game over!");
        }
    }
}