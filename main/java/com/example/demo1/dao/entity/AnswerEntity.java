package com.example.demo1.dao.entity;

import java.io.Serializable;
import java.util.Date;

public class AnswerEntity implements Serializable {

    private String id;
    private String userid;
    private String optionid;
    private String username;
    private Date answertime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getOptionid() {
        return optionid;
    }

    public void setOptionid(String optionid) {
        this.optionid = optionid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getAnswertime() {
        return answertime;
    }

    public void setAnswertime(Date answertime) {
        this.answertime = answertime;
    }

    @Override
    public String toString() {
        return "AnswerEntity{" +
                "id='" + id + '\'' +
                ", userid='" + userid + '\'' +
                ", optionid='" + optionid + '\'' +
                ", username='" + username + '\'' +
                ", answertime=" + answertime +
                '}';
    }
}
