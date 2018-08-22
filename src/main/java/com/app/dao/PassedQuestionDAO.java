package com.app.dao;

import com.app.model.PassedQuestion;
import com.app.model.User;

import java.util.Set;

public interface PassedQuestionDAO {
    Long insert(PassedQuestion passedQuestion);

    void update(PassedQuestion passedQuestion);

    void delete(PassedQuestion passedQuestion);

    PassedQuestion findById(Long id);

    Set<PassedQuestion> findAll();
}
