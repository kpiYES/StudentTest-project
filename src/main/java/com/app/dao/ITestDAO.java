package com.app.dao;

import com.app.model.Test;

public interface ITestDAO {

    Long insert(Test test);

    void update(Test test);

    void delete(Test test);

    Test getById(Long id);
}
