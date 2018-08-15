package com.app.service.impl;

import com.app.dao.DAOFactory;
import com.app.dao.PassedTestDAO;
import com.app.model.PassedTest;
import com.app.service.PassedTestService;

public class PassedTestServiceImpl implements PassedTestService {


   private DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.TypeDB.mySQL);
   private PassedTestDAO passedTestDAO = daoFactory.getPassedTestDAO();

    @Override
    public Long insert(PassedTest passedTest) {
        return passedTestDAO.insert(passedTest);
    }

    @Override
    public void update(PassedTest passedTest) {

    }

    @Override
    public void delete(PassedTest passedTest) {

    }

    @Override
    public PassedTest getById(Long id) {
        return null;
    }
}
