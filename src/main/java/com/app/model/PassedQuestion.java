package com.app.model;

import java.io.Serializable;

public class PassedQuestion implements Serializable {

    private Long id;
    private Question question;
    private PassedTest passedTest;
    private String userAnswer;

    public PassedQuestion() {
    }

    public PassedQuestion(Long id, Question question, PassedTest passedTest, String userAnswer) {
        this.id = id;
        this.question = question;
        this.passedTest = passedTest;
        this.userAnswer = userAnswer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PassedTest getPassedTest() {
        return passedTest;
    }

    public void setPassedTest(PassedTest passedTest) {
        this.passedTest = passedTest;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PassedQuestion)) return false;

        PassedQuestion that = (PassedQuestion) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (question != null ? !question.equals(that.question) : that.question != null) return false;
        if (passedTest != null ? !passedTest.equals(that.passedTest) : that.passedTest != null) return false;
        return userAnswer != null ? userAnswer.equals(that.userAnswer) : that.userAnswer == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (passedTest != null ? passedTest.hashCode() : 0);
        result = 31 * result + (userAnswer != null ? userAnswer.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PassedQuestion{" +
                "id=" + id +
                ", question=" + question +
                ", passedTest=" + passedTest +
                ", userAnswer='" + userAnswer + '\'' +
                '}';
    }
}
