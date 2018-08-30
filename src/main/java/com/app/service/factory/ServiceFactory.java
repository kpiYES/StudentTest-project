package com.app.service.factory;

import com.app.service.PassedQuestionService;
import com.app.service.PassedTestService;
import com.app.service.QuestionService;
import com.app.service.RatingService;
import com.app.service.RoleService;
import com.app.service.SubjectService;
import com.app.service.TestService;
import com.app.service.UserService;
import com.app.service.impl.PassedQuestionServiceImpl;
import com.app.service.impl.PassedTestServiceImpl;
import com.app.service.impl.QuestionServiceImpl;
import com.app.service.impl.RatingServiceImpl;
import com.app.service.impl.RoleServiceImpl;
import com.app.service.impl.SubjectServiceImpl;
import com.app.service.impl.TestServiceImpl;
import com.app.service.impl.UserServiceImpl;

public class ServiceFactory {

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return ServiceFactoryHolder.INSTANCE;
    }

    public UserService getUserService() {
        return UserServiceImpl.getInstance();
    }

    public TestService getTestService() {
        return TestServiceImpl.getInstance();
    }

    public SubjectService getSubjectService() {
        return SubjectServiceImpl.getInstance();
    }

    public QuestionService getQuestionService() {
        return QuestionServiceImpl.getInstance();
    }

    public RoleService getRoleService() {
        return RoleServiceImpl.getInstance();
    }

    public PassedTestService getPassedTestService() {
        return PassedTestServiceImpl.getInstance();
    }

    public PassedQuestionService getPassedQuestionService() {
        return PassedQuestionServiceImpl.getInstance();
    }

    public RatingService getRatingService() {
        return RatingServiceImpl.getInstance();
    }

    private static class ServiceFactoryHolder {
        private final static ServiceFactory INSTANCE = new ServiceFactory();
    }
}
