package com.app.service;

import com.app.dto.UserDTO;
import com.app.model.PassedTest;
import com.app.model.Test;

import java.util.Map;
import java.util.Set;

public interface PassedTestService extends Service {

    Long insert(PassedTest passedTest);

    void update(PassedTest passedTest);

    void delete(PassedTest passedTest);

    PassedTest findById(Long id);

    Set<PassedTest> findAll();

    Set<PassedTest> findByUserId(Long id);

    Long insertWithPassedQuestions(PassedTest passedTest);


    }
