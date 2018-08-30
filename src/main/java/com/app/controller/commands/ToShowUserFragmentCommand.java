package com.app.controller.commands;

import com.app.dto.DTOHandler;
import com.app.dto.UserDTO;
import com.app.model.Role;
import com.app.model.User;
import com.app.service.RoleService;
import com.app.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class ToShowUserFragmentCommand implements Command {

    private UserService userService;
    private RoleService roleService;

    public ToShowUserFragmentCommand(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        User user = userService.findByMail(request.getParameter("userMail"));
        UserDTO userDTO = DTOHandler.constructUserDTO(user);
        Set<Role> roleSet = roleService.findAll();

        request.getSession().setAttribute("userDTO", userDTO);
        request.getSession().setAttribute("roleSet", roleSet);
        request.setAttribute("pageFragment", "showListOfUsersFragment.jsp");
        request.setAttribute("subPageFragment", "showUserFragment.jsp");
        return "admin.jsp";
    }
}
