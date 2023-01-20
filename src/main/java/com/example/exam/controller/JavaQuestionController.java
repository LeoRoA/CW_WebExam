package com.example.exam.controller;

import com.example.exam.model.Question;
import com.example.exam.service.JavaQuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Java")
public class JavaQuestionController {

    private final JavaQuestionService javaQuestionService;

    public JavaQuestionController(JavaQuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @GetMapping("/add")
    public Question addQuestion (@RequestParam ("question") String question,
                                 @RequestParam ("answer") String answer){
        return javaQuestionService.add(question,answer);
    }

//    @GetMapping("/add")
//    public Question addQuestion (@RequestParam ("question")Question question){
//        return javaQuestionService.add(question);
//    }

    @GetMapping("/remove")
    public Question removeQuestion (@RequestParam ("question") String question,
                                    @RequestParam ("answer") String answer){
        Question q = new Question(question, answer);
        return javaQuestionService.remove(q);
    }
}
