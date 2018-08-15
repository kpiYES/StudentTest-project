package com.app.dao;

import com.app.model.PassedTest;

public interface PassedTestDAO {

    Long insert(PassedTest passedTest);

    void update(PassedTest passedTest);

    void delete(PassedTest passedTest);

    PassedTest findById(Long id);
}
