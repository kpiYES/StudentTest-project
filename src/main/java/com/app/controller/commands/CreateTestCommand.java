package com.app.controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateTestCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

//        System.out.println("Create test");
//        TestService testService = new TestServiceImpl();
//        SubjectService subjectService = new SubjectServiceImpl();
//        QuestionService questionService = new QuestionServiceImpl();
//        Subject subject = subjectService.findByName((String) request.getSession().getAttribute("subject"));
//        Test test = new Test();
//        test.setSubject(subject);
//        test.setName((String) request.getSession().getAttribute("newTestName"));
//
//        Set<Long> questionsIdSet = (Set<Long>) request.getSession().getAttribute("chosenQuestionsSet");
//        Set<Question> questionSet = new HashSet<>();
//        for (Long id : questionsIdSet) {
//            Question question = questionService.findById(id);
//            questionSet.add(question);
//        }
//        test.setQuestionSet(questionSet);
//        testService.insert(test);
        return "admin.jsp";
    }
}
