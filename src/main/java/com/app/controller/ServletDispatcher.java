package com.app.controller;

import com.app.controller.commands.Command;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletDispatcher extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ServletDispatcher.class);

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        CommandHelper commandHelper = new CommandHelper();
        Command command = commandHelper.chooseCommand(req);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(command.execute(req, resp));
        if ((req.getAttribute("redirect"))!=null) {
            resp.sendRedirect((String) req.getAttribute("redirect"));
        }
        else {
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
