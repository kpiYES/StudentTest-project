package com.app.service;

import com.app.model.PassedTest;

import java.util.Set;

public interface PassedTestService {

    Long insert(PassedTest passedTest);

    PassedTest findById(Long id);

    Set<PassedTest> findAllByUserIdAndSubjectId(Long userId, Long subjectId );

    void insertWithPassedQuestions(PassedTest passedTest);

    void sendResult(PassedTest passedTest);

}
