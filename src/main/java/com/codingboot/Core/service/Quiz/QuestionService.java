package com.codingboot.Core.service.Quiz;

import com.codingboot.Core.domain.entity.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {

    List<Question> getQuestions(Long id);

}
