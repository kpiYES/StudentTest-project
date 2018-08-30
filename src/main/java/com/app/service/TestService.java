package com.app.service;

import com.app.model.Test;

import java.util.Set;

public interface TestService extends Service {

    Long insert(Test test);

    void update(Test test);

    void delete(Test test);

    Test findById(Long id);

    Set<Test> findAll();

    Set<Test> findAllBySubjectId(Long id);

//    Test findByIdWithQuestions(Long id);

    Set<Test> getFindAllByQuestionId(Long id);


}
