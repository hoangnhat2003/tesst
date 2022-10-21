package com.codingboot.Core.controller.Quiz;


import com.codingboot.Core.domain.entity.Quiz;
import com.codingboot.Core.service.Quiz.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok().body(quizService.findAll());
    }

    @GetMapping("/quizs")
    public ResponseEntity<List<Quiz>> getQuizs(){
        return ResponseEntity.ok().body(quizService.getQuizs());
    }

    @GetMapping("/quizs/{id}")
    public ResponseEntity<List<Quiz>> getQuizs(@PathVariable Long id){
        return ResponseEntity.ok().body(quizService.getQuizsDeltail(id));
    }

}
