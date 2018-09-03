package com.app.controller.commands.student;

import com.app.controller.commands.Command;
import com.app.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PassTestToPassTestFragmentCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Set<Question> questionSet = ((Test) request.getSession().getAttribute("test")).getQuestionSet();
        ArrayList<Question> questionsList = new ArrayList<>(questionSet);

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

        long currentQuestionIndex = 0;

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
