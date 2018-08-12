package com.app.dao;

import com.app.model.User;

public interface IUserDAO {

    Long insert(User user);

    void update(User user);

    void delete(User user);

    User getById(Long user_id);

    User getByMail(String email);

}
