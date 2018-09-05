package com.app.service;

import com.app.model.PassedQuestion;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface PassedQuestionService {

    List<Long> insertAll(Set<PassedQuestion> passedQuestionSet);

    Set<PassedQuestion> findAllByPassedTestId(Long id);

    Set<PassedQuestion> constructFromUsersAnswersMap(Map<Long, String> usersAnswersMap);

}
