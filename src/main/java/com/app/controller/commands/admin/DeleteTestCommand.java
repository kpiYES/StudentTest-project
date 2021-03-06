package com.app.controller.commands.admin;

import com.app.controller.commands.Command;
import com.app.model.Test;
import com.app.service.ServiceFactory;
import com.app.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteTestCommand implements Command {

    private TestService testService = ServiceFactory.getTestService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        testService.delete((Test) request.getSession().getAttribute("test"));
        String testName = ((Test) request.getSession().getAttribute("test")).getName();
        String msg = "Test '" + testName + "' was successfully deleted";
        request.getSession().removeAttribute("test");
        request.setAttribute("msg", msg);

        return "admin.jsp";
    }
}
