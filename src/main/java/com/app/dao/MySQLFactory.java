package com.app.dao;

import com.app.dao.connection.DAOConnection;
import com.app.dao.connection.MySQLConnection;
import com.app.dao.mySQLImpl.*;
import com.app.exceptions.InteractionDBException;
import com.app.util.DataSource;

import java.sql.Connection;

public class MySQLFactory extends DAOFactory {


    private DataSource dataSource = DataSource.getInstance();

    public DAOConnection getConnection() {
        return new MySQLConnection(dataSource.getConnection());
    }

    @Override
    public UserDAO getUserDAO(DAOConnection connection) {
        return new UserDAOImpl(getSqlConnection(connection));
    }

    @Override
    public TestDAO getTestDAO(DAOConnection connection) {
        return new TestDAOImpl(getSqlConnection(connection));
    }

    @Override
    public SubjectDAO getSubjectDAO(DAOConnection connection) {
        return new SubjectDAOImpl(getSqlConnection(connection));
    }

    @Override
    public RoleDAO getRoleDAO(DAOConnection connection) {
        return new RoleDAOImpl(getSqlConnection(connection));
    }

    @Override
    public PassedTestDAO getPassedTestDAO(DAOConnection connection) {
        return new PassedTestDAOImpl(getSqlConnection(connection));
    }

    @Override
    public PassedQuestionDAO getPassedQuestionDAO(DAOConnection connection) {
        return new PassedQuestionDAOImpl(getSqlConnection(connection));
    }

    @Override
    public QuestionDAO getQuestionDAO(DAOConnection connection) {
        return new QuestionDAOImpl(getSqlConnection(connection));
    }

    private Connection getSqlConnection(DAOConnection connection) {
        checkDaoConnection(connection);
        return (Connection) connection.getNativeConnection();
    }

    private void checkDaoConnection(DAOConnection connection) {
        if(connection == null || connection.getNativeConnection() == null) {
            throw new InteractionDBException("sdfs");
        }
        if(! (connection instanceof MySQLConnection)) {
            throw new InteractionDBException("sdafasf");
        }
    }

}
