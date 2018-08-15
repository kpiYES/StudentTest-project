package com.app.controller;

import com.app.dto.DTOHandler;
import com.app.dto.UserDTO;
import com.app.model.User;
import com.app.service.UserService;
import com.app.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginCommand implements Command {

    private UserService userService;

    public LoginCommand() {
        userService = new UserServiceImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        User user = userService.findByMail(request.getParameter("email"));

        if (user == null) {
            request.setAttribute("errorMsg", "Unknown email. Please retry.");
        return "index.jsp";
        }else if (!userService.validateUserPassword(request.getParameter("password"), user)){
            request.setAttribute("errorMsg", "Incorrect password. Please retry.");
            return "index.jsp";
        }else{
            UserDTO userDTO = DTOHandler.constructUserDTO(user);
            request.getSession().setAttribute("user", userDTO);

            switch (userDTO.getRole()) {
                case "admin":
                    return "admin.jsp";
                case "student":
                    return "student.jsp";
                    default: return "index.jsp";
            }
        }
    }
}

