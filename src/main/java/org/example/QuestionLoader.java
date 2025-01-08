package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionLoader {

    public List<Question> loadQuestions(String file) {
        List<Question> questionList = new ArrayList<>();
        String delimiter = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            br.readLine();
            // Read the file line by line
            while ((line = br.readLine()) != null) {
                String[] questions = line.split(delimiter);

                if (questions.length == 6) {
                    String questionText = questions[0];
                    List<String> options = List.of(questions[1], questions[2], questions[3], questions[4]);
                    int correctAnswerIndex = Integer.parseInt(questions[5]);
                    questionList.add(new Question(questionText, options, correctAnswerIndex));
                } else {
                    System.out.println("Skipping malformed line: " + line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return questionList;
    }
}
