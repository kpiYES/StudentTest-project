package com.app.controller;

import com.app.dao.TestDAO;
import com.app.dao.mySQLImpl.TestDAOImpl;
import com.app.model.Subject;
import com.app.model.Test;
import com.app.service.SubjectService;
import com.app.service.TestService;
import com.app.service.impl.SubjectServiceImpl;
import com.app.service.impl.TestServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateTestCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        TestService testService = new TestServiceImpl();
        SubjectService subjectService = new SubjectServiceImpl();
        Subject subject = subjectService.findByName(request.getParameter("subject"));

        Test test = new Test();
        test.setSubject(subject);
        test.setName(request.getParameter("name"));
        testService.insert(test);


        return null;
    }
}
