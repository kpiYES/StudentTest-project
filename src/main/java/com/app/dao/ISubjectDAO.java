package com.app.dao;

import com.app.model.Subject;

public interface ISubjectDAO {
    Long insert(Subject subject);

    void update(Subject subject);

    void delete(Subject subject);

    Subject getById(Long subject_id);

    Subject getByName(String name);
}
