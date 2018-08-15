package com.app.service;

import com.app.model.Subject;

public interface SubjectService {

    Long insert(Subject subject);

    void update(Subject subject);

    void delete(Subject subject);

    Subject findById(Long id);

    Subject findByName(String name);
}
