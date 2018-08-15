package com.app.dao;

import com.app.model.User;

public interface UserDAO {

    Long insert(User user);

    void update(User user);

    void delete(User user);

    User findById(Long user_id);

    User findByMail(String mail);

}
