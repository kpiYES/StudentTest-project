package com.app.service.impl;

import com.app.dao.DAOFactory;
import com.app.dao.PassedTestDAO;
import com.app.dao.connection.TransactionManager;
import com.app.dto.DTOHandler;
import com.app.dto.PassedTestDTO;
import com.app.dto.UserDTO;
import com.app.exceptions.InteractionDBException;
import com.app.model.*;
import com.app.service.PassedQuestionService;
import com.app.service.PassedTestService;
import com.app.service.ServiceFactory;
import com.app.service.util.EmailSender;
import com.app.service.util.TestMarkRating;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class PassedTestServiceImpl implements PassedTestService {

    private PassedTestDAO passedTestDAO;
    private PassedQuestionService passedQuestionService;


    private PassedTestServiceImpl() {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.TypeDB.mySQL);
        passedTestDAO = daoFactory.getPassedTestDAO();
        passedQuestionService = ServiceFactory.getPassedQuestionService();
    }

    public static PassedTestServiceImpl getInstance() {
        return PassedTestServiceImplHolder.INSTANCE;
    }

    @Override
    public Long insert(PassedTest passedTest) {
        return passedTestDAO.insert(passedTest);
    }

    @Override
    public PassedTestDTO findByIdWithQuestions(Long id) {
        PassedTest passedTest = passedTestDAO.findById(id);
        PassedTestDTO passedTestDTO = DTOHandler.constructPassedTestDTO(passedTest);
        passedTestDTO.setPassedQuestionSet(passedQuestionService.findAllByPassedTestId(id));
        return passedTestDTO;
    }

    @Override
    public Set<PassedTest> findAllByUserIdAndSubjectId(Long userId, Long subjectId) {
        return passedTestDAO.findAllByUserIdAndSubjectId(userId, subjectId);
    }

    @Override
    public void insertWithPassedQuestions(PassedTest passedTest) {
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

    public PassedTest create(List<Question> questionsList, Test test, UserDTO userDTO, Map<Long, String> answerMap) {
        for (Question question : questionsList) {
            if (!answerMap.containsKey(question.getId())) {
                answerMap.put(question.getId(), null);
            }
        }
        User user = DTOHandler.constructUser(userDTO);
        Integer mark = TestMarkRating.toRateMark(answerMap);
        PassedTest passedTest = new PassedTest();
        passedTest.setTest(test);
        passedTest.setUser(user);
        passedTest.setMark(mark);
        Set<PassedQuestion> passedQuestionSet = passedQuestionService.constructFromUsersAnswersMap(answerMap);
        passedTest.setPassedQuestionSet(passedQuestionSet);
        insertWithPassedQuestions(passedTest);
        sendResult(passedTest);
        return passedTest;
    }

    @Override
    public void sendResult(PassedTest passedTest) {
        EmailSender emailSender = new EmailSender(passedTest.getUser().getMail(), "You passed the test for " + passedTest.getMark().toString() + "%");
        emailSender.sendEmail();
    }

    private static class PassedTestServiceImplHolder {
        private final static PassedTestServiceImpl INSTANCE = new PassedTestServiceImpl();
    }
}
