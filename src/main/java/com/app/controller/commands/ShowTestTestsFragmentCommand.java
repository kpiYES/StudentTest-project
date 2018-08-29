package com.app.controller.commands;

import com.app.model.Question;
import com.app.model.Test;
import com.app.service.QuestionService;
import com.app.service.Service;
import com.app.service.ServiceFactory;
import com.app.service.TestService;
import com.app.service.impl.QuestionServiceImpl;
import com.app.service.impl.TestServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class ShowTestTestsFragmentCommand implements Command {

   private TestService testService = ServiceFactory.getTestService();
   private QuestionService questionService = ServiceFactory.getQuestionService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int page = 1;
        int recordsPerPage = 3;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if(request.getParameterMap().containsKey("testId")) {
            Test test = testService.findById(Long.parseLong(request.getParameter("testId")));
            request.getSession().setAttribute("test", test);
        }
        Set<Question> questionSetWithPagination = questionService.findAllByTestIdWithPagination(((Test)request.getSession().getAttribute("test")).getId(),recordsPerPage, (page - 1) * recordsPerPage);

        Set<Question> questionSet = questionService.findAllByTestId(((Test)request.getSession().getAttribute("test")).getId());
        long numberOfRecords = questionSet.size();
        long numberOfPages = (long) Math.ceil(numberOfRecords * 1.0 / recordsPerPage);

        request.setAttribute("questionSet", questionSetWithPagination);
        request.setAttribute("currentPage", page);
        request.setAttribute("numberOfPages", numberOfPages);

        request.setAttribute("pageFragment", "showListOfSubjectsTestsFragment.jsp");
        request.setAttribute("subPageFragment", "showListOfTestsTestsFragment.jsp");
        request.setAttribute("subSubPageFragment", "showTestTestsFragment.jsp");
        return "admin.jsp";
    }
}
