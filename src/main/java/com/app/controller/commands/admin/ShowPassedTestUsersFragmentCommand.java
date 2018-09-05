package com.app.controller.commands.admin;

import com.app.controller.commands.Command;
import com.app.dto.PassedTestDTO;
import com.app.service.PassedTestService;
import com.app.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowPassedTestUsersFragmentCommand implements Command {

    private PassedTestService passedTestService = ServiceFactory.getPassedTestService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long passedTestId = Long.parseLong(request.getParameter("passedTestId"));
        PassedTestDTO passedTestDTO = passedTestService.findByIdWithQuestions(passedTestId);

        request.getSession().setAttribute("passedTest", passedTestDTO);
        request.setAttribute("pageFragment", "showPassedTestUsersFragment.jsp");
        return "admin.jsp";
    }
}
