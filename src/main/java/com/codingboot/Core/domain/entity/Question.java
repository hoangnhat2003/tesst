package com.codingboot.Core.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long quizId;
    private String questionName;
    private Long questionTypeId;
    private Integer questionStatus;
    private Integer questionLevel;
    private Long creatorId;
    private Date createdTime;
    private Long modifyBy;
    private Date modifyDate;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "questions")
    private Set<Quiz> quizs = new HashSet<>();
}
