package com.app.dao;

import com.app.model.Subject;
import com.app.model.User;

import java.util.Set;

public interface SubjectDAO {

    Long insert(Subject subject);

    void update(Subject subject);

    void delete(Subject subject);

    Subject findById(Long subject_id);

    Set<Subject> findAll();

    Subject findByName(String name);
}
