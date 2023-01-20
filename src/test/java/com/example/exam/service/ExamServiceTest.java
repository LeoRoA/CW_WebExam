package com.example.exam.service;

import com.example.exam.exceptions.UnSufficientQuestionsException;
import com.example.exam.model.Question;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExamServiceTest {
    @Mock
    QuestionService questionService;

    @InjectMocks
    ExamServiceImpl examService;

    @Test
    void whenAmountOfQuestionsIsSufficientThenReturnsExactAmountQuestions(){
        List<Question> questionList = List.of(
                new Question("q1", "a1"),
                new Question("q2", "a2"),
                new Question("q3", "a3"),
                new Question("q4", "a4"),
                new Question("q5", "a5")
        );
        when(questionService.getAllQuestions()).thenReturn(questionList
        );
        when(questionService.getRandomQuestion()).thenReturn(questionList.get(0),questionList.get(2));
        Assertions.assertThat(examService.getQuestions(2))
                .containsOnly(questionList.get(0),questionList.get(2));
    }

    @Test
    void whenAmountOfQuestionsIsUnSufficientThenReturnsException(){
        when(questionService.getAllQuestions()).thenReturn(Collections.emptyList());
        Assertions.assertThatThrownBy(()->examService.getQuestions(10))
                .isInstanceOf(UnSufficientQuestionsException.class);
    }
}
