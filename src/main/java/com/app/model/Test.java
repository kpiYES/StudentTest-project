package com.app.model;

import java.io.Serializable;
import java.util.Set;

public class Test implements Serializable {

    private Long id;
    private Subject subject;
    private String name;
    private Set<Question> questionSet;
    private Set<PassedTest> passedTestSet;

    public Test() {
    }

    public Test(Long id, Subject subject, String name) {
        this.id = id;
        this.name = name;
        this.subject = subject;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Test)) return false;

        Test test = (Test) o;

        if (id != null ? !id.equals(test.id) : test.id != null) return false;
        if (subject != null ? !subject.equals(test.subject) : test.subject != null) return false;
        return name != null ? name.equals(test.name) : test.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", subject=" + subject +
                ", name='" + name + '\'' +
                ", questionSet=" + questionSet +
                '}';
    }
}
