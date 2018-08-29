package com.app.controller.commands;

import com.app.model.PassedTest;
import com.app.model.User;
import com.app.service.PassedTestService;
import com.app.service.ServiceFactory;
import com.app.service.UserService;
import com.app.service.impl.PassedTestServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class GetPassedTestsCommand implements Command {
    private PassedTestService passedTestService = ServiceFactory.getPassedTestService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Set<PassedTest> passedTestSet = passedTestService.findByUserId(((User)request.getSession().getAttribute("currentUser")).getId());
        return null;
    }
}