package com.app.model;

public class PassedQuestion extends AbstractEntity {

    private Question question;
    private Integer userAnswer;

    public PassedQuestion() {
    }

    public PassedQuestion(Long id, Question question, Integer userAnswer) {
        super(id);
        this.question = question;
        this.userAnswer = userAnswer;
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
