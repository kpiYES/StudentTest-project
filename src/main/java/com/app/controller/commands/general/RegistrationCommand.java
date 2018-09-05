package com.app.controller.commands.general;

import com.app.controller.commands.Command;
import com.app.dto.UserDTO;
import com.app.service.ServiceFactory;
import com.app.service.UserDetailsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegistrationCommand implements Command {

    private UserDetailsService userDetailsService = ServiceFactory.getUserDetailsService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        UserDTO currentUserDTO = userDetailsService.registration(request.getParameterMap());
        request.getSession().setAttribute("currentUser", currentUserDTO);
        return "student.jsp";
    }
}

