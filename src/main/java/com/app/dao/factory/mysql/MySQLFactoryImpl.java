package com.app.dao.factory.mysql;

import com.app.dao.PassedQuestionDAO;
import com.app.dao.PassedTestDAO;
import com.app.dao.QuestionDAO;
import com.app.dao.RoleDAO;
import com.app.dao.SubjectDAO;
import com.app.dao.TestDAO;
import com.app.dao.UserDAO;
import com.app.dao.connection.DAOConnection;
import com.app.dao.connection.mysql.MySQLConnectionImpl;
import com.app.dao.factory.DAOFactory;
import com.app.dao.mysql.*;
import com.app.exceptions.InteractionDBException;
import com.app.util.DataSource;

import java.sql.Connection;

public class MySQLFactoryImpl extends DAOFactory {

    private DataSource dataSource = DataSource.getInstance();

    public DAOConnection getConnection() {
        return new MySQLConnectionImpl(dataSource.getConnection());
    }

    @Override
    public UserDAO getUserDAO(DAOConnection connection) {
        return new MySQLUserDAOImpl(getSqlConnection(connection));
    }

    @Override
    public TestDAO getTestDAO(DAOConnection connection) {
        return new MySQLTestDAOImpl(getSqlConnection(connection));
    }

    @Override
    public SubjectDAO getSubjectDAO(DAOConnection connection) {
        return new MySQLSubjectDAOImpl(getSqlConnection(connection));
    }

    @Override
    public RoleDAO getRoleDAO(DAOConnection connection) {
        return new MySQLRoleDAOImpl(getSqlConnection(connection));
    }

    @Override
    public PassedTestDAO getPassedTestDAO(DAOConnection connection) {
        return new MySQLPassedTestDAOImpl(getSqlConnection(connection));
    }

    @Override
    public PassedQuestionDAO getPassedQuestionDAO(DAOConnection connection) {
        return new MySQLPassedQuestionDAOImpl(getSqlConnection(connection));
    }

    @Override
    public QuestionDAO getQuestionDAO(DAOConnection connection) {
        return new MySQLQuestionDAOImpl(getSqlConnection(connection));
    }

    private Connection getSqlConnection(DAOConnection connection) {
        checkDaoConnection(connection);
        return (Connection) connection.getNativeConnection();
    }

    private void checkDaoConnection(DAOConnection connection) {
        if(connection == null || connection.getNativeConnection() == null) {
            throw new InteractionDBException("sdfs");
        }
        if(! (connection instanceof MySQLConnectionImpl)) {
            throw new InteractionDBException("sdafasf");
        }
    }

}
