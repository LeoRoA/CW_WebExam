package com.example.exam.service;

import com.example.exam.exceptions.UnSufficientQuestionsException;
import com.example.exam.model.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ExamServiceImpl implements ExamService{
    private final QuestionService questionService;

    public ExamServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }


    @Override

    public Collection<Question> getQuestions(int amount)  {
        Set<Question> examQuestions = new HashSet<>();
        if (questionService.getAllQuestions().size() < amount){
            throw new UnSufficientQuestionsException();
        }
        while(examQuestions.size() < amount) {
            examQuestions.add(questionService.getRandomQuestion());
        }
        return examQuestions;
    }
}
