package com.codingboot.Core.service.Quiz;

import com.codingboot.Core.domain.entity.Options;
import com.codingboot.Core.domain.entity.Question;
import com.codingboot.Core.domain.entity.Quiz;
import com.codingboot.Core.domain.repository.OptionsRepository;
import com.codingboot.Core.domain.repository.QuestionRepository;
import com.codingboot.Core.domain.repository.QuizRepository;
import com.codingboot.Core.domain.response.QuizDTO;
import com.codingboot.Core.domain.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class QuizImpl implements QuizService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private OptionsRepository optionsRepository;


    @Override
    public List<Quiz> getQuizs() {
        return quizRepository.findAll();
    }

    @Override
    public List<Quiz> getQuizsDeltail(Long id) {
        //return quizRepository.findByQuizid(id);
        String sql = "SELECT  distinct\n" +
                "t2.[id]  as \"id\",\n" +
                "t2.[questionname] as \"questionname\" ,\n" +
                "t2.[questionstatus] as \"questionstatus\",\n" +
                "t2.[questionlevel] as \"questionlevel\",\n" +
                "t2.[quizid]  as \"quizid\"\n" +
                "FROM [test_db].[dbo].[tbl_quizs] t1\n" +
                "left join [test_db].[dbo].[tbl_questions] t2 on t2.[quizid] = t1.[quizid]\n" +
                "where t1.[quizid]=" + id;
        System.out.print("sql:" + sql);
        List<Quiz> quizs = new ArrayList<Quiz>();
        quizs = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Quiz.class));
        return quizs;
    }

    @Override
    public Response findAll() {
        Response responses = new Response();
        List<Response.Item> items = new ArrayList<>();
        List<QuizDTO> quizDTOS = quizRepository.findAllQuiz();

        for (int t = 0; t < quizDTOS.size(); t++) {
            QuizDTO i = quizDTOS.get(t);
            Response.Item item = new Response.Item();

            item.setId(i.getId());
            item.setName(i.getQuestionName());
            item.setQuestionTypeId(i.getQuestionTypeId());
            item.setQuestionId(i.getQuestionId());

            List<Response.OptionDTO> optionDTOS = quizDTOS.stream()
                    .filter(k -> k.getOptionQuestionId().equals(i.getQuestionId()))
                    .map(o -> {
                        Response.OptionDTO optionDTO = new Response.OptionDTO();
                        optionDTO.setId(o.getOptionId());
                        optionDTO.setName(o.getOptionQuestionName());
                        optionDTO.setIsAnswer(o.getIsAnswer());
                        optionDTO.setQuestionId(o.getQuestionId());
                        return optionDTO;
                    })
                    .collect(Collectors.toList());
            item.setOptions(optionDTOS.size() == 0 ? new ArrayList<>() : optionDTOS);

            if(items.stream().anyMatch(m -> m.getId().equals(item.getId()))) {
                continue;
            }else {
                items.add(item);
            }
        }

        responses.setTotal(items.size());
        responses.setItems(items);
        return responses;
    }
}
