package com.example.demo1.dao.entity;

import java.io.Serializable;
import java.util.List;

public class OptionsAndAnswersEntity implements Serializable {

    private String questionnaireName;

    private String content;

    private List<QuestionEntity> questionEntityList;

    private List<OptionEntity> optionEntityList;

    private List<AnswerEntity> answerEntityList;


    public String getQuestionnaireName() {
        return questionnaireName;
    }

    public void setQuestionnaireName(String questionnaireName) {
        this.questionnaireName = questionnaireName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<OptionEntity> getOptionEntityList() {
        return optionEntityList;
    }

    public void setOptionEntityList(List<OptionEntity> optionEntityList) {
        this.optionEntityList = optionEntityList;
    }

    public List<AnswerEntity> getAnswerEntityList() {
        return answerEntityList;
    }

    public void setAnswerEntityList(List<AnswerEntity> answerEntityList) {
        this.answerEntityList = answerEntityList;
    }

    @Override
    public String toString() {
        return "OptionsAndAnswersEntity{" +
                "questionnaireName='" + questionnaireName + '\'' +
                ", content='" + content + '\'' +
                ", optionEntityList=" + optionEntityList +
                ", answerEntityList=" + answerEntityList +
                '}';
    }
}
