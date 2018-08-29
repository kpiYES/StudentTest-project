package com.app.controller.commands;

import com.app.dto.DTOHandler;
import com.app.dto.UserDTO;
import com.app.model.User;
import com.app.service.ServiceFactory;
import com.app.service.UserService;
import com.app.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserCommand implements Command {
   private UserService userService = ServiceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserDTO userDTO =(UserDTO)request.getSession().getAttribute("userDTO");
        User user = DTOHandler.constructUser(userDTO);
        userService.delete(user);
//        request.getSession().removeAttribute("userDTO");
        return "admin.jsp";
    }
}
