package com.app.controller.commands;

import com.app.dto.UserDTO;
import com.app.model.PassedTest;
import com.app.service.PassedTestService;
import com.app.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class ShowListOfPassedTestsUsersFragmentCommand implements Command {

    private PassedTestService passedTestService = ServiceFactory.getPassedTestService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long userId = ((UserDTO) request.getSession().getAttribute("userDTO")).getId();
        Long subjectId = Long.parseLong(request.getParameter("subjectId"));
        Set<PassedTest> passedTestSet = passedTestService.findAllByUserIdAndSubjectId(userId, subjectId);
        request.getSession().setAttribute("passedTestSet", passedTestSet);
        request.setAttribute("pageFragment", "showListOfSubjectsUsersFragment.jsp");
        request.setAttribute("subPageFragment", "showListOfPassedTestsUsersFragment.jsp");
        return "admin.jsp";    }
}
