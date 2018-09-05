package com.app.dto;

import com.app.model.PassedTest;
import com.app.model.User;

import java.util.HashSet;
import java.util.Set;

public class DTOHandler {

    public static UserDTO constructUserDTO(User user) {
        return UserDTO.newBuilder().id(user.getId()).
                firstName(user.getFirstName()).lastName(user.getLastName()).
                mail(user.getMail()).role(user.getRole()).build();
    }

    public static PassedTestDTO constructPassedTestDTO(PassedTest passedTest) {
        PassedTestDTO passedTestDTO = new PassedTestDTO();
        passedTestDTO.setId(passedTest.getId());
        passedTestDTO.setUser(DTOHandler.constructUserDTO(passedTest.getUser()));
        passedTestDTO.setTest(passedTest.getTest());
        passedTestDTO.setMark(passedTest.getMark());
        return passedTestDTO;
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

    public static Set<UserDTO> constructSetUserDTO(Set<User> usersSet) {
        Set<UserDTO> usersDTOSet = new HashSet<>();
        for (User user : usersSet) {
            usersDTOSet.add(constructUserDTO(user));
        }
        return usersDTOSet;
    }
}
