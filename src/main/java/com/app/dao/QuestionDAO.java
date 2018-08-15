package com.app.dao;

import com.app.model.Question;

public interface QuestionDAO {

    Long insert(Question question);

    void update(Question question);

    void delete(Question question);

    Question findById(Long question_id);
}
