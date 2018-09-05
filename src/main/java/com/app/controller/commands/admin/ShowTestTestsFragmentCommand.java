package com.app.controller.commands.admin;

import com.app.controller.commands.Command;
import com.app.model.Test;
import com.app.service.ServiceFactory;
import com.app.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowTestTestsFragmentCommand implements Command {

    private TestService testService = ServiceFactory.getTestService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Test test = testService.findByIdWithQuestions(Long.parseLong(request.getParameter("testId")));
        request.getSession().setAttribute("test", test);
        request.setAttribute("pageFragment", "showTestTestsFragment.jsp");
        return "admin.jsp";
    }
}
