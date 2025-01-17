package org.example;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class SceneSwitcher {

    public static <T> T switchScene(String fxmlPath, Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneSwitcher.class.getResource(fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            // Return the controller
            return loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Handle error cases
        }
    }

}
