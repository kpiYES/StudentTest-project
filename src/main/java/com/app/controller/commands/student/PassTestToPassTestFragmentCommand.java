package com.app.controller.commands.student;

import com.app.controller.commands.Command;
import com.app.dto.DTOHandler;
import com.app.dto.UserDTO;
import com.app.model.*;
import com.app.service.PassedQuestionService;
import com.app.service.PassedTestService;
import com.app.service.QuestionService;
import com.app.service.ServiceFactory;
import com.app.util.PassedTestMark;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PassTestToPassTestFragmentCommand implements Command {

    private PassedTestService passedTestService = ServiceFactory.getPassedTestService();
    private PassedQuestionService passedQuestionService = ServiceFactory.getPassedQuestionService();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {


        Set<Question> questionSet = ((Test) request.getSession().getAttribute("test")).getQuestionSet();
        ArrayList<Question> questionsList = new ArrayList<>(questionSet);

        Map<Long, String> answerMap = new HashMap<>(questionsList.size());

        if ((request.getSession().getAttribute("answerMap")) != null && !((Map<Long, String>) (request.getSession().getAttribute("answerMap"))).isEmpty()) {
            answerMap.putAll((Map<Long, String>) request.getSession().getAttribute("answerMap"));
        }

        if (request.getParameterMap().containsKey("usersAnswer")) {
            String usersAnswer = request.getParameter("usersAnswer");

            long index = (Long) request.getSession().getAttribute("currentQuestionIndex");
            Question question = questionsList.get((int) index);
            answerMap.put(question.getId(), usersAnswer);
        }


        if (request.getParameter("action") != null) {
            for (Question question : questionsList) {
                if (!answerMap.containsKey(question.getId())) {
                    answerMap.put(question.getId(), null);
                }
            }

            Test test = (Test) request.getSession().getAttribute("test");
            UserDTO userDTO = (UserDTO) request.getSession().getAttribute("currentUser");
            User user = DTOHandler.constructUser(userDTO);
            Integer mark = PassedTestMark.toRateMark(answerMap);
            PassedTest passedTest = new PassedTest();
            passedTest.setTest(test);
            passedTest.setUser(user);
            passedTest.setMark(mark);
            Set<PassedQuestion> passedQuestionSet = passedQuestionService.constructFromUsersAnswersMap(answerMap);
            passedTest.setPassedQuestionSet(passedQuestionSet);
            passedTestService.insert(passedTest);
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

//    private String finishPassing(HttpServletRequest request, HttpServletResponse response) {
//
//
//    }

}
