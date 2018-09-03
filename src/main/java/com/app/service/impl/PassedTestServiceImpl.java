package com.app.service.impl;

import com.app.dao.DAOFactory;
import com.app.dao.PassedQuestionDAO;
import com.app.dao.PassedTestDAO;
import com.app.dao.connection.ConnectionSource;
import com.app.dao.connection.DAOConnection;
import com.app.dao.connection.TransactionManager;
import com.app.exceptions.InteractionDBException;
import com.app.model.PassedQuestion;
import com.app.model.PassedTest;
import com.app.service.PassedTestService;
import com.app.service.util.EmailSender;

import java.util.Set;

public class PassedTestServiceImpl implements PassedTestService {

    private final DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.TypeDB.mySQL);

    private PassedTestServiceImpl() {
    }

    public static PassedTestServiceImpl getInstance() {
        return PassedTestServiceImplHolder.INSTANCE;
    }

    @Override
    public Long insert(PassedTest passedTest) {
            PassedTestDAO passedTestDAO = daoFactory.getPassedTestDAO();
            return passedTestDAO.insert(passedTest);

    }

    @Override
    public void update(PassedTest passedTest) {
            PassedTestDAO passedTestDAO = daoFactory.getPassedTestDAO();
            passedTestDAO.update(passedTest);

    }

    @Override
    public void delete(PassedTest passedTest) {
            PassedTestDAO passedTestDAO = daoFactory.getPassedTestDAO();
            passedTestDAO.delete(passedTest);

    }

    @Override
    public PassedTest findById(Long id) {
            PassedTestDAO passedTestDAO = daoFactory.getPassedTestDAO();
            return passedTestDAO.findById(id);

    }

    @Override
    public Set<PassedTest> findAll() {
            PassedTestDAO passedTestDAO = daoFactory.getPassedTestDAO();
            return passedTestDAO.findAll();
        }


    @Override
    public Set<PassedTest> findAllByUserId(Long id) {
            PassedTestDAO passedTestDAO = daoFactory.getPassedTestDAO();
            return passedTestDAO.findAllByUserId(id);
        }


    @Override
    public Set<PassedTest> findAllByUserIdAndSubjectId(Long userId, Long subjectId ) {
            PassedTestDAO passedTestDAO = daoFactory.getPassedTestDAO();
            return passedTestDAO.findAllByUserIdAndSubjectId(userId, subjectId);
        }


    @Override
    public void insertWithPassedQuestions(PassedTest passedTest) {
        PassedTestDAO passedTestDAO = daoFactory.getPassedTestDAO();
        PassedQuestionDAO passedQuestionDAO = daoFactory.getPassedQuestionDAO();
        try {
            ConnectionSource.bindConnection();
            TransactionManager.beginTransaction();
            passedTest.setId(passedTestDAO.insert(passedTest));
            for (PassedQuestion passedQuestion : passedTest.getPassedQuestionSet()) {
                passedQuestion.setPassedTest(passedTest);
            }
            passedQuestionDAO.insertAll(passedTest.getPassedQuestionSet());
            TransactionManager.commitTransaction();
        }catch (InteractionDBException e){
            TransactionManager.rollbackTransaction();
        }finally {
            ConnectionSource.unbindConnection();
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
