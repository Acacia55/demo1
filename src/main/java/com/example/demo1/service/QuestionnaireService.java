package com.example.demo1.service;

import com.example.demo1.common.util.UUIDUtil;
import com.example.demo1.dao.QuestionnaireEntityMapper;
import com.example.demo1.dao.entity.OptionsAndAnswersEntity;
import com.example.demo1.dao.entity.ProjectEntity;
import com.example.demo1.dao.entity.QuestionnaireEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuestionnaireService {

    @Autowired
    private QuestionnaireEntityMapper questionnaireEntityMapper;

    public int addQuestionnaire(QuestionnaireEntity questionnaireEntity){

        questionnaireEntity.setId(UUIDUtil.getOneUUID());

        int addResult = questionnaireEntityMapper.insert(questionnaireEntity);
        if (addResult != 0){
            return 3;
//            3代表ok
        }
        return addResult;
    }

    public List<Map<String, Object>> queryQuestionnaireList(ProjectEntity projectEntity){

        return questionnaireEntityMapper.queryQuestionnaireList(projectEntity);
    }

    public OptionsAndAnswersEntity seeQuestionnaire(QuestionnaireEntity questionnaireEntity){

        return questionnaireEntityMapper.seeQuestionnaire(questionnaireEntity);
    }


}
