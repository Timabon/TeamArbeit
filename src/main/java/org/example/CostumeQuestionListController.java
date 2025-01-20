package org.example;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CostumeQuestionListController {

    @FXML
    private VBox questionContainer;
    @FXML
    private Button deleteSelectedButton;
    @FXML
    private Button deleteAllButton;
    @FXML
    private Button exitButton;

    @FXML
    private ListView<Question> questionListView; // ListView to show questions

    private ObservableList<Question> questions = FXCollections.observableArrayList(); // ObservableList for dynamic updates


    private static final String FILE_PATH = "src/main/resources/level_costume.json";


    @FXML
    public void initialize() {
        loadQuestions();  // Load questions from the JSON file
        setupListView();  // Setup ListView to display questions

        // Allow multiple selections
        questionListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    // Method to display the list of questions in the ListView
    private void setupListView() {
        questionListView.setItems(questions);  // Bind ListView to ObservableList
        questionListView.setCellFactory(lv -> new ListCell<Question>() {
            @Override
            protected void updateItem(Question question, boolean empty) {
                super.updateItem(question, empty);
                if (empty || question == null) {
                    setText(null);
                } else {
                    setText(question.getQuestionText());  // Display only the question text in the ListView
                }
            }
        });
    }

    public void loadQuestions() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Gson gson = new Gson();
            // Load all questions into the list
            Question[] loadedQuestions = gson.fromJson(reader, Question[].class);
            questions.clear();
            for (Question question : loadedQuestions) {
                questions.add(question);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading questions from file.");
        }
    }

    private void updateQuestionsFile() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            Gson gson = new Gson();
            gson.toJson(questions, writer);  // Write the updated list of questions back to the file
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to update questions!");
        }
    }


    
    public void handleDeleteAllButton(ActionEvent actionEvent) {
        questions.clear();
        updateQuestionsFile();
    }

    public void handleDeleteSelectedButton(ActionEvent actionEvent) {
        // Get the selected questions
        ObservableList<Question> selectedQuestions = questionListView.getSelectionModel().getSelectedItems();

        // Check if there are any selected questions
        if (!selectedQuestions.isEmpty()) {
            // Remove all selected questions from the list
            questions.removeAll(selectedQuestions);

            // Update the JSON file
            updateQuestionsFile();

            System.out.println("Deleted selected question(s).");

        }
    }

    public void handleExitButton(ActionEvent actionEvent) {
        try {
            // Load the Landing Page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/costume_interface.fxml"));
            Scene landingScene = new Scene(loader.load());

            // Get the current stage and set the new scene
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(landingScene);

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception
        }
    }
}
