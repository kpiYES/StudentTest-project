package com.app.service.impl;

import com.app.dao.PassedQuestionDAO;
import com.app.dao.connection.DAOConnection;
import com.app.dao.factory.DAOFactory;
import com.app.model.PassedQuestion;
import com.app.model.Question;
import com.app.service.PassedQuestionService;
import com.app.service.QuestionService;
import com.app.service.factory.ServiceFactory;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PassedQuestionServiceImpl implements PassedQuestionService {

    private final DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.TypeDB.mySQL);

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private QuestionService questionService;

    private PassedQuestionServiceImpl() {
        questionService = serviceFactory.getQuestionService();
    }

    //IMPL?
    public static PassedQuestionServiceImpl getInstance() {
        return PassedQuestionServiceImplHolder.INSTANCE;
    }

    @Override
    public Long insert(PassedQuestion passedQuestion) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            PassedQuestionDAO passedQuestionDAO = daoFactory.getPassedQuestionDAO(daoConnection);
            return passedQuestionDAO.insert(passedQuestion);
        }
    }


    @Override
    public void update(PassedQuestion passedQuestion) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            PassedQuestionDAO passedQuestionDAO = daoFactory.getPassedQuestionDAO(daoConnection);
            passedQuestionDAO.update(passedQuestion);
        }
    }


    @Override
    public void delete(PassedQuestion passedQuestion) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            PassedQuestionDAO passedQuestionDAO = daoFactory.getPassedQuestionDAO(daoConnection);
            passedQuestionDAO.delete(passedQuestion);
        }
    }

    @Override
    public PassedQuestion findById(Long id) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            PassedQuestionDAO passedQuestionDAO = daoFactory.getPassedQuestionDAO(daoConnection);
            return passedQuestionDAO.findById(id);
        }
    }


    @Override
    public Set<PassedQuestion> findAll() {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            PassedQuestionDAO passedQuestionDAO = daoFactory.getPassedQuestionDAO(daoConnection);
            return passedQuestionDAO.findAll();
        }
    }

    @Override
    public Set<PassedQuestion> constructFromUsersAnswersMap(Map<Long, String> usersAnswersMap) {
        Set<PassedQuestion> passedQuestionSet = new HashSet<>(usersAnswersMap.size());
        for (Map.Entry<Long, String> entry : usersAnswersMap.entrySet()) {
            Question question = questionService.findById(entry.getKey());
            PassedQuestion passedQuestion = new PassedQuestion();
            passedQuestion.setQuestion(question);
            passedQuestion.setUserAnswer(entry.getValue());
        }
        return passedQuestionSet;
    }

    //IMPL?
    private static class PassedQuestionServiceImplHolder {
        private final static PassedQuestionServiceImpl INSTANCE = new PassedQuestionServiceImpl();
    }
}
