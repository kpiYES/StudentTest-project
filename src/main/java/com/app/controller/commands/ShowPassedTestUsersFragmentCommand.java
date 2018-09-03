package com.app.controller.commands;

import com.app.model.PassedQuestion;
import com.app.model.PassedTest;
import com.app.service.PassedQuestionService;
import com.app.service.PassedTestService;
import com.app.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class ShowPassedTestUsersFragmentCommand implements Command {

    private PassedTestService passedTestService = ServiceFactory.getPassedTestService();
    private PassedQuestionService passedQuestionService = ServiceFactory.getPassedQuestionService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long passedTestId = Long.parseLong(request.getParameter("passedTestId"));
        PassedTest passedTest = passedTestService.findById(passedTestId);
        Set<PassedQuestion> passedQuestionSet = passedQuestionService.findAllByPassedTestId(passedTestId);
        passedTest.setPassedQuestionSet(passedQuestionSet);

        request.getSession().setAttribute("passedTest", passedTest);
        request.setAttribute("pageFragment", "showPassedTestUsersFragment.jsp");
        return "admin.jsp";
    }
}
