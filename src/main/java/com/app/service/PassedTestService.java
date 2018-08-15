package com.app.service;

import com.app.model.PassedTest;

public interface PassedTestService {

    Long insert(PassedTest passedTest);


    void update(PassedTest passedTest);


    void delete(PassedTest passedTest);


    PassedTest getById(Long id);

}
