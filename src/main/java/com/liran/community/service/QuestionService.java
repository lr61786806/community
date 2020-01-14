package com.liran.community.service;

import com.liran.community.Mapper.QuestionMapper;
import com.liran.community.Mapper.UserMapper;
import com.liran.community.dto.PaginationDTO;
import com.liran.community.dto.QuestionDTO;
import com.liran.community.model.Question;
import com.liran.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page,Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();

        paginationDTO.setPage(page);
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount,page,size);

        if(page<1){
            page=1;
        }
        if(page>paginationDTO.getTotalPage()){
            page=paginationDTO.getTotalPage();
        }

        Integer i = size*(page-1);
        List<Question> questions = questionMapper.list(i,size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for(Question question:questions){
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestionDTOS(questionDTOS);
        return paginationDTO;
    }

    public PaginationDTO listUserById(Integer userId, Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();

        paginationDTO.setPage(page);
        Integer totalCount = questionMapper.countByUserId(userId);
        paginationDTO.setPagination(totalCount,page,size);
        if(page<1){
            page=1;
        }
        if(page>paginationDTO.getTotalPage()){
            page=paginationDTO.getTotalPage();
        }

        Integer i = size*(page-1);
        List<Question> questions = questionMapper.listUserById(userId,i,size);

        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for(Question question:questions){
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestionDTOS(questionDTOS);
        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.getById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }
}
