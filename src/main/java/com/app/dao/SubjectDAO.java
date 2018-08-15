package com.app.dao;

import com.app.model.Subject;

public interface SubjectDAO {

    Long insert(Subject subject);

    void update(Subject subject);

    void delete(Subject subject);

    Subject findById(Long subject_id);

    Subject findByName(String name);
}
