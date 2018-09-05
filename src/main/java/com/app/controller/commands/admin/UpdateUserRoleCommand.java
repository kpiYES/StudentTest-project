package com.app.controller.commands.admin;

import com.app.controller.commands.Command;
import com.app.dto.UserDTO;
import com.app.service.ServiceFactory;
import com.app.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserRoleCommand implements Command {

    private UserService userService = ServiceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        if (request.getParameter("userRole") == null) {
            request.setAttribute("message", "You should choose different user role");
        } else {
            UserDTO updatedUserDTO = userService.updateRole(request.getParameter("userRole"), (UserDTO) request.getSession().getAttribute("userDTO"));
            request.getSession().setAttribute("userDTO", updatedUserDTO);
            request.setAttribute("message", "User role was successfully updated");
        }
        request.setAttribute("pageFragment", "showListOfUsersFragment.jsp");
        request.setAttribute("subPageFragment", "showUserFragment.jsp");
        return "admin.jsp";
    }
}
