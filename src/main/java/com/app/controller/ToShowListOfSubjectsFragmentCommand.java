package com.app.controller;

import com.app.model.Subject;
import com.app.service.SubjectService;
import com.app.service.impl.SubjectServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class ToShowListOfSubjectsFragmentCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        SubjectService subjectService = new SubjectServiceImpl();
       Set<Subject> subjectSet = subjectService.findAll();
       request.getSession().setAttribute("subjectSet", subjectSet);
        request.setAttribute("pageFragment", "showListOfSubjectsFragment.jsp");
       return "admin.jsp";
    }
}
