package com.app.service;

import com.app.model.PassedTest;

import java.util.Set;

public interface PassedTestService extends Service {

    Long insert(PassedTest passedTest);

    void update(PassedTest passedTest);

    void delete(PassedTest passedTest);

    PassedTest findById(Long id);

    Set<PassedTest> findAll();

    Set<PassedTest> findAllByUserId(Long id);

    Set<PassedTest> findAllByUserIdAndSubjectId(Long userId, Long subjectId );

    void insertWithPassedQuestions(PassedTest passedTest);

    void sendResult(PassedTest passedTest);

}
