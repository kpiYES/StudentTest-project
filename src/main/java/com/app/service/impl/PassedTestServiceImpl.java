package com.app.service.impl;

import com.app.dao.DAOFactory;
import com.app.dao.PassedQuestionDAO;
import com.app.dao.PassedTestDAO;
import com.app.dao.connection.TransactionManager;
import com.app.exceptions.InteractionDBException;
import com.app.model.PassedQuestion;
import com.app.model.PassedTest;
import com.app.service.PassedQuestionService;
import com.app.service.PassedTestService;
import com.app.service.ServiceFactory;
import com.app.service.util.EmailSender;

import java.util.Set;

public class PassedTestServiceImpl implements PassedTestService {

    private PassedTestDAO passedTestDAO;

    private PassedTestServiceImpl() {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.TypeDB.mySQL);
        passedTestDAO = daoFactory.getPassedTestDAO();
    }

    public static PassedTestServiceImpl getInstance() {
        return PassedTestServiceImplHolder.INSTANCE;
    }

    @Override
    public Long insert(PassedTest passedTest) {
        return passedTestDAO.insert(passedTest);
    }

    @Override
    public PassedTest findById(Long id) {
        return passedTestDAO.findById(id);
    }

    @Override
    public Set<PassedTest> findAllByUserIdAndSubjectId(Long userId, Long subjectId) {
        return passedTestDAO.findAllByUserIdAndSubjectId(userId, subjectId);
    }

    @Override
    public void insertWithPassedQuestions(PassedTest passedTest) {
        PassedQuestionService passedQuestionService = ServiceFactory.getPassedQuestionService();
        try {
            TransactionManager.beginTransaction();
            passedTest.setId(passedTestDAO.insert(passedTest));
            for (PassedQuestion passedQuestion : passedTest.getPassedQuestionSet()) {
                passedQuestion.setPassedTest(passedTest);
            }
            passedQuestionService.insertAll(passedTest.getPassedQuestionSet());
            TransactionManager.commitTransaction();
        } catch (InteractionDBException e) {
            TransactionManager.rollbackTransaction();
        }
    }

    @Override
    public void sendResult(PassedTest passedTest) {
        EmailSender emailSender = new EmailSender(passedTest.getUser().getMail(), passedTest.getMark().toString());
        emailSender.sendEmail();
    }

    private static class PassedTestServiceImplHolder {
        private final static PassedTestServiceImpl INSTANCE = new PassedTestServiceImpl();
    }
}
