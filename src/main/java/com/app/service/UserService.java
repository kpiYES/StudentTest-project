package com.app.service;

import com.app.model.User;

public interface UserService extends CrudService<User> {

    User findByMail(String mail);

    boolean validateUserPassword(String password, User user);

    User assembleCredentials(User user, String rawPassword);
}
