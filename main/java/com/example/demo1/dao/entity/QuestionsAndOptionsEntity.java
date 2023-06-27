package com.example.demo1.dao.entity;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class QuestionsAndOptionsEntity implements Serializable {


    private List<QuestionEntity> questionEntityList;

    private List<OptionEntity> optionEntityList;

    public List<QuestionEntity> getQuestionEntityList() {
        return questionEntityList;
    }

    public void setQuestionEntityList(List<QuestionEntity> questionEntityList) {
        this.questionEntityList = questionEntityList;
    }

    public List<OptionEntity> getOptionEntityList() {
        return optionEntityList;
    }

    public void setOptionEntityList(List<OptionEntity> optionEntityList) {
        this.optionEntityList = optionEntityList;
    }

    @Override
    public String toString() {
        return "QuestionsAndOptionsEntity{" +
                "questionEntityList=" + questionEntityList +
                ", optionEntityList=" + optionEntityList +
                '}';
    }
}
