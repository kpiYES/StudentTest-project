package com.app.service.impl;

import com.app.dao.DAOFactory;
import com.app.dao.PassedTestDAO;
import com.app.model.PassedTest;
import com.app.service.PassedTestService;

import java.util.Set;

public class PassedTestServiceImpl implements PassedTestService {

    private DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.TypeDB.mySQL);
    private PassedTestDAO passedTestDAO = daoFactory.getPassedTestDAO();

    @Override
    public Long insert(PassedTest passedTest) {
        return passedTestDAO.insert(passedTest);
    }

    @Override
    public void update(PassedTest passedTest) {
        passedTestDAO.update(passedTest);
    }

    @Override
    public void delete(PassedTest passedTest) {
        passedTestDAO.delete(passedTest);
    }

    @Override
    public PassedTest findById(Long id) {
        return passedTestDAO.findById(id);
    }

    @Override
    public Set<PassedTest> findAll() {
        return passedTestDAO.findAll();
    }

    @Override
    public Set<PassedTest> findByUserId(Long id) {
        return passedTestDAO.findByUserId(id);
    }
}
