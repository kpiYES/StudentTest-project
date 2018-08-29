package com.app.dao;


import com.app.dao.connection.DAOConnection;
import com.app.exceptions.InteractionDBException;

public abstract class DAOFactory {

    public static DAOFactory getDAOFactory(TypeDB typeDB) {
        switch (typeDB) {
            case mySQL:
                return new MySQLFactory();
            default:
                throw new InteractionDBException("Could not find the corresponding factory instance");
            ////exception
        }
    }
    public abstract DAOConnection getConnection();

    public abstract UserDAO getUserDAO(DAOConnection connection);

    public abstract TestDAO getTestDAO(DAOConnection connection);

    public abstract SubjectDAO getSubjectDAO(DAOConnection connection);

    public abstract RoleDAO getRoleDAO(DAOConnection connection);

    public abstract PassedTestDAO getPassedTestDAO(DAOConnection connection);

    public abstract PassedQuestionDAO getPassedQuestionDAO(DAOConnection connection);

    public abstract QuestionDAO getQuestionDAO(DAOConnection connection);

    public enum TypeDB {
        mySQL
    }

}
