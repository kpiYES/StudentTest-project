package com.app.dao;

import com.app.dao.mySQLImpl.*;

public class MySQLFactory extends DAOFactory {

    @Override
    public UserDAO getUserDAO() {
        return UserDAOMySQLImpl.getInstance();
    }

    @Override
    public TestDAO getTestDAO() {
        return TestDAOMySQLImpl.getInstance();
    }

    @Override
    public SubjectDAO getSubjectDAO() {
        return SubjectDAOMySQLImpl.getInstance();
    }

    @Override
    public RoleDAO getRoleDAO() {
        return RoleDAOMySQLImpl.getInstance();
    }

    @Override
    public PassedTestDAO getPassedTestDAO() {
        return PassedTestDAOMySQLImpl.getInstance();
    }

    @Override
    public PassedQuestionDAO getPassedQuestionDAO() {
        return PassedQuestionDAOMySQLImpl.getInstance();
    }

    @Override
    public QuestionDAO getQuestionDAO() {
        return QuestionDAOMySQLImpl.getInstance();
    }
}
