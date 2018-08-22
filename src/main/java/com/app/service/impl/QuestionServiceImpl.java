package com.app.service.impl;

import com.app.dao.DAOFactory;
import com.app.dao.QuestionDAO;
import com.app.model.Question;
import com.app.service.QuestionService;

import java.util.Set;

public class QuestionServiceImpl implements QuestionService {

    private DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.TypeDB.mySQL);
    private QuestionDAO questionDAO = daoFactory.getQuestionDAO();

    @Override
    public Long insert(Question question) {
        return questionDAO.insert(question);
    }

    @Override
    public void update(Question question) {
        questionDAO.insert(question);
    }

    @Override
    public void delete(Question question) {
        questionDAO.delete(question);
    }

    @Override
    public Question findById(Long question_id) {
        return questionDAO.findById(question_id);
    }

    @Override
    public Set<Question> findAll() {
        return questionDAO.findAll();
    }

    @Override
    public Set<Question> findAllBySubjectIdWithPagination(Long id, int limit, int offset) {
        return questionDAO.findAllBySubjectIdWithPagination(id, limit, offset);
    }

    @Override
    public Set<Question> findAllBySubjectId(Long id) {
        return questionDAO.findAllBySubjectId(id);
    }

    @Override
    public Set<Question> findAllByTestId(Long id){
        return questionDAO.findAllByTestId(id);
    }

    @Override
    public Set<Question> findAllByTestIdWithPagination(Long id, int limit, int offset){
        return questionDAO.findAllByTestIdWithPagination(id, limit, offset);
    }

}
