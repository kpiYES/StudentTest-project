package com.app.controller.commands;

import com.app.dto.DTOHandler;
import com.app.dto.UserDTO;
import com.app.model.User;
import com.app.service.ServiceFactory;
import com.app.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginCommand implements Command {

    private UserService userService = ServiceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        User user = userService.findByMail(request.getParameter("email"));
        if (user.getMail() == null || !userService.validateUserPassword(request.getParameter("password"), user)) {
            request.setAttribute("errorMsg", "Unknown email or incorrect password. Please retry.");
            return "index.jsp";
        }
        UserDTO userDTO = DTOHandler.constructUserDTO(user);
        request.getSession().setAttribute("currentUser", userDTO);
        switch (userDTO.getRole().getName()) {
            case "admin":
                return "admin.jsp";
            case "student":
                return "student.jsp";
            default:
                return "index.jsp";
        }
    }
}

