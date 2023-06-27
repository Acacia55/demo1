package com.example.demo1.dao.entity.responseEntity;

import com.example.demo1.dao.entity.QuestionnaireEntity;

import java.util.LinkedList;
import java.util.List;

public class Questionnaire {
    private QuestionnaireEntity questionnaireEntity;
    private List<Problem> problems;

    public Questionnaire() {
        questionnaireEntity = new QuestionnaireEntity();
        problems = new LinkedList<>();
    }

    public QuestionnaireEntity getQuestionnaireEntity() {
        return questionnaireEntity;
    }

    public void setQuestionnaireEntity(QuestionnaireEntity questionnaireEntity) {
        this.questionnaireEntity = questionnaireEntity;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }

    @Override
    public String toString() {
        return "Questionnaire{" +
                "questionnaireEntity=" + questionnaireEntity +
                ", problems=" + problems +
                '}';
    }
}
