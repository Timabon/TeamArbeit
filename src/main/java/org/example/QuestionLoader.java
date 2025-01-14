package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionLoader {

    public List<Question> loadQuestions(String file) {
        List<Question> questionList = null;
        Gson gson = new Gson();

        // Try to load the file from the classpath
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("questions.json");
        if (inputStream == null) {
            System.out.println("File not found");
            return null;
        }

        // Read and deserialize the JSON from the file
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            questionList = gson.fromJson(reader, new TypeToken<List<Question>>(){}.getType());
        } catch (FileNotFoundException e) {
            System.out.println("Json Datei wurde nicht gefunden");
        } catch (IOException e) {
            System.out.println("Fehler beim Lesen der Json Datei");
            e.printStackTrace();
        }
        return questionList;
    }
}
