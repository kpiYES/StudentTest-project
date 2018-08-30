package com.app.model;

public class PassedQuestion extends AbstractEntity {

    private Question question;
    private PassedTest passedTest;
    private String userAnswer;

    public PassedQuestion() {
    }

    public PassedTest getPassedTest() {
        return passedTest;
    }

    public void setPassedTest(PassedTest passedTest) {
        this.passedTest = passedTest;
    }

    public PassedQuestion(Long id, Question question, PassedTest passedTest, String userAnswer) {
        super(id);
        this.question = question;
        this.passedTest = passedTest;

        this.userAnswer = userAnswer;
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
}
