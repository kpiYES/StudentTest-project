package com.app.service.impl;

import com.app.dao.DAOFactory;
import com.app.dao.TestDAO;
import com.app.model.Test;
import com.app.service.TestService;

public class TestServiceImpl implements TestService {

    private DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.TypeDB.mySQL);
    private TestDAO testDAO = daoFactory.getTestDAO();

    @Override
    public Long insert(Test test) {
        return null;
    }

    @Override
    public void update(Test test) {

    }

    @Override
    public void delete(Test test) {

    }

    @Override
    public Test findById(Long id) {
        return testDAO.findById(id);
    }
}
