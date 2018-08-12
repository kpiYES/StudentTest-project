package com.app.dao;

import com.app.dao.mySQLImpl.QuestionDAOImpl;
import com.app.dao.mySQLImpl.UserDAOImpl;

public class MySQLDAOFactory extends DAOFactory {
    @Override
    public IUserDAO getUserDAO() {
        return new UserDAOImpl();
    }

    @Override
    public IQuestionDAO getQuestionDAO() {
        return new QuestionDAOImpl();
    }

}
