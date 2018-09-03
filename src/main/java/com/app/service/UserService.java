package com.app.service;

import com.app.model.User;

import java.util.Set;

public interface UserService {

    Long insert(User user);

    void update(User user);

    User findById(Long user_id);

    Set<User> findAll();

    User findByMail(String mail);

    boolean validateUserPassword(String password, User user);
}
