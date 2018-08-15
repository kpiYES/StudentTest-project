package com.app.controller;

import com.app.service.PassedTestService;
import com.app.service.impl.PassedTestServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreatePassedTestCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        PassedTestService passedTestService = new PassedTestServiceImpl();


        return null;
    }
}
