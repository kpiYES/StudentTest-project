package com.app.controller;

import com.app.model.Question;
import com.app.model.Subject;
import com.app.service.QuestionService;
import com.app.service.SubjectService;
import com.app.service.impl.QuestionServiceImpl;
import com.app.service.impl.SubjectServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateQuestionCommand implements Command {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        QuestionService questionService = new QuestionServiceImpl();
        SubjectService subjectService = new SubjectServiceImpl();
        Subject subject = subjectService.findByName(request.getParameter("subject"));


        Question question = new Question();
        question.setSubject(subject);
        question.setQuery(request.getParameter("query"));
        question.setAnswer1(request.getParameter("answer1"));
        question.setAnswer2(request.getParameter("answer2"));

        if (request.getParameterMap().containsKey("answer3")) {
            question.setAnswer3(request.getParameter("answer3"));
        }

        if (request.getParameterMap().containsKey("answer4")) {
            question.setAnswer4(request.getParameter("answer4"));
        }

        questionService.insert(question);
        return null;
    }
}
