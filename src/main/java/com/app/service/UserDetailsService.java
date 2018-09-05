package com.app.service;

import com.app.dto.UserDTO;

import java.util.Map;

public interface UserDetailsService {

    UserDTO logIn(String email, String password);

    UserDTO registration(Map<String, String[]> paramMap);

}
