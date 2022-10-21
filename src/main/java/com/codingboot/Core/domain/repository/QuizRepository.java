package com.codingboot.Core.domain.repository;

import com.codingboot.Core.domain.entity.Quiz;
import com.codingboot.Core.domain.response.QuizDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Long> {

     @Query("SELECT q.id as id, qt.id as questionId, qt.questionName as questionName, " +
             "qt.questionTypeId as questionTypeId, op.id as optionId, op.questionId as optionQuestionId, " +
             "op.name as optionQuestionName, op.isAnswer as isAnswer " +
             "FROM Quiz q JOIN Question qt ON q.id = qt.quizId JOIN Options op ON qt.id = op.questionId")
     List<QuizDTO> findAllQuiz();
}
