package com.app.controller.filter;

import javax.servlet.*;
import java.io.IOException;

public class CommandFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest.getParameter("action") != null) {
            servletRequest.setAttribute("command", servletRequest.getParameter("action"));
        } else {
            servletRequest.setAttribute("command", servletRequest.getParameter("command"));
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
