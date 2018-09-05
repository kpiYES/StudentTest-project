package com.app.dao;

import com.app.exceptions.InteractionDBException;

public abstract class DAOFactory {

    /**
     * Factory that creates DAO entities
     *
     * @author Markovych Andrii
     */
    public static DAOFactory getDAOFactory(TypeDB typeDB) {
        switch (typeDB) {
            case mySQL:
                return new MySQLFactory();
            default:
                throw new InteractionDBException("Could not find the corresponding factory instance");
        }
    }

    /**
     * @return instance of concrete UserDAO implementation.
     */
    public abstract UserDAO getUserDAO();

    /**
     * @return instance of concrete TestDAO implementation.
     */
    public abstract TestDAO getTestDAO();

    /**
     * @return instance of concrete SubjectDAO implementation.
     */
    public abstract SubjectDAO getSubjectDAO();

    /**
     * @return instance of concrete RoleDAO implementation.
     */
    public abstract RoleDAO getRoleDAO();

    /**
     * @return instance of concrete PassedTestDAO implementation.
     */
    public abstract PassedTestDAO getPassedTestDAO();

    /**
     * @return instance of concrete PassedQuestionDAO implementation.
     */
    public abstract PassedQuestionDAO getPassedQuestionDAO();

    /**
     * @return instance of concrete QuestionDAO implementation.
     */
    public abstract QuestionDAO getQuestionDAO();

    public enum TypeDB {
        mySQL
    }

}
