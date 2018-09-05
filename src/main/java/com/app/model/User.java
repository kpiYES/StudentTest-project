package com.app.model;

import java.io.Serializable;
import java.util.Set;

public class User implements Serializable {

    private Long id;
    private Role role;
    private String firstName;
    private String lastName;
    private String mail;
    private String salt;
    private String hash;
    private Set<PassedTest> passedTestSet;

    public User() {
    }

    public User(Long id, Role role, String fistName, String lastName, String mail, String salt, String hash) {
        this.id = id;
        this.role = role;
        this.firstName = fistName;
        this.lastName = lastName;
        this.mail = mail;
        this.salt = salt;
        this.hash = hash;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fistName) {
        this.firstName = fistName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (role != null ? !role.equals(user.role) : user.role != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (mail != null ? !mail.equals(user.mail) : user.mail != null) return false;
        if (salt != null ? !salt.equals(user.salt) : user.salt != null) return false;
        return hash != null ? hash.equals(user.hash) : user.hash == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (salt != null ? salt.hashCode() : 0);
        result = 31 * result + (hash != null ? hash.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role=" + role +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mail='" + mail + '\'' +
                ", salt='" + salt + '\'' +
                ", hash='" + hash + '\'' +
                ", passedTestSet=" + passedTestSet +
                '}';
    }
}
