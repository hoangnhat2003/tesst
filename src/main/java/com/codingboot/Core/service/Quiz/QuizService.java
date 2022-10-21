package com.codingboot.Core.service.Quiz;

import com.codingboot.Core.domain.entity.Quiz;
import com.codingboot.Core.domain.response.QuizDTO;
import com.codingboot.Core.domain.response.Response;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface QuizService {

    List<Quiz> getQuizs();

    List<Quiz> getQuizsDeltail(Long  id);

    Response findAll();
}
