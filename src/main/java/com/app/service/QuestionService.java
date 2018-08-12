package com.app.service;

import com.app.model.Question;

public interface QuestionService {

    Long insert(Question question);


    void update(Question question);


    void delete(Question question);


    Question getById(Long question_id);

}
