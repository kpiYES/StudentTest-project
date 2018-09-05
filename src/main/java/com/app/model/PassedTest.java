package com.app.model;

import java.io.Serializable;
import java.util.Set;

public class PassedTest implements Serializable {

    private Long id;
    private User user;
    private Test test;
    private Integer mark;
    private Set<PassedQuestion> passedQuestionSet;

    public PassedTest() {
    }

    public PassedTest(Long id, User user, Test test, Integer mark) {
        this.id = id;
        this.user = user;
        this.test = test;
        this.mark = mark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PassedTest)) return false;

        PassedTest that = (PassedTest) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (test != null ? !test.equals(that.test) : that.test != null) return false;
        return mark != null ? mark.equals(that.mark) : that.mark == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (test != null ? test.hashCode() : 0);
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PassedTest{" +
                "id=" + id +
                ", user=" + user +
                ", test=" + test +
                ", mark=" + mark +
                '}';
    }
}
