package com.app.dao;

public abstract class DAOFactory {

    public static DAOFactory getDAOFactory(TypeDB typeDB) {
        switch (typeDB) {
            case mySQL:
                return new MySQLDAOFactory();
            default: return null;
            ////exception
        }
    }

    public abstract IUserDAO getUserDAO();

    public abstract IQuestionDAO getQuestionDAO();

    public enum TypeDB {
        mySQL
    }

}
