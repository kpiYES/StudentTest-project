package com.app.dao;

import com.app.model.User;

import java.util.Set;

public interface UserDAO {

    Long insert(User user);

    void update(User user);

    void delete(User user);

    User findById(Long user_id);

    Set<User> findAll();

    User findByMail(String mail);

//    void updateByRoleName(User user, String roleName);

}
