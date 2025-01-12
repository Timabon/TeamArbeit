package org.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class GameOverPageController {
    @FXML
    private Button restartButton;

    @FXML
    private Button exitButton;

    @FXML
    private Label gameOverLabel;

    @FXML
    private void handleRestart(){
        Stage stage = (Stage) restartButton.getScene().getWindow();
        SceneSwitcher.switchScene("/question_page.fxml", stage);
    }

    @FXML
    private void handleExit(){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}