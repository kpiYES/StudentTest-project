package com.app.controller.commands;

import com.app.model.Subject;
import com.app.model.Test;
import com.app.service.ServiceFactory;
import com.app.service.SubjectService;
import com.app.service.TestService;
import com.app.service.impl.SubjectServiceImpl;
import com.app.service.impl.TestServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class ShowListOfTestsTestsFragmentCommand implements Command {

   private TestService testService = ServiceFactory.getTestService();
   private SubjectService subjectService = ServiceFactory.getSubjectService();

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
