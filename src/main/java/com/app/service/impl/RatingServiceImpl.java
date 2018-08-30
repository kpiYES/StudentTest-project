package com.app.service.impl;

import com.app.model.Question;
import com.app.service.QuestionService;
import com.app.service.RatingService;
import com.app.service.factory.ServiceFactory;

import java.util.Map;

public class RatingServiceImpl implements RatingService {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private QuestionService questionService;

    public static RatingServiceImpl getInstance() {
        return RatingServiceImpl.RatingServiceImplHolder.INSTANCE;
    }

    private RatingServiceImpl() {
        this.questionService = serviceFactory.getQuestionService();
    }

    @Override
    public Integer rateAnswer(Map<Long, String> answersMap) {
        int countOfCorrectAnswers = 0;
        int countOfQuestions = answersMap.size();
        for (Map.Entry<Long, String> entry : answersMap.entrySet()) {
            Question question = questionService.findById(entry.getKey());
            if (question.getCorrectAnswer().equals(entry.getValue())) {
                countOfCorrectAnswers++;
            }
        }
        return Math.round(countOfCorrectAnswers * 100 / countOfQuestions);
    }

    private static class RatingServiceImplHolder {
        private final static RatingServiceImpl INSTANCE = new RatingServiceImpl();
    }
}
