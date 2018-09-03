package com.app.service.impl;

import com.app.dao.DAOFactory;
import com.app.dao.TestDAO;
import com.app.model.Test;
import com.app.service.TestService;

import java.util.Set;

public class TestServiceImpl implements TestService {

    private TestDAO testDAO;

    private TestServiceImpl() {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.TypeDB.mySQL);
        testDAO = daoFactory.getTestDAO();
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
    public Set<Test> findAllBySubjectId(Long id) {
        return testDAO.findAllBySubjectId(id);
    }

    @Override
    public Set<Test> getFindAllByQuestionId(Long id) {
        return testDAO.findAllByQuestionId(id);
    }

    private static class TestServiceImplHolder {
        private final static TestServiceImpl INSTANCE = new TestServiceImpl();
    }
}
