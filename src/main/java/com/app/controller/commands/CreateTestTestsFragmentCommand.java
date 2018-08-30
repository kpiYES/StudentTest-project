package com.app.controller.commands;

import com.app.model.Question;
import com.app.model.Subject;
import com.app.model.Test;
import com.app.service.QuestionService;
import com.app.service.ServiceFactory;
import com.app.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CreateTestTestsFragmentCommand implements Command {

    private TestService testService = ServiceFactory.getTestService();
    private QuestionService questionService = ServiceFactory.getQuestionService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int page = 1;
        int recordsPerPage = 3;
        Set<Long> chosenQuestionsSet = new HashSet<>();

        if ((request.getSession().getAttribute("chosenQuestionsSet")) != null && !((Set<Long>) (request.getSession().getAttribute("chosenQuestionsSet"))).isEmpty()) {
            chosenQuestionsSet.addAll((Set<Long>) request.getSession().getAttribute("chosenQuestionsSet"));
        }

        if ((request.getSession().getAttribute("questionSet")) != null && !((Set<Question>) (request.getSession().getAttribute("questionSet"))).isEmpty()) {
            for (Question question : ((Set<Question>) request.getSession().getAttribute("questionSet"))) {
                if (chosenQuestionsSet.contains(question.getId())) {
                    if (request.getParameterMap().containsKey("question")) {
//                        for (String questionId : request.getParameterValues("question")) {
//                            if (!chosenQuestionsSet.contains(Long.parseLong(questionId))) {
                            if (!Arrays.asList(request.getParameterValues("question")).contains(question.getId().toString())) {
                                chosenQuestionsSet.remove(question.getId());
                            }
                    } else {
                        chosenQuestionsSet.remove(question.getId());
                    }
                }
            }}



        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        if (request.getParameterMap().containsKey("question")) {
            String[] chosenQuestions = request.getParameterValues("question");
            for (String id : chosenQuestions) {
                chosenQuestionsSet.add(Long.parseLong(id));
            }
        }

        if (request.getParameterMap().containsKey("newTestName")) {
            request.getSession().setAttribute("newTestName", request.getParameter("newTestName"));
        }

        if (request.getParameterMap().containsKey("newTimeLimit")) {
            request.getSession().setAttribute("newTimeLimit", request.getParameter("newTimeLimit"));
        }

        if (request.getParameter("action") != null) {
            Subject subject = (Subject) request.getSession().getAttribute("subject");
            Test test = new Test();
            test.setSubject(subject);
            test.setName((String) request.getSession().getAttribute("newTestName"));
            test.setTimeLimit(Integer.parseInt((String)request.getSession().getAttribute("newTimeLimit")));
            Set<Question> questionSet = new HashSet<>();
            for (Long id : chosenQuestionsSet) {
                Question question = questionService.findById(id);
                questionSet.add(question);
            }
            test.setQuestionSet(questionSet);
            testService.insert(test);
            String testName = (String) request.getSession().getAttribute("newTestName");
            request.setAttribute("msg", testName + " was successfully created");
            return "admin.jsp";
        }

        Set<Question> questionSetWithPagination = questionService.findAllBySubjectIdWithPagination(((Subject) request.getSession().getAttribute("subject")).getId(), recordsPerPage, (page - 1) * recordsPerPage);
        Set<Question> questionSet = questionService.findAllBySubjectId(((Subject) request.getSession().getAttribute("subject")).getId());
        long numberOfRecords = questionSet.size();
        long numberOfPages = (long) Math.ceil(numberOfRecords * 1.0 / recordsPerPage);
        request.getSession().setAttribute("chosenQuestionsSet", chosenQuestionsSet);
        request.getSession().setAttribute("questionSet", questionSetWithPagination);
        request.setAttribute("currentPage", page);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("pageFragment", "showListOfSubjectsTestsFragment.jsp");
        request.setAttribute("subPageFragment", "showListOfTestsTestsFragment.jsp");
        request.setAttribute("subSubPageFragment", "createTestFragment.jsp");
        return "showListOfTestsTestsFragment.jsp";
    }
}
