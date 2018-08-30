package com.app.controller.commands.student;

import com.app.controller.commands.Command;
import com.app.model.Subject;
import com.app.service.SubjectService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class ShowListOfSubjectsToPassTestFragmentCommand implements Command {

    private SubjectService subjectService;

    public ShowListOfSubjectsToPassTestFragmentCommand(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Set<Subject> subjectSet = subjectService.findAll();
        request.getSession().setAttribute("subjectSet", subjectSet);
        request.setAttribute("pageFragment", "showListOfSubjectsToPassTestFragment.jsp");

        return "student.jsp";
    }
}
