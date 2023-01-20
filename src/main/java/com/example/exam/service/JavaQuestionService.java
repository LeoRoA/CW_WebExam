package com.example.exam.service;

import com.example.exam.exceptions.QAException;
import com.example.exam.model.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class JavaQuestionService implements QuestionService {
    private final Set<Question> questions = new HashSet<>();

    @Override
    public Question add(String question, String answer) {
        Question q = (new Question(question,answer));
        questions.add(q);
        return q;
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAllQuestions() {
        return Collections.unmodifiableCollection(questions);
    }

    @Override
    public Question getRandomQuestion() {
        long random = ThreadLocalRandom.current().nextLong(0, questions.size());
        return questions.stream()
                .skip(random)
                .findFirst()
                .orElseThrow(QAException::new);
    }
}
