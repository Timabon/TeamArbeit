package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameApp extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        //splash screen
        FXMLLoader splashLoader = new FXMLLoader(getClass().getResource("/splash_screen.fxml"));
        Scene splashScene = new Scene(splashLoader.load());
        primaryStage.setScene(splashScene);
        primaryStage.setTitle("Splash Screen");
        primaryStage.show();

        // Get the SplashScreenController and pass the primaryStage
        SplashScreenController splashController = splashLoader.getController();
        splashController.setPrimaryStage(primaryStage);

        //Switch to landing page
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/landing_page.fxml"));
                Scene landingScene = new Scene(loader.load());
                javafx.application.Platform.runLater(() -> {
                    //primaryStage.setTitle("Who Wants to Be a Millionaire?");
                    primaryStage.setScene(landingScene);
                    // primaryStage.show();
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }
}