package com.app.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        if ((request.getSession().getAttribute("currentUser") == null) &&
                (!request.getParameter("command").equals("login") && !request.getParameter("command").equals("toRegistrPage")
                        && (!request.getParameter("command").equals("registration")))) {
            response.sendRedirect("index.jsp");
        } else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {
    }
}
