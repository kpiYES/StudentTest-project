package com.app.dao;

import com.app.dao.connection.DAOConnection;
import com.app.dao.connection.MySQLConnection;
import com.app.dao.mySQLImpl.*;
import com.app.exceptions.InteractionDBException;
import com.app.dao.connection.ConnectionSource;

import java.sql.Connection;

public class MySQLFactory extends DAOFactory {


    private ConnectionSource connectionSource = ConnectionSource.getInstance();


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
