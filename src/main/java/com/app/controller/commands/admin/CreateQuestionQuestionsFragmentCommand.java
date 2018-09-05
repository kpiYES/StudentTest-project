package com.app.controller.commands.admin;

import com.app.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateQuestionQuestionsFragmentCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("pageFragment", "createQuestionQuestionsFragment.jsp");
        return "admin.jsp";
    }
}
