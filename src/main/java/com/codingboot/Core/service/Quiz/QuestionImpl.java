package com.codingboot.Core.service.Quiz;

import com.codingboot.Core.domain.entity.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class QuestionImpl implements QuestionService{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Question> getQuestions(Long id) {
        String sql="SELECT  distinct\n" +
                "t2.[id]  as \"id\",\n" +
                "t2.[questionname] as \"questionname\" ,\n" +
                "t2.[questionstatus] as \"questionstatus\",\n" +
                "t2.[questionlevel] as \"questionlevel\",\n" +
                "t2.[quizid]  as \"quizid\"\n" +
                "FROM [test_db].[dbo].[tbl_quizs] t1\n" +
                "left join [test_db].[dbo].[tbl_questions] t2 on t2.[quizid] = t1.[quizid]\n"+
                "where t1.[quizid]="+id;
        System.out.print("sql:"+sql);
        List<Question> question = new ArrayList<Question>();
        question = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Question.class));
        return question;
    }
}
