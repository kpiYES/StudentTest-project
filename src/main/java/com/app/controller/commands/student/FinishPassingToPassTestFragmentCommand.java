package com.app.controller.commands.student;

import com.app.controller.commands.Command;
import com.app.dto.DTOHandler;
import com.app.dto.UserDTO;
import com.app.model.*;
import com.app.service.PassedQuestionService;
import com.app.service.PassedTestService;
import com.app.service.ServiceFactory;
import com.app.service.util.TestMarkRating;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class FinishPassingToPassTestFragmentCommand implements Command {

    private PassedTestService passedTestService = ServiceFactory.getPassedTestService();
    private PassedQuestionService passedQuestionService = ServiceFactory.getPassedQuestionService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

//посмотреть, может лишнее что, удалить может пару строк
        Set<Question> questionSet = ((Test) request.getSession().getAttribute("test")).getQuestionSet();
        ArrayList<Question> questionsList = new ArrayList<>(questionSet);
        Map<Long, String> answerMap = (Map<Long, String>) request.getSession().getAttribute("answerMap");


        if (request.getParameterMap().containsKey("usersAnswer")) {
            long index = (Long) request.getSession().getAttribute("currentQuestionIndex");
            Question question = questionsList.get((int) index);
            answerMap.put(question.getId(), request.getParameter("usersAnswer"));
        }

        for (Question question : questionsList) {
            if (!answerMap.containsKey(question.getId())) {
                answerMap.put(question.getId(), null);
            }
        }

        Test test = (Test) request.getSession().getAttribute("test");
        UserDTO userDTO = (UserDTO) request.getSession().getAttribute("currentUser");
        User user = DTOHandler.constructUser(userDTO);
        Integer mark = TestMarkRating.toRateMark(answerMap);
        PassedTest passedTest = new PassedTest();
        passedTest.setTest(test);
        passedTest.setUser(user);
        passedTest.setMark(mark);
        Set<PassedQuestion> passedQuestionSet = passedQuestionService.constructFromUsersAnswersMap(answerMap);
        passedTest.setPassedQuestionSet(passedQuestionSet);
        passedTestService.insertWithPassedQuestions(passedTest);
        passedTestService.sendResult(passedTest);

        request.getSession().removeAttribute("test");
        request.getSession().setAttribute("passedTest", passedTest);
        request.setAttribute("pageFragment", "finishPassingToPassTestFragment.jsp");
        return "student.jsp";
    }
}