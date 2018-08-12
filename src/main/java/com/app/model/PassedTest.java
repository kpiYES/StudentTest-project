package com.app.model;

import java.util.Set;

public class PassedTest extends AbstractEntity {


    private User user;
    private Test test;
    private Integer mark;
    private Set<PassedQuestion> passedQuestionSet;

    public PassedTest() {
    }

    public PassedTest(Long id, User user, Test test, Integer mark, Set<PassedQuestion> passedQuestionSet) {
        super(id);
        this.user = user;
        this.test = test;
        this.mark = mark;
        this.passedQuestionSet = passedQuestionSet;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Set<PassedQuestion> getPassedQuestionSet() {
        return passedQuestionSet;
    }

    public void setPassedQuestionSet(Set<PassedQuestion> passedQuestionSet) {
        this.passedQuestionSet = passedQuestionSet;
    }
}
