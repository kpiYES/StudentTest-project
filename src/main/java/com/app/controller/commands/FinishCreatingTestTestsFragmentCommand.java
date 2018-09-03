package com.app.controller.commands;

import com.app.model.Question;
import com.app.model.Subject;
import com.app.model.Test;
import com.app.service.QuestionService;
import com.app.service.ServiceFactory;
import com.app.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

public class FinishCreatingTestTestsFragmentCommand implements Command  {

    private TestService testService = ServiceFactory.getTestService();
    private QuestionService questionService = ServiceFactory.getQuestionService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

          Set<Long>   chosenQuestionsSet = (Set<Long>) request.getSession().getAttribute("chosenQuestionsSet");

        Subject subject = (Subject) request.getSession().getAttribute("subject");
        Test test = new Test();
        test.setSubject(subject);
        test.setName((String) request.getSession().getAttribute("newTestName"));
        Set<Question> questionSet = new HashSet<>();
        for (Long id : chosenQuestionsSet) {
            Question question = questionService.findById(id);
            questionSet.add(question);
        }
        test.setQuestionSet(questionSet);
        testService.insert(test);
        request.setAttribute("msg","Test was successfully created");
        return "admin.jsp";
    }
}
