package com.app.dao;


import com.app.dao.connection.DAOConnection;
import com.app.exceptions.InteractionDBException;
import com.app.model.PassedTest;

public abstract class DAOFactory {

    public static DAOFactory getDAOFactory(TypeDB typeDB) {
        switch (typeDB) {
            case mySQL:
                return new MySQLFactory();
            default:
                throw new InteractionDBException("Could not find the corresponding factory instance");
        }
    }
    public abstract UserDAO getUserDAO();

    public abstract TestDAO getTestDAO();

    public abstract SubjectDAO getSubjectDAO();

    public abstract RoleDAO getRoleDAO();

    public abstract PassedTestDAO getPassedTestDAO();

    public abstract PassedQuestionDAO getPassedQuestionDAO();

    public abstract QuestionDAO getQuestionDAO();

    public enum TypeDB {
        mySQL
    }

}
