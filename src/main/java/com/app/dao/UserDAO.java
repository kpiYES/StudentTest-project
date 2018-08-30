package com.app.dao;

import com.app.model.User;

public interface UserDAO extends AbstractDAO<User> {

    User findByMail(String mail);
}
