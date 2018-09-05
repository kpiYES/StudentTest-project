package com.app.controller.commands.admin;

import com.app.controller.commands.Command;
import com.app.dto.DTOHandler;
import com.app.dto.UserDTO;
import com.app.model.Role;
import com.app.service.RoleService;
import com.app.service.ServiceFactory;
import com.app.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class ToShowUserFragmentCommand implements Command {

    private UserService userService = ServiceFactory.getUserService();
    private RoleService roleService = ServiceFactory.getRoleService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        UserDTO userDTO = DTOHandler.constructUserDTO(userService.findByMail(request.getParameter("userMail")));
        Set<Role> roleSet = roleService.findAll();
        request.getSession().setAttribute("userDTO", userDTO);
        request.getSession().setAttribute("roleSet", roleSet);
        request.setAttribute("pageFragment", "showListOfUsersFragment.jsp");
        request.setAttribute("subPageFragment", "showUserFragment.jsp");
        return "admin.jsp";
    }
}
