package com.app.dao;

import com.app.model.PassedTest;

public interface IPassedTestDAO {

    Long insert(PassedTest passedTest);

    void update(PassedTest passedTest);

    void delete(PassedTest passedTest);

    PassedTest getById(Long id);
}
