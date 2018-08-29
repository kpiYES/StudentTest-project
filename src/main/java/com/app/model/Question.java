package com.app.model;

import java.util.Set;

public class Question extends AbstractEntity {

    private Subject subject;
    private String query;
    private String answer1;

    @Override
    public String toString() {
        return "Question{" +
                "subject=" + subject +
                ", query='" + query + '\'' +
                ", answer1='" + answer1 + '\'' +
                ", answer2='" + answer2 + '\'' +
                ", answer3='" + answer3 + '\'' +
                ", answer4='" + answer4 + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", testSet=" + testSet +
                '}';
    }

    private String answer2;
    private String answer3;
    private String answer4;
    private String correctAnswer;
    private Set<Test> testSet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;

        Question question = (Question) o;

        if (!subject.equals(question.subject)) return false;
        if (!query.equals(question.query)) return false;
        if (!answer1.equals(question.answer1)) return false;
        if (!answer2.equals(question.answer2)) return false;
        if (answer3 != null ? !answer3.equals(question.answer3) : question.answer3 != null) return false;
        if (answer4 != null ? !answer4.equals(question.answer4) : question.answer4 != null) return false;
        return correctAnswer.equals(question.correctAnswer);
    }

    @Override
    public int hashCode() {
        int result = subject.hashCode();
        result = 31 * result + query.hashCode();
        result = 31 * result + answer1.hashCode();
        result = 31 * result + answer2.hashCode();
        result = 31 * result + (answer3 != null ? answer3.hashCode() : 0);
        result = 31 * result + (answer4 != null ? answer4.hashCode() : 0);
        result = 31 * result + correctAnswer.hashCode();
        return result;
    }

    public Question() {
    }

    public Question(Long id, Subject subject, String query, String answer1, String answer2, String answer3, String answer4, String correctAnswer) {
        super(id);
        this.subject = subject;
        this.query = query;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correctAnswer = correctAnswer;
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
}
