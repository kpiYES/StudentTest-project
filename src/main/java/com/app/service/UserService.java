package com.app.service;

import com.app.dto.UserDTO;
import com.app.model.User;

import java.util.Set;

public interface UserService {

    Long insert(User user);

    void update(User user);

    User findById(Long user_id);

    Set<UserDTO> findAll();

    User findByMail(String mail);

    UserDTO updateRole(String newUserRole, UserDTO userDTO);
}
