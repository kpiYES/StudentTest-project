package com.app.service.util;

import com.app.model.Question;
import com.app.service.QuestionService;
import com.app.service.ServiceFactory;

import java.util.Map;

public class TestMarkRating {

    public static Integer toRateMark(Map<Long, String> answersMap) {

        QuestionService questionService = ServiceFactory.getQuestionService();
        int countOfCorrectAnswers = 0;
        int countOfQuestions = answersMap.size();
        for (Map.Entry entry : answersMap.entrySet()) {
            Question question = questionService.findById((Long) entry.getKey());
            if (question.getCorrectAnswer().equals(entry.getValue())) {
                countOfCorrectAnswers++;
            }
        }
        return Math.round(countOfCorrectAnswers * 100 / countOfQuestions);
    }
}
