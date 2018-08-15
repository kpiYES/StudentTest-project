package com.app.dto;

import com.app.model.User;

public class DTOHandler {

    public static UserDTO constructUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFistName(user.getFistName());
        userDTO.setLastName(user.getLastName());
        userDTO.setMail(user.getMail());
        userDTO.setRole(user.getRole().getName());
        return userDTO;
    }
}
