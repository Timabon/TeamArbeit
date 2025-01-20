package org.example;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;

public class CustomInterfaceController {

    @FXML
    private Button customPlayButton;
    @FXML
    private Button customDeleteButton;
    @FXML
    private Button customNewQuestionButton;
    @FXML
    private Button customExitButton;

    public void initialize() {

        //Add hover effect
        addHoverEffect(customPlayButton);
        addHoverEffect(customDeleteButton);
        addHoverEffect(customNewQuestionButton);
        addHoverEffect(customExitButton);

        customPlayButton.setStyle("-fx-font-size: 12px; -fx-text-fill: white; -fx-background-color: blue; -fx-background-radius: 15px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 8, 0.5, 0, 2);");
        customDeleteButton.setStyle("-fx-font-size: 12px; -fx-text-fill: white; -fx-background-color: blue; -fx-background-radius: 15px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 8, 0.5, 0, 2);");
        customNewQuestionButton.setStyle("-fx-font-size: 12px; -fx-text-fill: white; -fx-background-color: blue; -fx-background-radius: 15px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 8, 0.5, 0, 2);");
        customExitButton.setStyle("-fx-font-size: 12px; -fx-text-fill: white; -fx-background-color: blue; -fx-background-radius: 15px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 8, 0.5, 0, 2);");
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

    private boolean isFileEmpty() {
        try (FileReader reader = new FileReader("src/main/resources/level_custom.json")) {
            Gson gson = new Gson();
            Question[] questions = gson.fromJson(reader, Question[].class);

            // Check if the questions array is null or empty
            return questions == null || questions.length == 0;
        } catch (IOException e) {
            e.printStackTrace();
            return true; // Treat as empty if there's an error reading the file
        }
    }

    private Stage currentStage;

    public void handleCustomPlay(ActionEvent actionEvent) {
        // Check if the JSON file is empty or contains no valid questions
        if (isFileEmpty()) {
            System.out.println("The question file is empty. Cannot proceed to Play.");
            return; // Exit the method without proceeding
        }
        try {
            // Load the Landing Page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/level_custom.fxml"));
            Scene landingScene = new Scene(loader.load());

            // Get the current stage and set the new scene
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(landingScene);

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception
        }
    }

    public void handleCustomNewQuestion(ActionEvent actionEvent) {
        try {
            // Load the Custom_New_Question_Page for adding new question(s)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/custom_new_question_page.fxml"));
            Scene landingScene = new Scene(loader.load());

            // Get the current stage and set the new scene
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(landingScene);

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception
        }
    }

    public void handleCustomDelete(ActionEvent actionEvent) {
        try {
            // Load the Custom_Question_List for deleting questions(s)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/custom_question_list.fxml"));
            Scene landingScene = new Scene(loader.load());

            // Get the current stage and set the new scene
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(landingScene);

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception
        }
    }

    public void handleCustomExit(ActionEvent actionEvent) {
        try {
            // Load the Landing Page to leave custom mode
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/landing_page.fxml"));
            Scene landingScene = new Scene(loader.load());

            // Get the current stage and set the new scene
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(landingScene);

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception
        }
    }
}



