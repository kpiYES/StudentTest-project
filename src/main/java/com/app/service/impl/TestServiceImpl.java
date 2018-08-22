package com.app.service.impl;

import com.app.dao.DAOFactory;
import com.app.dao.TestDAO;
import com.app.model.Test;
import com.app.service.TestService;

import java.util.Set;

public class TestServiceImpl implements TestService {

    private DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.TypeDB.mySQL);
    private TestDAO testDAO = daoFactory.getTestDAO();

    @Override
    public Long insert(Test test) {
        return testDAO.insert(test);
    }

    @Override
    public void update(Test test) {
        testDAO.update(test);
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
    public Set<Test> findAll() {
        return testDAO.findAll();
    }

    @Override
    public Set<Test> findAllBySubjectId(Long id) {
        return testDAO.findAllBySubjectId( id);
    }

    public void connectTestAndQuestions(Test test){testDAO.connectTestAndQuestions(test);}
}
