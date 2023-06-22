package com.example.demo1.dao.entity;

import java.io.Serializable;

public class QuestionEntity implements Serializable {

    private String questionId;

    private String questionnaireId;
//题型编号
    private String questionType;
//    题型名称
    private String questionTypeName;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(String questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestionTypeName() {
        return questionTypeName;
    }

    public void setQuestionTypeName(String questionTypeName) {
        this.questionTypeName = questionTypeName;
    }

    @Override
    public String toString() {
        return "QuestionEntity{" +
                "questionId='" + questionId + '\'' +
                ", questionnaireId='" + questionnaireId + '\'' +
                ", questionType='" + questionType + '\'' +
                ", questionTypeName='" + questionTypeName + '\'' +
                '}';
    }
}
