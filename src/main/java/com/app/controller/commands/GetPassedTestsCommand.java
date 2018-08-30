package com.app.controller.commands;

import com.app.model.PassedTest;
import com.app.model.User;
import com.app.service.PassedTestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class GetPassedTestsCommand implements Command {
    private PassedTestService passedTestService;

    public GetPassedTestsCommand(PassedTestService passedTestService) {
        this.passedTestService = passedTestService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Set<PassedTest> passedTestSet = passedTestService.findByUserId(((User) request.getSession().getAttribute("currentUser")).getId());
        return null;
    }
}
