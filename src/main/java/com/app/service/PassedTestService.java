package com.app.service;

import com.app.dto.PassedTestDTO;
import com.app.dto.UserDTO;
import com.app.model.PassedTest;
import com.app.model.Question;
import com.app.model.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface PassedTestService {

    Long insert(PassedTest passedTest);

    PassedTestDTO findByIdWithQuestions(Long id);

    Set<PassedTest> findAllByUserIdAndSubjectId(Long userId, Long subjectId);

    void insertWithPassedQuestions(PassedTest passedTest);

    void sendResult(PassedTest passedTest);

    PassedTest create(List<Question> questionsList, Test test, UserDTO userDTO, Map<Long, String> answerMap);


}
