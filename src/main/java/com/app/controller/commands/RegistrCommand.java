package com.app.controller.commands;

import com.app.dto.DTOHandler;
import com.app.dto.UserDTO;
import com.app.model.Role;
import com.app.model.User;
import com.app.service.RoleService;
import com.app.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegistrCommand implements Command {

    private UserService userService;
    private RoleService roleService;

    public RegistrCommand(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if (!validatePassword(request)) {
            request.setAttribute("errorMsg", "You've incorrectly confirm password, please try again");
            return "registr.jsp";
        }
        Role role = roleService.findByName("student");
        User rawUser = new User();
        rawUser.setRole(role);
        rawUser.setFirstName(request.getParameter("firstName"));
        rawUser.setLastName(request.getParameter("lastName"));
        rawUser.setMail(request.getParameter("email"));
        String password = request.getParameter("password");
        User user = userService.assembleCredentials(rawUser, password);
        Long id = userService.insert(user);
        User currentUser = userService.findById(id);
        UserDTO currentUserDTO = DTOHandler.constructUserDTO(currentUser);
        request.getSession().setAttribute("currentUserDTO", currentUserDTO);
        return "student.jsp";
    }

    private boolean validatePassword(HttpServletRequest request) {
        return request.getParameter("password").equals(request.getParameter("confirmPassword"));
    }
}

