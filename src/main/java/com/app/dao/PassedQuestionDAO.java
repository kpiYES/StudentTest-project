package com.app.dao;

public interface PassedQuestionDAO {
    Long insert(com.app.model.PassedQuestion passedQuestion);

    void update(com.app.model.PassedQuestion passedQuestion);

    void delete(com.app.model.PassedQuestion passedQuestion);

    com.app.model.PassedQuestion findById(Long id);
}
