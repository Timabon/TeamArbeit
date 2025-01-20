package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CostumeInterfaceController {

    private Stage currentStage;

    public void handleCostumePlay(ActionEvent actionEvent) {
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
            // Load the Landing Page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/costume_new_question_page.fxml"));
            Scene landingScene = new Scene(loader.load());

            // Get the current stage and set the new scene
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(landingScene);

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception
        }
    }


    public void handleCostumeDelete(ActionEvent actionEvent) {try {
        // Load the Landing Page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/costume_question_list.fxml"));
        Scene landingScene = new Scene(loader.load());

        // Get the current stage and set the new scene
        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(landingScene);

    } catch (IOException e) {
        e.printStackTrace(); // Handle the exception
    }
    }


}



