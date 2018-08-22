package com.app.controller;

import com.app.model.Question;
import com.app.model.Subject;
import com.app.model.Test;
import com.app.service.QuestionService;
import com.app.service.SubjectService;
import com.app.service.TestService;
import com.app.service.impl.QuestionServiceImpl;
import com.app.service.impl.SubjectServiceImpl;
import com.app.service.impl.TestServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

public class CreateTestCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

//        System.out.println("Create test");
//        TestService testService = new TestServiceImpl();
//        SubjectService subjectService = new SubjectServiceImpl();
//        QuestionService questionService = new QuestionServiceImpl();
//        Subject subject = subjectService.findByName((String) request.getSession().getAttribute("subject"));
//        Test test = new Test();
//        test.setSubject(subject);
//        test.setName((String) request.getSession().getAttribute("newTestName"));
//
//        Set<Long> questionsIdSet = (Set<Long>) request.getSession().getAttribute("chosenQuestionsSet");
//        Set<Question> questionSet = new HashSet<>();
//        for (Long id : questionsIdSet) {
//            Question question = questionService.findById(id);
//            questionSet.add(question);
//        }
//        test.setQuestionSet(questionSet);
//        testService.insert(test);
        return "admin.jsp";
    }
}
