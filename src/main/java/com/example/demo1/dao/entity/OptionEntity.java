package com.example.demo1.dao.entity;

import java.io.Serializable;

public class OptionEntity implements Serializable {

    private String id;

    private String questionid;

    private String optionContent;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid;
    }

    public String getOptionContent() {
        return optionContent;
    }

    public void setOptionContent(String optionContent) {
        this.optionContent = optionContent;
    }

    @Override
    public String toString() {
        return "OptionEntity{" +
                "id='" + id + '\'' +
                ", questionid='" + questionid + '\'' +
                ", optionContent='" + optionContent + '\'' +
                '}';
    }
}
