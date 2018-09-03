package com.app.service;

import com.app.model.Subject;

import java.util.Set;

public interface SubjectService {

    Subject findById(Long id);

    Set<Subject> findAll();

}
