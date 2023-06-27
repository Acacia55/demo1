package com.example.demo1.dao;

import com.example.demo1.dao.entity.AnswerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface AnswerEntityMapper {
    AnswerEntity selectByPrimaryKey(String id);

    int insert(AnswerEntity answerEntity);
}
