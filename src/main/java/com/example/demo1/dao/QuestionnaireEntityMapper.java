package com.example.demo1.dao;

import com.example.demo1.dao.entity.QuestionnaireEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface QuestionnaireEntityMapper {

    int insert(QuestionnaireEntity questionnaireEntity);

}
