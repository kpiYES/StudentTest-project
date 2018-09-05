package com.app.model;

import java.io.Serializable;
import java.util.Set;

public class Question implements Serializable {

    private Long id;
    private Subject subject;
    private String query;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String correctAnswer;
    private Set<Test> testSet;

    public Question() {
    }


    public Question(Long id, Subject subject, String query, String answer1, String answer2, String answer3, String answer4, String correctAnswer) {
        this.id = id;
        this.subject = subject;
        this.query = query;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correctAnswer = correctAnswer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Set<Test> getTestSet() {
        return testSet;
    }

    public void setTestSet(Set<Test> testSet) {
        this.testSet = testSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;

        Question question = (Question) o;

        if (id != null ? !id.equals(question.id) : question.id != null) return false;
        if (subject != null ? !subject.equals(question.subject) : question.subject != null) return false;
        if (query != null ? !query.equals(question.query) : question.query != null) return false;
        if (answer1 != null ? !answer1.equals(question.answer1) : question.answer1 != null) return false;
        if (answer2 != null ? !answer2.equals(question.answer2) : question.answer2 != null) return false;
        if (answer3 != null ? !answer3.equals(question.answer3) : question.answer3 != null) return false;
        if (answer4 != null ? !answer4.equals(question.answer4) : question.answer4 != null) return false;
        return correctAnswer != null ? correctAnswer.equals(question.correctAnswer) : question.correctAnswer == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (query != null ? query.hashCode() : 0);
        result = 31 * result + (answer1 != null ? answer1.hashCode() : 0);
        result = 31 * result + (answer2 != null ? answer2.hashCode() : 0);
        result = 31 * result + (answer3 != null ? answer3.hashCode() : 0);
        result = 31 * result + (answer4 != null ? answer4.hashCode() : 0);
        result = 31 * result + (correctAnswer != null ? correctAnswer.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", subject=" + subject +
                ", query='" + query + '\'' +
                ", answer1='" + answer1 + '\'' +
                ", answer2='" + answer2 + '\'' +
                ", answer3='" + answer3 + '\'' +
                ", answer4='" + answer4 + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                '}';
    }
}
