package com.app.controller.commands.admin;

import com.app.controller.commands.Command;
import com.app.model.Subject;
import com.app.service.QuestionService;
import com.app.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateQuestionCommand implements Command {

    private QuestionService questionService = ServiceFactory.getQuestionService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        if (questionService.create(request.getParameterMap(), (Subject) request.getSession().getAttribute("subject"))) {
            request.setAttribute("msg", "Question has successfully created");
            return "admin.jsp";
        }
        request.setAttribute("errorMsg", "Wrong answer! Correct answer must exist.");
        request.setAttribute("pageFragment", "showListOfSubjectsQuestionsFragment.jsp");
        request.setAttribute("subPageFragment", "showListOfQuestionsQuestionsFragment.jsp");
        return "admin.jsp";
    }
}
