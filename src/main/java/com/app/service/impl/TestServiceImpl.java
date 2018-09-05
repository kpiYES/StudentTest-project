package com.app.service.impl;

import com.app.dao.DAOFactory;
import com.app.dao.TestDAO;
import com.app.model.Question;
import com.app.model.Subject;
import com.app.model.Test;
import com.app.service.QuestionService;
import com.app.service.ServiceFactory;
import com.app.service.TestService;

import java.util.HashSet;
import java.util.Set;

public class TestServiceImpl implements TestService {

    private TestDAO testDAO;
    private QuestionService questionService;

    private TestServiceImpl() {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.TypeDB.mySQL);
        testDAO = daoFactory.getTestDAO();
        questionService = ServiceFactory.getQuestionService();
    }

    public static TestServiceImpl getInstance() {
        return TestServiceImplHolder.INSTANCE;
    }

    @Override
    public Long insert(Test test) {
        return testDAO.insert(test);
    }

    @Override
    public void delete(Test test) {
        testDAO.delete(test);
    }

    @Override
    public Test findById(Long id) {
        return testDAO.findById(id);
    }

    @Override
    public Test create(Set<Long> chosenQuestionsIdSet, Subject subject, String testName) {
        Test test = new Test();
        test.setSubject(subject);
        test.setName(testName);
        Set<Question> questionSet = new HashSet<>();
        for (Long id : chosenQuestionsIdSet) {
            Question question = questionService.findById(id);
            questionSet.add(question);
        }
        test.setQuestionSet(questionSet);
        Long id = insert(test);
        test.setId(id);
        return test;
    }

    @Override
    public Test findByIdWithQuestions(Long id) {
        Test test = findById(id);
        Set<Question> questionSet = questionService.findAllByTestId(test.getId());
        test.setQuestionSet(questionSet);
        return test;
    }

    @Override
    public Set<Test> findAllBySubjectId(Long id) {
        return testDAO.findAllBySubjectId(id);
    }

    @Override
    public Set<Test> findAllByQuestionId(Long id) {
        return testDAO.findAllByQuestionId(id);
    }

    private static class TestServiceImplHolder {
        private final static TestServiceImpl INSTANCE = new TestServiceImpl();
    }
}
