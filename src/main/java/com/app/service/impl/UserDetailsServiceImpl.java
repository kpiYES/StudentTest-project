package com.app.service.impl;


import com.app.dto.DTOHandler;
import com.app.dto.UserDTO;
import com.app.exceptions.PasswordValidationException;
import com.app.model.Role;
import com.app.model.User;
import com.app.service.RoleService;
import com.app.service.ServiceFactory;
import com.app.service.UserDetailsService;
import com.app.service.UserService;
import com.app.service.util.PasswordSecurity;
import com.app.service.util.Validator;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;
    private RoleService roleService;

    private UserDetailsServiceImpl() {
        userService = ServiceFactory.getUserService();
        roleService = ServiceFactory.getRoleService();
    }

    public static UserDetailsServiceImpl getInstance() {
        return UserDetailsServiceImpl.UserDetailsServiceImplHolder.INSTANCE;
    }

    @Override
    public UserDTO logIn(String email, String password) {
        UserDTO userDTO = null;
        if (Validator.validateLogInForm(email, password)) {
            User user = userService.findByMail(email);
            try {
                if (PasswordSecurity.validatePassword(password, user.getHash(), user.getSalt())) {
                    userDTO = DTOHandler.constructUserDTO(user);
                }
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                throw new PasswordValidationException("Password validation exception", e);
            }
        }
        return userDTO;
    }

    @Override
    public UserDTO registration(Map<String, String[]> paramMap) {

        if (!Validator.validateRegistrationForm(paramMap)) {
            return null;
        }

        Role role = roleService.findByName("student");
        User user = new User();
        user.setRole(role);
        user.setFirstName(paramMap.get("firstName")[0]);
        user.setLastName(paramMap.get("lastName")[0]);
        user.setMail(paramMap.get("email")[0]);
        String saltedHash = PasswordSecurity.generateSaltedPasswordHash(paramMap.get("password")[0]);
        user.setHash(PasswordSecurity.getHashFromSaltedHash(saltedHash));
        user.setSalt(PasswordSecurity.getSaltFromSaltedHash(saltedHash));
        user.setId(userService.insert(user));
        return DTOHandler.constructUserDTO(user);
    }


    private static class UserDetailsServiceImplHolder {
        private final static UserDetailsServiceImpl INSTANCE = new UserDetailsServiceImpl();
    }
}

