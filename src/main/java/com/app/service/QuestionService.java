package com.app.service;

import com.app.model.Question;

import java.util.Set;

public interface QuestionService {

    Long insert(Question question);

    void delete(Question question);

    Question findById(Long id);

    Set<Question> findAllBySubjectId(Long id);

    Set<Question> findAllBySubjectIdWithPagination(Long id, int limit, int offset);

    Set<Question> findAllByTestId(Long id);
}
