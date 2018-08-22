package com.app.dto;

import com.app.model.User;

import java.util.HashSet;
import java.util.Set;

public class DTOHandler {

    public static UserDTO constructUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setMail(user.getMail());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    public static User constructUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setMail(userDTO.getMail());
        user.setRole(userDTO.getRole());
        return user;
    }

    public static Set<UserDTO> constructSetUserDTO(Set<User> usersSet){
        Set<UserDTO> usersDTOSet = new HashSet<>();
        for(User user : usersSet){
            usersDTOSet.add(constructUserDTO(user));
        }
        return usersDTOSet;
    }
}
