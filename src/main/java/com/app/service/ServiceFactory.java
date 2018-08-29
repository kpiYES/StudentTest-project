package com.app.service;

import com.app.service.impl.*;

public class ServiceFactory {

    private static ServiceFactory instance;

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        if (instance == null) {
            instance = new ServiceFactory();
        }
        return instance;
    }

    public static UserService getUserService() {
        return UserServiceImpl.getInstance();
    }

    public static TestService getTestService() {
        return TestServiceImpl.getInstance();
    }

    public static SubjectService getSubjectService() {
        return SubjectServiceImpl.getInstance();
    }

    public static QuestionService getQuestionService() {
        return QuestionServiceImpl.getInstance();
    }

    public static RoleService getRoleService() {
        return RoleServiceImpl.getInstance();
    }

    public static PassedTestService getPassedTestService() {
        return PassedTestServiceImpl.getInstance();
    }

}
