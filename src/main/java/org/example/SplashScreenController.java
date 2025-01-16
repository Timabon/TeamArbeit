package org.example;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SplashScreenController {

    @FXML
    private StackPane splashRoot; // The root of the splash screen

    public void initialize(Stage primaryStage) {
        // Create a fade-out effect for the splash screen
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), splashRoot);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(event -> {
            try {
                // Load the landing page after the splash screen fades out
                SceneSwitcher.switchScene("/landing_page.fxml", primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        fadeOut.play();
    }
}
