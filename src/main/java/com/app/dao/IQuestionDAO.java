package com.app.dao;

import com.app.model.Question;

public interface IQuestionDAO {

    Long insert(Question question);

    void update(Question question);

    void delete(Question question);

    Question getById(Long question_id);
}
