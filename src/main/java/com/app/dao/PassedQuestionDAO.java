package com.app.dao;

import com.app.model.PassedQuestion;

import java.util.List;
import java.util.Set;

public interface PassedQuestionDAO {

    Long insert(PassedQuestion passedQuestion);

    void update(PassedQuestion passedQuestion);

    void delete(PassedQuestion passedQuestion);

    PassedQuestion findById(Long id);

    Set<PassedQuestion> findAll();

    List<Long> insertAll(Set<PassedQuestion> passedQuestionSet);

    Set<PassedQuestion> findAllByPassedTestId(Long id);


}
