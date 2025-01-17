package org.example;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SplashScreenController {

    @FXML
    private StackPane splashRoot; // The root of the splash screen
    @FXML
    private Pane blackBackground; // Black background pane

    private Stage primaryStage;

    public void initialize() {
        System.out.println("SplashScreenController: initialize() called.");

        if (splashRoot == null) {
            System.out.println("splashRoot is null! Initialization aborted.");
            return;
        }

        // Force layout pass
        splashRoot.applyCss();
        splashRoot.layout();
        System.out.println("After layout pass: BlackBackground size: " + blackBackground.getWidth() + "x" + blackBackground.getHeight());
// Add listener for layout changes
        blackBackground.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Updated BlackBackground size: " + newValue.getWidth() + "x" + newValue.getHeight());
        });
        System.out.println("BlackBackground size: " + blackBackground.getWidth() + "x" + blackBackground.getHeight());

        // Add the shake effect at the start
        playShakeEffect();
    }
    // Set the primary stage after loading the FXML
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Start the fade-out transition
        setupFadeTransition();
    }
        private void setupFadeTransition (){
            if (primaryStage == null) {
                System.out.println("PrimaryStage is null! Cannot start fade transition.");
                return;
            }

            // Fade out splash screen content
            FadeTransition splashFade = new FadeTransition(Duration.seconds(3), splashRoot);
            splashFade.setFromValue(1.0);
            splashFade.setToValue(0.0);

            // Fade in black background
            FadeTransition blackFade = new FadeTransition(Duration.seconds(3), blackBackground);
            blackFade.setFromValue(0.0);
            blackFade.setToValue(1.0);

            // Play both animations sequentially
            SequentialTransition transition = new SequentialTransition(splashFade, blackFade);
            transition.setOnFinished(event -> {
                try {
                    // Load the landing page after the fade-out
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/landing_page.fxml"));
                    Scene landingScene = new Scene(loader.load());
                    primaryStage.setScene(landingScene);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            transition.play();
    }

    private void playShakeEffect() {
        if (splashRoot == null) {
            System.out.println("splashRoot is null!");
            return;
        }

        // Define the shake animation
        Timeline shakeTimeline = new Timeline(
                // Move left
                new KeyFrame(Duration.millis(100), new KeyValue(splashRoot.translateXProperty(), -5)),
                // Move right
                new KeyFrame(Duration.millis(200), new KeyValue(splashRoot.translateXProperty(), 5)),
                // Back to center
                new KeyFrame(Duration.millis(300), new KeyValue(splashRoot.translateXProperty(), -5))
                // Right again
                //new KeyFrame(Duration.millis(200), new KeyValue(splashRoot.translateXProperty(), 5)),
                // Settle at the original position
               // new KeyFrame(Duration.millis(250), new KeyValue(splashRoot.translateXProperty(), 0))
        );

        // Play the animation
        shakeTimeline.setCycleCount(3); // Repeat the shake twice for extra effect
        //shakeTimeline.setAutoReverse(false); // Do not auto-reverse
        shakeTimeline.setOnFinished(event -> setupFadeTransition());
        shakeTimeline.play();

    }
}