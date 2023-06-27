package com.example.demo1.service;

import com.example.demo1.common.util.UUIDUtil;
import com.example.demo1.dao.QuestionEntityMapper;
import com.example.demo1.dao.entity.QuestionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionEntityMapper questionEntityMapper;

    public int addQuestion(QuestionEntity questionEntity){

        questionEntity.setQuestionId(UUIDUtil.getOneUUID());

        int addResult = questionEntityMapper.insert(questionEntity);
        if (addResult != 0){
            return 3;
//            3代表ok
        }
        return addResult;
    }

    public List<QuestionEntity> getQuestionByQuestionnaireId(String questionnaireId){
        return questionEntityMapper.selectByQuestionnaireId(questionnaireId);
    }

}
