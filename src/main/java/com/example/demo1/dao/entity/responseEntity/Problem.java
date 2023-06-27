package com.example.demo1.dao.entity.responseEntity;

import com.example.demo1.dao.entity.AnswerEntity;
import com.example.demo1.dao.entity.OptionEntity;
import com.example.demo1.dao.entity.QuestionEntity;

import java.util.LinkedList;
import java.util.List;

public class Problem {
    private QuestionEntity questionEntity;
    private List<OptionEntity> options;
    private List<AnswerEntity> answers;

    public Problem() {
        questionEntity = new QuestionEntity();
        options = new LinkedList<>();
        answers = new LinkedList<>();
    }

    public QuestionEntity getQuestionEntity() {
        return questionEntity;
    }

    public void setQuestionEntity(QuestionEntity questionEntity) {
        this.questionEntity = questionEntity;
    }

    public List<OptionEntity> getOptions() {
        return options;
    }

    public void setOptions(List<OptionEntity> options) {
        this.options = options;
    }

    public List<AnswerEntity> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerEntity> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "questionEntity=" + questionEntity +
                ", options=" + options +
                ", answers=" + answers +
                '}';
    }
}
