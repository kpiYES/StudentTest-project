package com.app.dao;

import com.app.model.Test;

public interface TestDAO {

    Long insert(Test test);

    void update(Test test);

    void delete(Test test);

    Test findById(Long id);
}
