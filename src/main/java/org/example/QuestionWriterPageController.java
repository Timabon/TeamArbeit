package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionWriterPageController {

    //path to "database" file
    private final String filePath = "src/main/resources/questions.json";

    @FXML
    private TextArea questionTextField;

    @FXML
    private TextField option1TextField;

    @FXML
    private TextField option2TextField;

    @FXML
    private TextField option3TextField;

    @FXML
    private TextField option4TextField;

    @FXML
    private RadioButton option1RadioButton;

    @FXML
    private RadioButton option2RadioButton;

    @FXML
    private RadioButton option3RadioButton;

    @FXML
    private RadioButton option4RadioButton;

    private ToggleGroup correctAnswerToggleGroup; // ToggleGroup for radio buttons

    private Stage currentStage;

    // Method to set the stage
    public void setStage(Stage stage) {
        this.currentStage = stage;
    }

    @FXML
    public void initialize() {
        // Initialize the ToggleGroup and associate it with the RadioButtons
        correctAnswerToggleGroup = new ToggleGroup();  // Initialize the ToggleGroup
        option1RadioButton.setToggleGroup(correctAnswerToggleGroup);
        option2RadioButton.setToggleGroup(correctAnswerToggleGroup);
        option3RadioButton.setToggleGroup(correctAnswerToggleGroup);
        option4RadioButton.setToggleGroup(correctAnswerToggleGroup);
    }

    public void saveQuestion(ActionEvent actionEvent) {
        String questionText = questionTextField.getText();
        String option1 = option1TextField.getText();
        String option2 = option2TextField.getText();
        String option3 = option3TextField.getText();
        String option4 = option4TextField.getText();

        // Check if any fields are empty
        if (questionText.isEmpty() || option1.isEmpty() || option2.isEmpty() || option3.isEmpty() || option4.isEmpty()) {
            System.out.println("All fields must be filled!");
            return;
        }

        // Question constructor requires a list of options
        List<String> options = new ArrayList<>();
        options.add(option1);
        options.add(option2);
        options.add(option3);
        options.add(option4);

        // Check if any radio button in the ToggleGroup is selected
        RadioButton selectedRadioButton = (RadioButton) correctAnswerToggleGroup.getSelectedToggle();
        if (selectedRadioButton == null) {
            System.out.println("Please select the correct answer!");
            return;
        }

        // Check if correct answer has been selected and set correctAnswerIndex
        Toggle selectedToggle = correctAnswerToggleGroup.getSelectedToggle();
        if (selectedToggle == null) {
            System.out.println("Bitte die richtige Antwort auswählen!");
            return;
        }

        int correctAnswerIndex=-1;
        if (selectedToggle == option1RadioButton) correctAnswerIndex = 0;
        if (selectedToggle == option2RadioButton) correctAnswerIndex = 1;
        if (selectedToggle == option3RadioButton) correctAnswerIndex = 2;
        if (selectedToggle == option4RadioButton) correctAnswerIndex = 3;

        // New Question Object based on input
        Question newQuestion = new Question(questionText, options, correctAnswerIndex);


        // Using try-catch to handle saving and error messaging
        try {
            // Read the existing questions from the file (if any)
            Gson gson = new Gson();
            List<Question> questionList = new ArrayList<>();

            // If the file already exists, load existing questions
            File file = new File(filePath);
            if (file.exists() && file.length() > 0) {
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                questionList = gson.fromJson(reader, new TypeToken<List<Question>>() {}.getType());
                reader.close();
            }

            // Add the new question to the list
            questionList.add(newQuestion);

            // Write the updated list back to the file
            FileWriter writer = new FileWriter(filePath);
            gson.toJson(questionList, writer);
            writer.close();

            System.out.println("Question saved successfully!");
            clearFields();
            returnToLandingPage(actionEvent);

        } catch (IOException e) {
            // Handle exceptions if saving the question fails
            e.printStackTrace();
            System.out.println("Failed to save question!");
        }
    }



    public void exitApplication(ActionEvent actionEvent) {
        clearFields();
        returnToLandingPage(actionEvent);
    }

    private void clearFields() {
        questionTextField.clear();
        option1TextField.clear();
        option2TextField.clear();
        option3TextField.clear();
        option4TextField.clear();
        correctAnswerToggleGroup.selectToggle(null); // Clear selection of the ToggleGroup
    }

    @FXML
    public void returnToLandingPage(ActionEvent actionEvent){
        try {
            // Load the Landing Page
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
