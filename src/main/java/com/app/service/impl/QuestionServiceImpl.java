package com.app.service.impl;

import com.app.dao.DAOFactory;
import com.app.dao.QuestionDAO;
import com.app.model.Question;
import com.app.model.Subject;
import com.app.service.QuestionService;
import com.app.service.util.Validator;

import java.util.Map;
import java.util.Set;

public class QuestionServiceImpl implements QuestionService {

    private QuestionDAO questionDAO;

    private QuestionServiceImpl() {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.TypeDB.mySQL);
        questionDAO = daoFactory.getQuestionDAO();
    }

    public static QuestionServiceImpl getInstance() {
        return PassedTestServiceImplHolder.INSTANCE;
    }

    @Override
    public Long insert(Question question) {
        return questionDAO.insert(question);
    }

    @Override
    public void delete(Question question) {
        questionDAO.delete(question);
    }

    @Override
    public Question findById(Long id) {
        return questionDAO.findById(id);
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
    public Set<Question> findAllByTestId(Long id) {
        return questionDAO.findAllByTestId(id);
    }

    @Override
    public Boolean create(Map<String, String[]> paramMap, Subject subject) {
        if (Validator.validateCreateQuestionForm(paramMap)) {
            return false;
        }
        Question question = new Question();
        question.setSubject(subject);
        question.setQuery(paramMap.get("query")[0]);
        question.setAnswer1(paramMap.get("answer1")[0]);
        question.setAnswer2(paramMap.get("answer2")[0]);
        question.setAnswer3(paramMap.get("answer3")[0]);
        question.setAnswer4(paramMap.get("answer4")[0]);
        question.setCorrectAnswer(paramMap.get(paramMap.get("correctAnswer")[0])[0]);
        insert(question);
        return true;
    }

    private static class PassedTestServiceImplHolder {
        private final static QuestionServiceImpl INSTANCE = new QuestionServiceImpl();
    }
}
