package com.example.exam.service;

import com.example.exam.model.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public interface ExamService {
    Collection<Question> getQuestions (int amount);
}
