package com.app.model;

import java.util.Set;

public class Test extends AbstractEntity {

    private Subject subject;
    private String name;
    private Integer timeLimit;
    private Set<Question> questionSet;
    private Set<PassedTest> passedTestSet;

    public Test() {
    }

    public Test(Long id, Subject subject, String name, Integer tileLimit) {
        super(id);
        this.name = name;
        this.subject = subject;
        this.timeLimit = tileLimit;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer tileLimit) {
        this.timeLimit = tileLimit;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Question> getQuestionSet() {
        return questionSet;
    }

    public void setQuestionSet(Set<Question> questionSet) {
        this.questionSet = questionSet;
    }

    public Set<PassedTest> getPassedTestSet() {
        return passedTestSet;
    }

    public void setPassedTestSet(Set<PassedTest> passedTestSet) {
        this.passedTestSet = passedTestSet;
    }
}
