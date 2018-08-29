package com.app.controller.commands.student;

import com.app.controller.commands.Command;
import com.app.model.Question;
import com.app.model.Test;
import com.app.service.QuestionService;
import com.app.service.ServiceFactory;
import com.app.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class ShowTestToPassTestFragmentCommand implements Command {

    private TestService testService = ServiceFactory.getTestService();
    private QuestionService questionService = ServiceFactory.getQuestionService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

//        Test test = testService.findByIdWithQuestions(Long.parseLong(request.getParameter("testId")));
        Test test = testService.findById(Long.parseLong(request.getParameter("testId")));
        Set<Question> questionSet = questionService.findAllByTestId(test.getId());
        test.setQuestionSet(questionSet);
        request.getSession().setAttribute("test", test);

        request.setAttribute("pageFragment", "showListOfSubjectsToPassTestFragment.jsp");
        request.setAttribute("subPageFragment", "showListOfTestsToPassTestFragment.jsp");
        request.setAttribute("subSubPageFragment", "showTestToPassTestFragment.jsp");

        return "student.jsp";
    }
}
