package com.app.service.impl;

import com.app.dao.DAOFactory;
import com.app.dao.PassedQuestionDAO;
import com.app.model.PassedQuestion;
import com.app.model.Question;
import com.app.service.PassedQuestionService;
import com.app.service.QuestionService;
import com.app.service.ServiceFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PassedQuestionServiceImpl implements PassedQuestionService {

    private PassedQuestionDAO passedQuestionDAO;

    private PassedQuestionServiceImpl() {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.TypeDB.mySQL);
        passedQuestionDAO = daoFactory.getPassedQuestionDAO();
    }

    public static PassedQuestionService getInstance() {
        return PassedQuestionServiceImplHolder.INSTANCE;
    }

    @Override
    public List<Long> insertAll(Set<PassedQuestion> passedQuestionSet) {
        return passedQuestionDAO.insertAll(passedQuestionSet);
    }
    @Override
    public Set<PassedQuestion> findAllByPassedTestId(Long id) {
        return passedQuestionDAO.findAllByPassedTestId(id);
    }

    @Override
    public Set<PassedQuestion> constructFromUsersAnswersMap(Map<Long, String> usersAnswersMap) {
        QuestionService questionService = ServiceFactory.getQuestionService();
        Set<PassedQuestion> passedQuestionSet = new HashSet<>(usersAnswersMap.size());
        for (Map.Entry entry : usersAnswersMap.entrySet()) {
            Question question = questionService.findById((Long) entry.getKey());
            PassedQuestion passedQuestion = new PassedQuestion();
            passedQuestion.setQuestion(question);
            passedQuestion.setUserAnswer((String) entry.getValue());
            passedQuestionSet.add(passedQuestion);
        }
        return passedQuestionSet;
    }

    private static class PassedQuestionServiceImplHolder {
        private final static PassedQuestionService INSTANCE = new PassedQuestionServiceImpl();
    }
}
