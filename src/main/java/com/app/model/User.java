package com.app.model;

import java.util.Set;

public class User extends AbstractEntity {

    private Role role;
    private String fistName;
    private String lastName;
    private String mail;
    private String salt;
    private String hash;
    private Set<PassedTest> passedTestSet;


    public User() {
    }

    public User(Long id, Role role, String fistName, String lastName, String mail, String salt, String hash) {
        super(id);
        this.role = role;
        this.fistName = fistName;
        this.lastName = lastName;
        this.mail = mail;
        this.salt = salt;
        this.hash = hash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Set<PassedTest> getPassedTestSet() {
        return passedTestSet;
    }

    public void setPassedTestSet(Set<PassedTest> passedTestSet) {
        this.passedTestSet = passedTestSet;
    }
}
