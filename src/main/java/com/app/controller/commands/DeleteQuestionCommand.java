package com.app.controller.commands;

import com.app.model.Question;
import com.app.service.QuestionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteQuestionCommand implements Command {

    private QuestionService questionService;

    public DeleteQuestionCommand(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        questionService.delete((Question) request.getSession().getAttribute("question"));
        request.setAttribute("msg", "The question was successfully deleted.");
        request.getSession().removeAttribute("question");
        request.setAttribute("pageFragment", "showListOfSubjectsQuestionsFragment.jsp");
        request.setAttribute("subPageFragment", "showListOfQuestionsQuestionsFragment.jsp");
        return "admin.jsp";
    }
}
