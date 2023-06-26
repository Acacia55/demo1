package com.example.demo1.dao;

import com.example.demo1.dao.entity.ProjectEntity;
import com.example.demo1.dao.entity.QuestionnaireEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface QuestionnaireEntityMapper {

    int insert(QuestionnaireEntity questionnaireEntity);

    List<Map<String,Object>> queryQuestionnaireList(ProjectEntity projectEntity);
    List<Map<String,Object>> seeQuestionnaire(QuestionnaireEntity questionnaireEntity);

}
