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

public class UpdateUserRoleCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = new UserServiceImpl();
        RoleService roleService = new RoleServiceImpl();

       if (request.getParameter("userRole")!=null) {
           String newUserRole = request.getParameter("userRole");
           Role role = roleService.findByName(newUserRole);
           UserDTO userDTO = (UserDTO)request.getSession().getAttribute("userDTO");
           User user = userService.findById(userDTO.getId());
           user.setRole(role);
//           User user = DTOHandler.constructUser( userDTO);
//           userService.updateByRoleName(user, newUserRole);
           userService.update(user);
           User updatedUser = userService.findById(user.getId());
           UserDTO updatedUserDTO = DTOHandler.constructUserDTO(updatedUser);
           request.getSession().setAttribute("userDTO", updatedUserDTO);
           request.setAttribute("Massage","User role was successfully updated");
       }else{
           request.setAttribute("Massage", "You should choose different user role");
       }
        request.setAttribute("pageFragment", "showListOfUsersFragment.jsp");
        request.setAttribute("subPageFragment", "showUserFragment.jsp");
        return "admin.jsp";
    }
}
