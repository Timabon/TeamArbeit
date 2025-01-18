package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;


public class LandingPageController {

@FXML
    public void handleStartGame(javafx.event.ActionEvent actionEvent) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/question_page.fxml"));
            Scene qestionScene = new Scene(loader.load());
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(qestionScene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void handlenewQuestionButton(ActionEvent actionEvent) {
        try {
            // Load the Question Page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/question_writer_page.fxml"));
            Scene questionWriterScene = new Scene(loader.load());

            // Get the current stage and set the new scene
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(questionWriterScene);

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception
        }
    }
}
