package com.app.controller;

import com.app.model.Question;
import com.app.model.Subject;
import com.app.model.Test;
import com.app.service.QuestionService;
import com.app.service.SubjectService;
import com.app.service.TestService;
import com.app.service.impl.QuestionServiceImpl;
import com.app.service.impl.SubjectServiceImpl;
import com.app.service.impl.TestServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ToCreateTestFragmentCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Set<Long> chosenQuestionsSet = new HashSet<>();
        if ((request.getSession().getAttribute("chosenQuestionsSet"))!=null && !((Set<Long>)(request.getSession().getAttribute("chosenQuestionsSet"))).isEmpty()) {
            System.out.println("ses atr = null");
//            chosenQuestionsSet.addAll((Set<String>) request.getSession().getAttribute("chosenQuestionsSet"));
            for(Long id :((Set<Long>) request.getSession().getAttribute("chosenQuestionsSet"))){
                chosenQuestionsSet.add(id);
            }
        }
        int page = 1;
        int recordsPerPage = 3;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (request.getParameterMap().containsKey("question")) {
            System.out.println("yeees");
            String[] chosenQuestions = request.getParameterValues("question");
//            chosenQuestionsSet.addAll(Arrays.asList(chosenQuestions));
            for(String id : chosenQuestions) {
                chosenQuestionsSet.add(Long.parseLong(id));
            }
            System.out.println(Arrays.toString(chosenQuestionsSet.toArray()));
        }

        if (request.getParameterMap().containsKey("newTestName")) {
            request.getSession().setAttribute("newTestName", request.getParameter("newTestName"));
        }

        if(request.getParameter("action") != null){
            System.out.println("Create test");
            TestService testService = new TestServiceImpl();
            QuestionService questionService = new QuestionServiceImpl();
            Subject subject = (Subject)request.getSession().getAttribute("subject");
            Test test = new Test();
            test.setSubject(subject);
            test.setName((String) request.getSession().getAttribute("newTestName"));

//            Set<Long> questionsIdSet = (Set<Long>) request.getSession().getAttribute("chosenQuestionsSet");
            Set<Question> questionSet = new HashSet<>();
            for (Long id : chosenQuestionsSet) {
                Question question = questionService.findById(id);
                questionSet.add(question);
            }

            test.setQuestionSet(questionSet);
            test.setId(testService.insert(test));
            testService.connectTestAndQuestions(test);
            return "admin.jsp";
        }
        QuestionService questionService = new QuestionServiceImpl();
        Set<Question> questionSetWithPagination = questionService.findAllBySubjectIdWithPagination(((Subject) request.getSession().getAttribute("subject")).getId(), recordsPerPage, (page - 1) * recordsPerPage);
        Set<Question> questionSet = questionService.findAllBySubjectId(((Subject) request.getSession().getAttribute("subject")).getId());
        long numberOfRecords = questionSet.size();
        long numberOfPages = (long) Math.ceil(numberOfRecords * 1.0 / recordsPerPage);
        request.getSession().setAttribute("chosenQuestionsSet", chosenQuestionsSet);
        request.setAttribute("questionSet", questionSetWithPagination);
        request.setAttribute("currentPage", page);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("pageFragment", "showListOfSubjectsFragment.jsp");
        request.setAttribute("subPageFragment", "showListOfTestsFragment.jsp");
        request.setAttribute("subSubPageFragment", "createTestFragment.jsp");
        return "showListOfTestsFragment.jsp";
    }
}
