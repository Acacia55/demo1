package com.example.demo1.dao;

import com.example.demo1.dao.entity.QuestionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface QuestionEntityMapper {

    int insert(QuestionEntity questionEntity);

}