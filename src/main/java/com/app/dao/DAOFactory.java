package com.app.dao;


public abstract class DAOFactory {

    public static DAOFactory getDAOFactory(TypeDB typeDB) {
        switch (typeDB) {
            case mySQL:
                return new MySQLDAOFactory();
            default:
                return null;
            ////exception
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
