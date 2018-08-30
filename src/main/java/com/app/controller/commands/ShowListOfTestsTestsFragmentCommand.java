package com.app.controller.commands;

import com.app.model.Subject;
import com.app.model.Test;
import com.app.service.SubjectService;
import com.app.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class ShowListOfTestsTestsFragmentCommand implements Command {

    private TestService testService;
    private SubjectService subjectService;

    public ShowListOfTestsTestsFragmentCommand(TestService testService, SubjectService subjectService) {
        this.testService = testService;
        this.subjectService = subjectService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Subject subject = subjectService.findByName(request.getParameter("subjectName"));
        request.getSession().setAttribute("subject", subject);
        Set<Test> testSet = testService.findAllBySubjectId(subject.getId());
        request.getSession().setAttribute("testSet", testSet);
        request.setAttribute("pageFragment", "showListOfSubjectsTestsFragment.jsp");
        request.setAttribute("subPageFragment", "showListOfTestsTestsFragment.jsp");

        return "admin.jsp";
    }
}
