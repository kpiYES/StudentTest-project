package com.app.service;

import com.app.model.Test;

public interface TestService {

    Long insert(Test test);

    void update(Test test);

    void delete(Test test);

    Test findById(Long id);

}
