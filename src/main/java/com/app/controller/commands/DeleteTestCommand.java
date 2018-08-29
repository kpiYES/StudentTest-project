package com.app.controller.commands;

import com.app.model.Test;
import com.app.service.ServiceFactory;
import com.app.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteTestCommand implements Command {
    TestService testService = ServiceFactory.getTestService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        testService.delete((Test)request.getSession().getAttribute("test"));
        testService.
        return null;
    }
}
