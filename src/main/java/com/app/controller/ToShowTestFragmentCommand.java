package com.app.controller;

import com.app.model.Question;
import com.app.model.Subject;
import com.app.model.Test;
import com.app.service.QuestionService;
import com.app.service.TestService;
import com.app.service.impl.QuestionServiceImpl;
import com.app.service.impl.TestServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class ToShowTestFragmentCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int page = 1;
        int recordsPerPage = 3;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        TestService testService = new TestServiceImpl();
        QuestionService questionService = new QuestionServiceImpl();
        System.out.println(request.getParameter("testId"));
        Test test = testService.findById(Long.parseLong(request.getParameter("testId")));
        Set<Question> questionSetWithPagination = questionService.findAllByTestIdWithPagination(test.getId(),recordsPerPage, (page - 1) * recordsPerPage);

        Set<Question> questionSet = questionService.findAllByTestId(test.getId());
        long numberOfRecords = questionSet.size();
        long numberOfPages = (long) Math.ceil(numberOfRecords * 1.0 / recordsPerPage);

        request.setAttribute("questionSet", questionSetWithPagination);
        request.setAttribute("currentPage", page);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("test", test);
        request.setAttribute("pageFragment", "showListOfSubjectsFragment.jsp");
        request.setAttribute("subPageFragment", "showListOfTestsFragment.jsp");
        request.setAttribute("subSubPageFragment", "showTestFragment.jsp");
        return "admin.jsp";
    }
}
