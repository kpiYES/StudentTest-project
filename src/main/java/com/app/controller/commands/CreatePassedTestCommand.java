package com.app.controller.commands;

import com.app.service.PassedTestService;
import com.app.service.ServiceFactory;
import com.app.service.impl.PassedTestServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreatePassedTestCommand implements Command {
    private PassedTestService passedTestService = ServiceFactory.getPassedTestService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
