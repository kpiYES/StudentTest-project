package com.app.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletDispatcher extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        CommandHelper commandHelper = new CommandHelper();
        String page = commandHelper.startCommand(req, resp);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(page);

        if ((req.getAttribute("redirect")) != null) {
            resp.sendRedirect((String) req.getAttribute("redirect"));
        } else {
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
