package com.app.controller.commands;

import com.app.model.Question;
import com.app.model.Test;
import com.app.service.QuestionService;
import com.app.service.ServiceFactory;
import com.app.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class ShowQuestionQuestionsFragmentCommand implements Command {

    private QuestionService questionService = ServiceFactory.getQuestionService();
    private TestService testService = ServiceFactory.getTestService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Question question = questionService.findById(Long.parseLong(request.getParameter("questionId")));
        request.getSession().setAttribute("question", question);
        Set<Test> testSet = testService.getFindAllByQuestionId(((Question) request.getSession().getAttribute("question")).getId());

        request.getSession().setAttribute("testSet", testSet);
        request.setAttribute("pageFragment", "showListOfSubjectsQuestionsFragment.jsp");
        request.setAttribute("subPageFragment", "showListOfQuestionsQuestionsFragment.jsp");
        request.setAttribute("subSubPageFragment", "showQuestionQuestionsFragment.jsp");

        return "admin.jsp";
    }
}
