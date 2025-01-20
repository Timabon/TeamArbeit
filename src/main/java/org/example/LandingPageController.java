package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class LandingPageController {
    @FXML
    public Button level1;
    @FXML
    public Button level2;
    @FXML
    public Button level3;
    @FXML
    public Button custom;

    public void initialize() {
        // Apply hover effects to buttons
        addHoverEffect(level1);
        addHoverEffect(level2);
        addHoverEffect(level3);
        addHoverEffect(custom);

        // Set the default style for the buttons
        level1.setStyle("-fx-font-size: 12px; -fx-text-fill: white; -fx-background-color: blue; -fx-background-radius: 15px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 8, 0.5, 0, 2);");
        level2.setStyle("-fx-font-size: 12px; -fx-text-fill: white; -fx-background-color: blue; -fx-background-radius: 15px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 8, 0.5, 0, 2);");
        level3.setStyle("-fx-font-size: 12px; -fx-text-fill: white; -fx-background-color: blue; -fx-background-radius: 15px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 8, 0.5, 0, 2);");
        custom.setStyle("-fx-font-size: 12px; -fx-text-fill: white; -fx-background-color: blue; -fx-background-radius: 15px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 8, 0.5, 0, 2);");

    }
        private void addHoverEffect (Button button){
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

    @FXML
    public void handleLevel1(javafx.event.ActionEvent actionEvent) {
        level1.setDisable(true); // Disable the button
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/level1.fxml"));
            Scene questionScene = new Scene(loader.load());
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(questionScene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void handleLevel2(ActionEvent actionEvent) {
        level2.setDisable(true); // Disable the button
        try {
            // Load the Question Page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/level2.fxml"));
            Scene questionWriterScene = new Scene(loader.load());

            // Get the current stage and set the new scene
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(questionWriterScene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void handleLevel3(ActionEvent actionEvent) {
        level3.setDisable(true); // Disable the button
        try {
            // Load the Question Page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/level3.fxml"));
            Scene questionWriterScene = new Scene(loader.load());

            // Get the current stage and set the new scene
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(questionWriterScene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void handleCustom(ActionEvent actionEvent) {
        custom.setDisable(true); // Disable the button
        try {
            // Load the Question Page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/costume_interface.fxml"));
            Scene questionWriterScene = new Scene(loader.load());

            // Get the current stage and set the new scene
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(questionWriterScene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

