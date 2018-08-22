package com.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToCreateQuestionPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("subPageFragment","createQuestionFragment.jsp");
        return "admin.jsp";
    }
}
