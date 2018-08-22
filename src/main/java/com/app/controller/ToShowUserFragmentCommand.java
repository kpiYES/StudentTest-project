package com.app.controller;

import com.app.dto.DTOHandler;
import com.app.dto.UserDTO;
import com.app.model.Role;
import com.app.model.User;
import com.app.service.RoleService;
import com.app.service.UserService;
import com.app.service.impl.RoleServiceImpl;
import com.app.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class ToShowUserFragmentCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = new UserServiceImpl();
        RoleService roleService = new RoleServiceImpl();
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
