package com.codingboot.Core.controller.Quiz;

import com.codingboot.Core.domain.entity.Question;
import com.codingboot.Core.service.Quiz.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService  questionService;

    @GetMapping("/question/{id}")
    public ResponseEntity<List<Question>> getQuestions(@PathVariable Long id){
        return ResponseEntity.ok().body(questionService.getQuestions(id));
    }

}
