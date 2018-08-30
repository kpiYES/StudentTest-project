package com.app.controller.commands;

import com.app.service.PassedTestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreatePassedTestCommand implements Command {
    private PassedTestService passedTestService;

    public CreatePassedTestCommand(PassedTestService passedTestService) {
        this.passedTestService = passedTestService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
