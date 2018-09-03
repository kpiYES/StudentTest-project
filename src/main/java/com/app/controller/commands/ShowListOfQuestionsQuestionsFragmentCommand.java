package com.app.controller.commands;

import com.app.model.Question;
import com.app.model.Subject;
import com.app.service.QuestionService;
import com.app.service.ServiceFactory;
import com.app.service.SubjectService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class ShowListOfQuestionsQuestionsFragmentCommand implements Command {

    private QuestionService questionService = ServiceFactory.getQuestionService();
    private SubjectService subjectService = ServiceFactory.getSubjectService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Subject subject = subjectService.findById(Long.parseLong(request.getParameter("subjectId")));
        request.getSession().setAttribute("subject", subject);
        Set<Question> questionSet = questionService.findAllBySubjectId(subject.getId());
        request.getSession().setAttribute("questionSet", questionSet);
        request.setAttribute("pageFragment", "showListOfSubjectsQuestionsFragment.jsp");
        request.setAttribute("subPageFragment", "showListOfQuestionsQuestionsFragment.jsp");

        return "admin.jsp";
    }
}
