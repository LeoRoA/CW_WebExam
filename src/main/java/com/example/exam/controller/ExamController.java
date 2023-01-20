package com.example.exam.controller;

import com.example.exam.exceptions.UnSufficientQuestionsException;
import com.example.exam.model.Question;
import com.example.exam.service.ExamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/get")
public class ExamController {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @ExceptionHandler(UnSufficientQuestionsException.class)
    public ResponseEntity<String> handleException(){
        return ResponseEntity
                .badRequest()
                .body("Недостаточно вопросов для формирования билета");
    }

    @GetMapping("/{amount}")
    public Collection<Question> getQuestions (@PathVariable("amount") int amount){
        return examService.getQuestions(amount);
    }

}
