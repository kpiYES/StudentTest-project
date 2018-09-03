package com.app.controller.commands;

import com.app.model.Question;
import com.app.model.Subject;
import com.app.service.QuestionService;
import com.app.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateQuestionCommand implements Command {
    private QuestionService questionService = ServiceFactory.getQuestionService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        if (!validateAnswerForm(request, response)) {
            request.setAttribute("errorMsg", "Wrong answer! Correct answer must exist.");
            request.setAttribute("pageFragment", "showListOfSubjectsQuestionsFragment.jsp");
            request.setAttribute("subPageFragment", "showListOfQuestionsQuestionsFragment.jsp");
            return "admin.jsp";
        }

        Question question = new Question();
        question.setSubject((Subject) request.getSession().getAttribute("subject"));
        question.setQuery(request.getParameter("query"));
        question.setAnswer1(request.getParameter("answer1"));
        question.setAnswer2(request.getParameter("answer2"));
        question.setAnswer3(request.getParameter("answer3"));
        question.setAnswer4(request.getParameter("answer4"));
        question.setCorrectAnswer(request.getParameter(request.getParameter("correctAnswer")));
        questionService.insert(question);
        request.setAttribute("msg", "Question has successfully created");

        return "admin.jsp";
    }

    private boolean validateAnswerForm(HttpServletRequest request, HttpServletResponse response) {

        return !(request.getParameter("correctAnswer").equals("answer3") && request.getParameter("answer3").isEmpty()) ||
                (request.getParameter("correctAnswer").equals("answer4") && request.getParameter("answer4").isEmpty());

    }
}
