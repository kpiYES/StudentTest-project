package com.app.service.impl;

import com.app.dao.DAOFactory;
import com.app.dao.QuestionDAO;
import com.app.dao.connection.DAOConnection;
import com.app.model.Question;
import com.app.service.QuestionService;

import java.util.Set;

public class QuestionServiceImpl implements QuestionService {

    private DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.TypeDB.mySQL);

    private QuestionServiceImpl() {
    }

    public static QuestionServiceImpl getInstance() {
        return PassedTestServiceImplHolder.INSTANCE;
    }

    @Override
    public Long insert(Question question) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            QuestionDAO questionDAO = daoFactory.getQuestionDAO(daoConnection);
            return questionDAO.insert(question);
        }
    }

    @Override
    public void update(Question question) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            QuestionDAO questionDAO = daoFactory.getQuestionDAO(daoConnection);
            questionDAO.update(question);
        }
    }

    @Override
    public void delete(Question question) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            QuestionDAO questionDAO = daoFactory.getQuestionDAO(daoConnection);
            questionDAO.delete(question);
        }
    }

    @Override
    public Question findById(Long id) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            QuestionDAO questionDAO = daoFactory.getQuestionDAO(daoConnection);
            return questionDAO.findById(id);
        }
    }

    @Override
    public Set<Question> findAll() {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            QuestionDAO questionDAO = daoFactory.getQuestionDAO(daoConnection);
            return questionDAO.findAll();
        }
    }

    @Override
    public Set<Question> findAllBySubjectIdWithPagination(Long id, int limit, int offset) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            QuestionDAO questionDAO = daoFactory.getQuestionDAO(daoConnection);
            return questionDAO.findAllBySubjectIdWithPagination(id, limit, offset);
        }
    }

    @Override
    public Set<Question> findAllBySubjectId(Long id) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            QuestionDAO questionDAO = daoFactory.getQuestionDAO(daoConnection);
            return questionDAO.findAllBySubjectId(id);
        }
    }

    @Override
    public Set<Question> findAllByTestId(Long id) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            QuestionDAO questionDAO = daoFactory.getQuestionDAO(daoConnection);
            return questionDAO.findAllByTestId(id);
        }
    }

    @Override
    public Set<Question> findAllByTestIdWithPagination(Long id, int limit, int offset) {
        try (DAOConnection daoConnection = daoFactory.getConnection()) {
            QuestionDAO questionDAO = daoFactory.getQuestionDAO(daoConnection);
            return questionDAO.findAllByTestIdWithPagination(id, limit, offset);
        }
    }

    private static class PassedTestServiceImplHolder {
        private final static QuestionServiceImpl INSTANCE = new QuestionServiceImpl();
    }
}
