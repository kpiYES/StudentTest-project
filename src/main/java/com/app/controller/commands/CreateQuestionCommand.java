package com.app.controller.commands;

import com.app.model.Question;
import com.app.model.Subject;
import com.app.service.QuestionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateQuestionCommand implements Command {
    private QuestionService questionService;

    public CreateQuestionCommand(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {


        if (!validateCorrectAnswer(request, response)) {
            request.setAttribute("errorMsg", "The correct answer must be one of the answers");
            request.setAttribute("pageFragment", "showListOfSubjectsQuestionsFragment.jsp");
            request.setAttribute("subPageFragment", "showListOfQuestionsQuestionsFragment.jsp");
            return "admin.jsp";
        }

        Question question = new Question();
        question.setSubject((Subject) request.getSession().getAttribute("subject"));
        question.setQuery(request.getParameter("query"));
        question.setAnswer1(request.getParameter("answer1"));
        question.setAnswer2(request.getParameter("answer2"));

//        if (request.getParameterMap().containsKey("answer3")) {
        question.setAnswer3(request.getParameter("answer3"));
//        }

//        if (request.getParameterMap().containsKey("answer4")) {
        question.setAnswer4(request.getParameter("answer4"));
//        }
        question.setCorrectAnswer(request.getParameter("correctAnswer"));

        questionService.insert(question);
        request.setAttribute("msg", "Question has successfully created");
        request.setAttribute("pageFragment", "showListOfSubjectsQuestionsFragment.jsp");
        request.setAttribute("subPageFragment", "showListOfQuestionsQuestionsFragment.jsp");

        return "admin.jsp";
    }

    private boolean validateCorrectAnswer(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("answer1").equals(request.getParameter("correctAnswer")) ||
                request.getParameter("answer2").equals(request.getParameter("correctAnswer")) ||
                request.getParameter("answer3").equals(request.getParameter("correctAnswer")) ||
                request.getParameter("answer4").equals(request.getParameter("correctAnswer"))) {
            return true;
        }
        return false;
    }


}
