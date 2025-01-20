package org.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class LevelCustomController {

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
        @FXML
        private Label scoreLabel;

        private GameLogic gameLogic; // Instance of GameLogic

        private boolean isFirstQuestion = true; // Flag to track if it's the first question

        public void initialize() throws IOException {

            //Add hover effect
            addHoverEffect(optionA);
            addHoverEffect(optionB);
            addHoverEffect(optionC);
            addHoverEffect(optionD);

            // Load questions and initialize GameLogic
            QuestionLoader loader = new QuestionLoader();
            List<Question> questions = loader.loadQuestions("level_custom.json");
            gameLogic = new GameLogic(questions);//initialization of GameLogic

            // Add hover and click effects to buttons
            addHoverEffect(optionA);
            addHoverEffect(optionB);
            addHoverEffect(optionC);
            addHoverEffect(optionD);

            // Other initialization logic...
            scoreLabel.setText("Score: $0");

            // Display initial score
            updateScoreLabel();
            displayQuestion();
        }
        private void updateScoreLabel() {
            if (scoreLabel != null) {
                scoreLabel.setText("Score: $" + gameLogic.getPrizeAmount());
            }
        }

        private void addHoverEffect(Button button) {
            // Mouse entered (hover)
            button.setOnMouseEntered(event -> button.setStyle(
                    "-fx-font-size: 12px; -fx-text-fill: white; -fx-background-color: #005BB5; -fx-background-radius: 15px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.7), 12, 0.7, 0, 4);"
            ));

            // Mouse exited
            button.setOnMouseExited(event -> button.setStyle(
                    "-fx-font-size: 12px; -fx-text-fill: white; -fx-background-color: blue; -fx-background-radius: 15px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 8, 0.5, 0, 2);"
            ));

            // Mouse pressed (click)
            button.setOnMousePressed(event -> button.setStyle(
                    "-fx-font-size: 12px; -fx-text-fill: white; -fx-background-color: #003F7D; -fx-background-radius: 15px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.3), 6, 0.3, 0, 1);"
            ));

            // Mouse released
            button.setOnMouseReleased(event -> button.setStyle(
                    "-fx-font-size: 12px; -fx-text-fill: white; -fx-background-color: #005BB5; -fx-background-radius: 15px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.7), 12, 0.7, 0, 4);"
            ));
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
                triggerConfettiAnimation(); // Show confetti animation
                loadGameOverPage(true); // Game over - user wins
            }
        }

        private void triggerConfettiAnimation() {
            Pane rootPane = (Pane) questionLabel.getScene().getRoot(); // Root container of the current scene
            // Example confetti animation logic
            ConfettiAnimationController confetti = new ConfettiAnimationController(); // Replace with your confetti implementation
            confetti.start(rootPane); // Add confetti to the root pane
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
                    ? "    Congratulations!\nYou Won: $" + prizeAmount
                    : "Game Over! Your Prize: $" + prizeAmount;

            controller.setResultText(resultMessage);
            if (isWin) {
                controller.playConfetti(); // Play confetti if the player wins
            }

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
                updateScoreLabel(); // Update score label

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


