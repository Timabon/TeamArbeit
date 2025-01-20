package org.example;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;

public class CostumeInterfaceController {

    private boolean isFileEmpty() {
        try (FileReader reader = new FileReader("src/main/resources/level_costume.json")) {
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

    public void handleCostumePlay(ActionEvent actionEvent) {
        // Check if the JSON file is empty or contains no valid questions
        if (isFileEmpty()) {
            System.out.println("The question file is empty. Cannot proceed to Play.");
            return; // Exit the method without proceeding
        }
        try {
            // Load the Landing Page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/level_costume.fxml"));
            Scene landingScene = new Scene(loader.load());

            // Get the current stage and set the new scene
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(landingScene);

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception
        }
    }

    public void handleCostumeNewQuestion(ActionEvent actionEvent) {
        try {
            // Load the Costume_New_Question_Page for adding new question(s)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/costume_new_question_page.fxml"));
            Scene landingScene = new Scene(loader.load());

            // Get the current stage and set the new scene
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(landingScene);

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception
        }
    }


    public void handleCostumeDelete(ActionEvent actionEvent) {
        try {
            // Load the Costume_Question_List for deleting questions(s)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/costume_question_list.fxml"));
            Scene landingScene = new Scene(loader.load());

            // Get the current stage and set the new scene
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(landingScene);

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception
        }
    }


    public void handleCostumeExit(ActionEvent actionEvent) {
        try {
            // Load the Landing Page to leave costume mode
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



