package com.app.model;

public class PassedQuestion extends AbstractEntity {

    private PassedTest passedTest;
    private Question question;
    private Integer userAnswer;

    public PassedQuestion(){
    }

    public PassedQuestion(Long id, PassedTest passedTest, Question question, Integer userAnswer) {
        super(id);
        this.passedTest = passedTest;
        this.question = question;
        this.userAnswer = userAnswer;
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

    public Integer getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(Integer userAnswer) {
        this.userAnswer = userAnswer;
    }
}
