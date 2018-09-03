package com.app.controller.commands.student;

import com.app.controller.commands.Command;
import com.app.dto.UserDTO;
import com.app.model.PassedTest;
import com.app.service.PassedTestService;
import com.app.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class ShowListOfPassedTestsFragmentCommand implements Command {

    private PassedTestService passedTestService = ServiceFactory.getPassedTestService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long userId = ((UserDTO) request.getSession().getAttribute("currentUser")).getId();
        Long subjectId = Long.parseLong(request.getParameter("subjectId"));
        Set<PassedTest> passedTestSet = passedTestService.findAllByUserIdAndSubjectId(userId, subjectId);
        request.getSession().setAttribute("passedTestSet", passedTestSet);
        request.setAttribute("pageFragment", "showListOfSubjectsPassedTestsFragment.jsp");
        request.setAttribute("subPageFragment", "showListOfPassedTestsFragment.jsp");
        return "student.jsp";
    }
}
