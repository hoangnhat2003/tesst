package com.codingboot.Core.domain.response;

public interface QuizDTO {
    Long getId();
    Long getQuestionId();
    String getQuestionName();
    Long getQuestionTypeId();
    Long getOptionId();
    Long getOptionQuestionId();
    String getOptionQuestionName();
    Boolean getIsAnswer();
}
