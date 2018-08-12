package com.app.service;

import com.app.dto.UserDTO;
import com.app.model.User;

public interface UserService {

    User getByMail(String mail);

    boolean validateUserPassword(String password, User user);

    UserDTO constructUserDTO(User user);
}
