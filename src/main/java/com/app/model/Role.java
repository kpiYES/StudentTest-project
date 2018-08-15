package com.app.model;


import java.util.Set;

public class Role extends AbstractEntity {

    private String name;
    private Set<User> userSet;

    public Role() {
    }

    public Role(Long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }
}
