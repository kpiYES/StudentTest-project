package com.app.controller.commands.student;

import com.app.controller.commands.Command;
import com.app.dto.UserDTO;
import com.app.model.PassedTest;
import com.app.model.Question;
import com.app.model.Test;
import com.app.service.PassedTestService;
import com.app.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class PassTestToPassTestFragmentCommand implements Command {

    private PassedTestService passedTestService = ServiceFactory.getPassedTestService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        long currentQuestionIndex = 0;

        Set<Question> questionSet = ((Test) request.getSession().getAttribute("test")).getQuestionSet();
        List<Question> questionsList = new ArrayList<>(questionSet);

        Map<Long, String> answerMap = new HashMap<>(questionsList.size());
        if ((request.getSession().getAttribute("answerMap")) != null) {
            answerMap.putAll((Map<Long, String>) request.getSession().getAttribute("answerMap"));
        }
        if (request.getParameterMap().containsKey("usersAnswer")) {
            String usersAnswer = request.getParameter("usersAnswer");
            long index = (Long) request.getSession().getAttribute("currentQuestionIndex");
            Question question = questionsList.get((int) index);
            answerMap.put(question.getId(), usersAnswer);
        }

        if ("finishPassingTest".equals(request.getParameter("action"))) {
            PassedTest passedTest = passedTestService.create(questionsList, (Test) request.getSession().getAttribute("test"),
                    (UserDTO) request.getSession().getAttribute("currentUser"), answerMap);
            request.getSession().removeAttribute("test");
            request.getSession().setAttribute("passedTest", passedTest);
            request.setAttribute("pageFragment", "finishPassingToPassTestFragment.jsp");
            return "student.jsp";
        }

        if (request.getParameter("currentQuestionIndex") != null) {
            currentQuestionIndex = Long.parseLong(request.getParameter("currentQuestionIndex"));
        }
        request.getSession().setAttribute("answerMap", answerMap);
        request.getSession().setAttribute("currentQuestionIndex", currentQuestionIndex);
        request.setAttribute("questionsList", questionsList);
        request.setAttribute("pageFragment", "passTestToPassTestFragment.jsp");
        return "student.jsp";
    }
}
