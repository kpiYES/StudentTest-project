package com.app.controller.commands.admin;

import com.app.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToRegistrPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "registr.jsp";
    }
}
