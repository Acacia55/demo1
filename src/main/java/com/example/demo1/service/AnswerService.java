package com.example.demo1.service;

import com.example.demo1.common.util.UUIDUtil;
import com.example.demo1.dao.AnswerEntityMapper;
import com.example.demo1.dao.entity.AnswerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {
    @Autowired
    private AnswerEntityMapper answerEntityMapper;

    public AnswerEntity selectByPrimaryKey(AnswerEntity answerEntity) {
        return answerEntityMapper.selectByPrimaryKey(answerEntity);
    }

    public int insert(AnswerEntity answerEntity){

        answerEntity.setId(UUIDUtil.getOneUUID());

        int result=answerEntityMapper.insert(answerEntity);

        if (result != 0){
//            3代表ok
            return 3;
        }
        return result;
    }
}
