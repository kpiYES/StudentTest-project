package com.app.service.impl;

import com.app.dao.DAOFactory;
import com.app.dao.TestDAO;
import com.app.dao.connection.DAOConnection;
import com.app.model.Test;
import com.app.service.TestService;

import java.util.Set;

public class TestServiceImpl implements TestService {

    private DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.TypeDB.mySQL);

    private TestServiceImpl() {
    }

    public static TestServiceImpl getInstance() {
        return TestServiceImplHolder.INSTANCE;
    }

    @Override
    public Long insert(Test test) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            TestDAO testDAO = daoFactory.getTestDAO(daoConnection);
            return testDAO.insert(test);
        }
    }

    @Override
    public void update(Test test) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            TestDAO testDAO = daoFactory.getTestDAO(daoConnection);
            testDAO.update(test);
        }
    }

    @Override
    public void delete(Test test) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            TestDAO testDAO = daoFactory.getTestDAO(daoConnection);
            testDAO.delete(test);
        }
    }

    @Override
    public Test findById(Long id) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            TestDAO testDAO = daoFactory.getTestDAO(daoConnection);
            return testDAO.findById(id);
        }
    }

    @Override
    public Set<Test> findAll() {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            TestDAO testDAO = daoFactory.getTestDAO(daoConnection);
            return testDAO.findAll();
        }
    }

    @Override
    public Set<Test> findAllBySubjectId(Long id) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            TestDAO testDAO = daoFactory.getTestDAO(daoConnection);
            return testDAO.findAllBySubjectId(id);
        }
    }

    @Override
    public Set<Test> getFindAllByQuestionId(Long id) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            TestDAO testDAO = daoFactory.getTestDAO(daoConnection);
            return testDAO.findAllByQuestionId(id);
        }
    }

//    @Override
//    public Test findByIdWithQuestions(Long id) {
//        try (DAOConnection daoConnection = daoFactory.getConnection()) {
//            TestDAO testDAO = daoFactory.getTestDAO(daoConnection);
//            QuestionDAO questionDAO = daoFactory.getQuestionDAO(daoConnection);
//            daoConnection.startTransaction();
//
//            Test test = testDAO.findById(id);
//            Set<Question> questionSet = questionDAO.findAllByTestId(test.getId());
//            test.setQuestionSet(questionSet);
//            daoConnection.commit();
//
//            return test;
//        }
//    }


    private static class TestServiceImplHolder {
        private final static TestServiceImpl INSTANCE = new TestServiceImpl();
    }
}
