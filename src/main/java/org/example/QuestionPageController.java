package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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

    public void initialize() {
        QuestionLoader loader = new QuestionLoader();
        questions = loader.loadQuestions("src/main/resources/questions25TeamArbeit.csv");
        displayQuestion();
    }

    private void displayQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            questionLabel.setText(currentQuestion.getQuestionText());
            optionA.setText(currentQuestion.getOptions().get(0));
            optionB.setText(currentQuestion.getOptions().get(1));
            optionC.setText(currentQuestion.getOptions().get(2));
            optionD.setText(currentQuestion.getOptions().get(3));
        } else {
            questionLabel.setText("Game Over!");
        }
    }

    @FXML
    private void handleOptionA() {
        checkAnswer(0);
    }

    @FXML
    private void handleOptionB() {
        checkAnswer(1);
    }

    @FXML
    private void handleOptionC() {
        checkAnswer(2);
    }

    @FXML
    private void handleOptionD() {
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


