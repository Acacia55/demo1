package com.example.demo1.service;

import com.example.demo1.dao.QuestionnaireEntityMapper;
import com.example.demo1.dao.entity.QuestionnaireEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionnaireService {

    @Autowired
    private QuestionnaireEntityMapper questionnaireEntityMapper;

    public int addQuestionnaire(QuestionnaireEntity questionnaireEntity){
        int addResult = questionnaireEntityMapper.insert(questionnaireEntity);
        if (addResult != 0){
            return 3;
//            3代表ok
        }
        return addResult;
    }

}
