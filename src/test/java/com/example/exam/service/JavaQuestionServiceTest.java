package com.example.exam.service;

import com.example.exam.model.Question;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class JavaQuestionServiceTest {
    private JavaQuestionService javaQuestionService;
    private List<Question> questions = List.of(
            new Question("q1", "a1"),
            new Question("q2", "a2"),
            new Question("q3", "a3"),
            new Question("q4", "a4"),
            new Question("q5", "a5")
    );


    @BeforeEach
    void setUp() {
        this.javaQuestionService = new JavaQuestionService();
        this.questions.forEach(this.javaQuestionService::add);

    }

    @Test
    void addQuestionTest() {
        Question newQ = new Question("q6", "a6");
        Question actual = this.javaQuestionService.add(newQ);
        assertThat(actual).isEqualTo(newQ);
        assertThat(this.javaQuestionService.getAllQuestions())
                .hasSize(6)
                .contains(newQ);
    }

    @Test
    void removeQuestionTest() {
        Question deletedQ = this.questions.get(3);
        Question actual = this.javaQuestionService.remove(deletedQ);
        assertThat(actual).isEqualTo(deletedQ);
        assertThat(this.javaQuestionService.getAllQuestions())
                .hasSize(4)
                .doesNotContain(deletedQ);
    }

    @Test
    void getRandomQuestionTest() {
        Question actual = this.javaQuestionService.getRandomQuestion();
        assertThat(actual).isIn(this.questions);
    }

    @Test
    void getAllQuestionTest() {
        assertThat(this.javaQuestionService.getAllQuestions())
                .hasSize(5)
                .containsAll(this.questions);
    }

    @Test
    void whenListIsEmptyThenGetRandomQuestionsReturnException() {
        questions.forEach(this.javaQuestionService::remove);
        Assertions.assertThrows(IllegalArgumentException.class, () -> javaQuestionService.getRandomQuestion());
    }

}
