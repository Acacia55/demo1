package com.example.demo1.dao.entity;

import java.io.Serializable;
import java.util.Date;

public class QuestionnaireEntity implements Serializable {

    private String id;

    private String projectId;

//    老师还是学生？
    private String answertype;

/*默认 123对应前端的 三种模板类型
    private int template;*/

    private String questionnaireName;

//    问卷说明
    private String content;

    private Date startTime;

    private Date endTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getAnswertype() {
        return answertype;
    }

    public void setAnswertype(String answertype) {
        this.answertype = answertype;
    }

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "QuestionnaireEntity{" +
                "id='" + id + '\'' +
                ", projectid='" + projectId + '\'' +
                ", answertype='" + answertype + '\'' +
                ", questionnaireName='" + questionnaireName + '\'' +
                ", content='" + content + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
