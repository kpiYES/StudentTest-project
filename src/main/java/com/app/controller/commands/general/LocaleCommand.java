package com.app.controller.commands.general;

import com.app.controller.commands.Command;
import com.app.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LocaleCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String loc = request.getParameter("locale");
        request.getSession().setAttribute("loc", loc);
        return ((UserDTO) request.getSession().getAttribute("currentUser")).getRole().getName() + ".jsp";
    }
}
