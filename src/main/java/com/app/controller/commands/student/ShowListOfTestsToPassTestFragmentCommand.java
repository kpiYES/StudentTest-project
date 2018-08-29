package com.app.controller.commands.student;

import com.app.controller.commands.Command;
import com.app.model.Subject;
import com.app.model.Test;
import com.app.service.ServiceFactory;
import com.app.service.SubjectService;
import com.app.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class ShowListOfTestsToPassTestFragmentCommand implements Command {

    private TestService testService = ServiceFactory.getTestService();
    private SubjectService subjectService = ServiceFactory.getSubjectService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Subject subject = subjectService.findByName(request.getParameter("subjectName"));
        request.getSession().setAttribute("subject", subject);
        Set<Test> testSet = testService.findAllBySubjectId(subject.getId());
        request.getSession().setAttribute("testSet", testSet);
        request.setAttribute("pageFragment", "showListOfSubjectsToPassTestFragment.jsp");
        request.setAttribute("subPageFragment", "showListOfTestsToPassTestFragment.jsp");

        return "student.jsp";
    }
}
