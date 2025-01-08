import org.example.Question;
import org.example.QuestionLoader;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionLoaderTest {

    @Test
    void testLoadQuestions(){
        QuestionLoader questionLoader = new QuestionLoader();
        List<Question> questionList = questionLoader.loadQuestions("src/main/resources/questions25TeamArbeit.csv");

        assertFalse(questionList.isEmpty(), "The list of questions shouldn't be empty");

        Question firstQuestion = questionList.get(0);
        assertEquals("What is the capital of France?", firstQuestion.getQuestionText());
        assertEquals(List.of("Paris", "Madrid", "Berlin", "Rome"), firstQuestion.getOptions());
        assertEquals(0, firstQuestion.getCorrectAnswerIndex());

        assertEquals(25, questionList.size(), "The are should be 25 questions.");
    }
}
