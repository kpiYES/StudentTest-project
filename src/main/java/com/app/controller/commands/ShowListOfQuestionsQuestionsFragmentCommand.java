package com.app.controller.commands;

import com.app.model.Question;
import com.app.model.Subject;
import com.app.service.QuestionService;
import com.app.service.SubjectService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class ShowListOfQuestionsQuestionsFragmentCommand implements Command {

    private QuestionService questionService;
    private SubjectService subjectService;

    public ShowListOfQuestionsQuestionsFragmentCommand(QuestionService questionService, SubjectService subjectService) {
        this.questionService = questionService;
        this.subjectService = subjectService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Subject subject = subjectService.findByName(request.getParameter("subjectName"));
        request.getSession().setAttribute("subject", subject);
        Set<Question> questionSet = questionService.findAllBySubjectId(subject.getId());
        request.getSession().setAttribute("questionSet", questionSet);
        request.setAttribute("pageFragment", "showListOfSubjectsQuestionsFragment.jsp");
        request.setAttribute("subPageFragment", "showListOfQuestionsQuestionsFragment.jsp");

        return "admin.jsp";
    }
}
