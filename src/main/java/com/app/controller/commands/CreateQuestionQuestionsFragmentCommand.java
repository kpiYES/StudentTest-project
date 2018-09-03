package com.app.controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateQuestionQuestionsFragmentCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("pageFragment", "createQuestionQuestionsFragment.jsp");
        return "admin.jsp";
    }
}
