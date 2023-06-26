package com.example.demo1.service;

import com.example.demo1.dao.OptionEntityMapper;
import com.example.demo1.dao.ProjectEntityMapper;
import com.example.demo1.dao.entity.OptionEntity;
import com.example.demo1.dao.entity.QuestionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OptionService {

    @Autowired
    private OptionEntityMapper optionEntityMapper;

    public int addOptions(OptionEntity optionEntity){
        int addResult = optionEntityMapper.insert(optionEntity);
        if (addResult != 0){
            return 3;
//            3代表ok
        }
        return addResult;
    }

}
