package com.app.dto;

import com.app.model.PassedQuestion;
import com.app.model.Test;

import java.util.Set;

public class PassedTestDTO {
    private Long id;
    private UserDTO user;
    private Test test;
    private Integer mark;
    private Set<PassedQuestion> passedQuestionSet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Set<PassedQuestion> getPassedQuestionSet() {
        return passedQuestionSet;
    }

    public void setPassedQuestionSet(Set<PassedQuestion> passedQuestionSet) {
        this.passedQuestionSet = passedQuestionSet;
    }
}
