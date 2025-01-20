package org.example;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class GameOverPageController {

    @FXML
    public Label scoreLabel;
    @FXML
    public Label resultText;
    @FXML
    public Button yesButton;
    @FXML
    public Button noButton;
    @FXML
    public Pane confettiPane;
    private ChangeListener<Bounds> confettiPaneListener;


    public void initialize() {

        // Add hover and click effects to buttons
        addHoverEffect(yesButton);
        addHoverEffect(noButton);

        // Ensure confettiPane has a valid size
        confettiPane.setPrefSize(800.0, 450.0); // Set the expected size of the pane
    }

    // Setter to update the score label
    public void setScore(int prizeAmounts) {

        if (scoreLabel != null) {
            // Test with a static value
            scoreLabel.setText("Your Score: $" + prizeAmounts);
        }
    }

    // Setter to update the result text
    public void setResultText(String result) {
        if (resultText != null) {
            resultText.setText(result); // Set the result text (You Won/You Lost)
        }
    }

    // Confetti method
    public void playConfetti() {

        // Ensure the Pane has valid dimensions
        if (confettiPane.getWidth() <= 0 || confettiPane.getHeight() <= 0) {
            System.out.println("ConfettiPane dimensions are invalid. Waiting for layout...");
            confettiPane.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.getWidth() > 0 && newValue.getHeight() > 0) {
                    System.out.println("ConfettiPane dimensions are now valid: " + newValue.getWidth() + "x" + newValue.getHeight());
                    new ConfettiAnimationController().start(confettiPane);
                    confettiPane.layoutBoundsProperty().removeListener(confettiPaneListener);
                }
            });
        } else {
            new ConfettiAnimationController().start(confettiPane); // Start immediately if dimensions are valid
        }
    }


    // Handle 'Yes' button click (Go to the question page)
    @FXML
    public void handleYesButton(ActionEvent actionEvent) {
        try {
            // Load the Question Page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/landing_page.fxml"));
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

    //Button shadow effect
    private void addHoverEffect(Button button) {
        // Mouse entered (hover)
        button.setOnMouseEntered(event -> button.setStyle(
                "-fx-font-size: 14px; -fx-text-fill: white; -fx-background-color: #005BB5; -fx-background-radius: 15px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.7), 12, 0.7, 0, 4);"
        ));

        // Mouse exited
        button.setOnMouseExited(event -> button.setStyle(
                "-fx-font-size: 14px; -fx-text-fill: white; -fx-background-color: blue; -fx-background-radius: 15px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 8, 0.5, 0, 2);"
        ));
    }
}

