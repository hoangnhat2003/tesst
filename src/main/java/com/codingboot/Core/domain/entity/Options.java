package com.codingboot.Core.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="tbl_options")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Options {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long questionId;
    private String name;
    private Boolean isAnswer;
    private Integer quizId;
    private Long creatorId;
    private Date createdTime;
    private Long modifyBy;
    private Date modifyDate;
}
