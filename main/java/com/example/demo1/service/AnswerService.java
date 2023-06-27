package com.example.demo1.service;

import com.example.demo1.dao.AnswerEntityMapper;
import com.example.demo1.dao.entity.AnswerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {
    @Autowired
    private AnswerEntityMapper answerEntityMapper;

    public AnswerEntity selectByPrimaryKey(String id) {
        return answerEntityMapper.selectByPrimaryKey(id);
    }

    public int insert(AnswerEntity answerEntity){
        int result=answerEntityMapper.insert(answerEntity);

        if (result != 0){
//            3代表ok
            return 3;
        }
        return result;
    }

}
