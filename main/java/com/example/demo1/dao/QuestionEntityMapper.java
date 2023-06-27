package com.example.demo1.dao;

import com.example.demo1.dao.entity.QuestionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface QuestionEntityMapper {

    int insert(QuestionEntity questionEntity);

    List<QuestionEntity> selectByQuestionnaireId(String questionnaireId);
}
