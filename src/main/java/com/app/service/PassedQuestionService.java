package com.app.service;

import com.app.model.PassedQuestion;

import java.util.Map;
import java.util.Set;

public interface PassedQuestionService {
    Long insert(PassedQuestion passedQuestion);

    void update(PassedQuestion passedQuestion);

    void delete(PassedQuestion passedQuestion);

    PassedQuestion findById(Long id);

    Set<PassedQuestion> findAll();

    Set<PassedQuestion> findAllByPassedTestId(Long id);

    Set<PassedQuestion> constructFromUsersAnswersMap(Map<Long, String> usersAnswersMap);

}
