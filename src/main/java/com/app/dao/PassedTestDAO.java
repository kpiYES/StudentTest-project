package com.app.dao;

import com.app.model.PassedTest;
import com.app.model.User;

import java.util.Set;

public interface PassedTestDAO {

    Long insert(PassedTest passedTest);

    void update(PassedTest passedTest);

    void delete(PassedTest passedTest);

    PassedTest findById(Long id);

    Set<PassedTest> findAll();

    Set<PassedTest> findAllByUserId(Long id);

    Set<PassedTest> findAllByUserIdAndSubjectId(Long userId, Long subjectId);
}
