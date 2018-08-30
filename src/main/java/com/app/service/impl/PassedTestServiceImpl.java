package com.app.service.impl;

import com.app.dao.DAOFactory;
import com.app.dao.PassedQuestionDAO;
import com.app.dao.PassedTestDAO;
import com.app.dao.connection.DAOConnection;
import com.app.dto.UserDTO;
import com.app.model.*;
import com.app.service.PassedTestService;
import com.app.service.QuestionService;
import com.app.service.ServiceFactory;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PassedTestServiceImpl implements PassedTestService {

    private final DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.TypeDB.mySQL);

    private PassedTestServiceImpl() {
    }

    public static PassedTestServiceImpl getInstance() {
        return PassedTestServiceImplHolder.INSTANCE;
    }

    @Override
    public Long insert(PassedTest passedTest) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            PassedTestDAO passedTestDAO = daoFactory.getPassedTestDAO(daoConnection);
            return passedTestDAO.insert(passedTest);
        }
    }

    @Override
    public void update(PassedTest passedTest) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            PassedTestDAO passedTestDAO = daoFactory.getPassedTestDAO(daoConnection);
            passedTestDAO.update(passedTest);
        }
    }

    @Override
    public void delete(PassedTest passedTest) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            PassedTestDAO passedTestDAO = daoFactory.getPassedTestDAO(daoConnection);
            passedTestDAO.delete(passedTest);
        }
    }

    @Override
    public PassedTest findById(Long id) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            PassedTestDAO passedTestDAO = daoFactory.getPassedTestDAO(daoConnection);
            return passedTestDAO.findById(id);
        }
    }

    @Override
    public Set<PassedTest> findAll() {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            PassedTestDAO passedTestDAO = daoFactory.getPassedTestDAO(daoConnection);
            return passedTestDAO.findAll();
        }
    }

    @Override
    public Set<PassedTest> findByUserId(Long id) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            PassedTestDAO passedTestDAO = daoFactory.getPassedTestDAO(daoConnection);
            return passedTestDAO.findByUserId(id);
        }
    }

    @Override
    public Long insertWithPassedQuestions(PassedTest passedTest) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            PassedTestDAO passedTestDAO = daoFactory.getPassedTestDAO(daoConnection);
            PassedQuestionDAO passedQuestionDAO = daoFactory.getPassedQuestionDAO(daoConnection);
            passedTestDAO.insert(passedTest);

            return passedTestDAO.insert(passedTest);
        }
    }

    private static class PassedTestServiceImplHolder {
        private final static PassedTestServiceImpl INSTANCE = new PassedTestServiceImpl();
    }
}
