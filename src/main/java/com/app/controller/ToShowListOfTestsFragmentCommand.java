package com.app.controller;

import com.app.model.Subject;
import com.app.model.Test;
import com.app.service.SubjectService;
import com.app.service.TestService;
import com.app.service.impl.SubjectServiceImpl;
import com.app.service.impl.TestServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class ToShowListOfTestsFragmentCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        TestService testService = new TestServiceImpl();
        SubjectService subjectService = new SubjectServiceImpl();
        Subject subject = subjectService.findByName(request.getParameter("subjectName"));
        request.getSession().setAttribute("subject", subject);
        Set<Test> testSet = testService.findAllBySubjectId(subject.getId());
        request.getSession().setAttribute("testSet", testSet);
        request.setAttribute("pageFragment", "showListOfSubjectsFragment.jsp");
        request.setAttribute("subPageFragment", "showListOfTestsFragment.jsp");

        return "admin.jsp";
    }
}
