package com.app.service;

import com.app.service.impl.*;

public class ServiceFactory {

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

    public static PassedQuestionService getPassedQuestionService() {
        return PassedQuestionServiceImpl.getInstance();
    }

    public static UserDetailsService getUserDetailsService() {
        return UserDetailsServiceImpl.getInstance();
    }

}
