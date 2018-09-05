package com.app.controller.commands.general;

import com.app.controller.commands.Command;
import com.app.dto.UserDTO;
import com.app.service.ServiceFactory;
import com.app.service.UserDetailsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LogInCommand implements Command {

    private UserDetailsService userDetailsService = ServiceFactory.getUserDetailsService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserDTO userDTO = userDetailsService.logIn(request.getParameter("email"), request.getParameter("password"));
        request.getSession().setAttribute("loc", "en");
        if (userDTO != null) {
            request.getSession().setAttribute("currentUser", userDTO);
            switch (userDTO.getRole().getName()) {
                case "admin":
                    return "admin.jsp";
                case "student":
                    return "student.jsp";
            }
        }
        request.setAttribute("errorMsg", "Unknown email or incorrect password. Please retry.");
        return "index.jsp";
    }
}

