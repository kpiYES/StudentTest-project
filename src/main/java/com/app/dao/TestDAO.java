package com.app.dao;

import com.app.model.Test;

import java.util.Set;

public interface TestDAO {

    Long insert(Test test);

    void update(Test test);

    void delete(Test test);

    Test findById(Long id);

    Set<Test> findAll();

    Set<Test> findAllBySubjectId(Long id);

    Set<Test> findAllByQuestionId(Long id);
}
