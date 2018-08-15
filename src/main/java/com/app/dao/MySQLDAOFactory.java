package com.app.dao;

import com.app.dao.mySQLImpl.*;

public class MySQLDAOFactory extends DAOFactory {
    @Override
    public UserDAO getUserDAO() {
        return new UserDAOImpl();
    }

    @Override
    public TestDAO getTestDAO() {
        return new TestDAOImpl();
    }

    @Override
    public SubjectDAO getSubjectDAO() {
        return new SubjectDAOImpl();
    }

    @Override
    public RoleDAO getRoleDAO() {
        return new RoleDAOImpl();
    }

    @Override
    public PassedTestDAO getPassedTestDAO() {
        return new PassedTestDAOImpl();
    }

    @Override
    public PassedQuestionDAO getPassedQuestionDAO() {
        return new PassedQuestionDAOImpl();
    }

    @Override
    public QuestionDAO getQuestionDAO() {
        return new QuestionDAOImpl();
    }

}
