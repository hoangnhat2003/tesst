package com.codingboot.Core.domain.repository;

import com.codingboot.Core.domain.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {

}
