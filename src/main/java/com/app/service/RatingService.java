package com.app.service;

import java.util.Map;

public interface RatingService {

    Integer rateAnswer(Map<Long, String> answersMap);
}
