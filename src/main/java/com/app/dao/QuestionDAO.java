package com.app.dao;

import com.app.model.Question;

import java.util.Set;

public interface QuestionDAO {

    Long insert(Question question);

    void update(Question question);

    void delete(Question question);

    Question findById(Long question_id);

    Set<Question> findAll();

    Set<Question> findAllBySubjectId(Long id);

    Set<Question> findAllBySubjectIdWithPagination(Long id, int limit, int offset);

    Set<Question> findAllByTestId(Long id);
}
