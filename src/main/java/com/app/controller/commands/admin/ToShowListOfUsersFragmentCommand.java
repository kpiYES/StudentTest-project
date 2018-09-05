package com.app.controller.commands.admin;

import com.app.controller.commands.Command;
import com.app.dto.UserDTO;
import com.app.service.ServiceFactory;
import com.app.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class ToShowListOfUsersFragmentCommand implements Command {

    private UserService userService = ServiceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Set<UserDTO> userDTOSet = userService.findAll();
        request.getSession().setAttribute("userDTOSet", userDTOSet);
        request.setAttribute("pageFragment", "showListOfUsersFragment.jsp");
        return "admin.jsp";
    }
}
