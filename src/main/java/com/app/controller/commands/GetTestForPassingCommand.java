package com.app.controller.commands;

import com.app.service.PassedTestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetTestForPassingCommand implements Command {
    private PassedTestService passedTestService;

    public GetTestForPassingCommand(PassedTestService passedTestService) {
        this.passedTestService = passedTestService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
//        passedTestService.getById(Long.parseLong(request.getParameter("testId")));
        return null;
    }
}
