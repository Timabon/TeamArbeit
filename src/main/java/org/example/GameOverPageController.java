package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.List;

public class GameOverPageController {

    @FXML
    public Label scoreLabel;
    @FXML
    public Label resultText;
    @FXML
    public Button yesButton;
    @FXML
    public Button noButton;


    // Setter to update the score label
    public void setScore(int prizeAmounts) {

        if (scoreLabel != null) {
            // Test with a static value
            scoreLabel.setText("Your Score: $" + prizeAmounts);
        } else {
            System.out.println("scoreLabel is null!");
        }
    }

    // Setter to update the result text
    public void setResultText(String result) {
        if (resultText != null) {
            resultText.setText(result); // Set the result text (You Won/You Lost)
        }
    }


    // Handle 'Yes' button click (Go to the question page)
    @FXML
    public void handleYesButton(ActionEvent actionEvent) {
        try {
            // Load the Question Page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/question_page.fxml"));
            Scene questionScene = new Scene(loader.load());

            // Get the current stage and set the new scene
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(questionScene);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception (you can show an error message if needed)
        }
    }

    // Handle 'No' button click (Close the application)
    @FXML
    public void handleNoButton(ActionEvent actionEvent) {
        // Close the application
        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}

