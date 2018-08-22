package com.app.service;

import com.app.model.Subject;

import java.util.Set;

public interface SubjectService {

    Long insert(Subject subject);

    void update(Subject subject);

    void delete(Subject subject);

    Subject findById(Long id);

    Set<Subject> findAll();

    Subject findByName(String name);

}
