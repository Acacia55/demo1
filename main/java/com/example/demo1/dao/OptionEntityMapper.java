package com.example.demo1.dao;

import com.example.demo1.dao.entity.OptionEntity;
import com.example.demo1.dao.entity.QuestionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface OptionEntityMapper {

    int insert(OptionEntity optionEntity);

    List<OptionEntity> getOptionByQuestionId(String questionId);
}
