package com.app.controller.commands.admin;

import com.app.controller.commands.Command;
import com.app.model.Question;
import com.app.service.QuestionService;
import com.app.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteQuestionCommand implements Command {

    private QuestionService questionService = ServiceFactory.getQuestionService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        questionService.delete((Question) request.getSession().getAttribute("question"));
        request.setAttribute("msg", "The question was successfully deleted.");
        request.getSession().removeAttribute("question");
        return "admin.jsp";
    }
}
