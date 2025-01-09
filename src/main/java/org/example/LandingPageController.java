package org.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionEvent;
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
}
