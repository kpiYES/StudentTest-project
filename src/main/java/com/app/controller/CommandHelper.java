package com.app.controller;

import com.app.controller.commands.*;
import com.app.controller.commands.student.*;
import com.app.exceptions.UnsupportedCommandException;
import com.app.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandHelper {

    private ServiceFactory factory = ServiceFactory.getInstance();
    private Map<String, Command> commandsMap = new HashMap<>();

    CommandHelper() {
        commandsMap.put("login", new LoginCommand(factory.getUserService()));
        commandsMap.put("registr", new RegistrCommand(factory.getUserService(), factory.getRoleService()));
        commandsMap.put("createPassedTest", new CreatePassedTestCommand(factory.getPassedTestService()));
        commandsMap.put("createQuestion", new CreateQuestionCommand(factory.getQuestionService()));
        commandsMap.put("deleteQuestion", new DeleteQuestionCommand(factory.getQuestionService()));
        commandsMap.put("createTest", new CreateTestCommand());
        commandsMap.put("deleteTest", new DeleteTestCommand(factory.getTestService()));
        commandsMap.put("getTestForPassing", new GetTestForPassingCommand(factory.getPassedTestService()));
        commandsMap.put("getPassedTests", new GetPassedTestsCommand(factory.getPassedTestService()));
        commandsMap.put("toShowListOfUsersFragment", new ToShowListOfUsersFragmentCommand(factory.getUserService()));
        commandsMap.put("toShowUserFragment", new ToShowUserFragmentCommand(factory.getUserService(), factory.getRoleService()));
        commandsMap.put("updateUserRole", new UpdateUserRoleCommand(factory.getUserService(), factory.getRoleService()));
        commandsMap.put("deleteUser", new DeleteUserCommand(factory.getUserService()));
        commandsMap.put("showListOfTestsTestsFragment", new ShowListOfTestsTestsFragmentCommand(factory.getTestService(), factory.getSubjectService()));
        commandsMap.put("showListOfSubjectsTestsFragment", new ShowListOfSubjectsTestsFragmentCommand(factory.getSubjectService()));
        commandsMap.put("createTestTestsFragment", new CreateTestTestsFragmentCommand(factory.getTestService(), factory.getQuestionService()));
        commandsMap.put("showTestTestsFragment", new ShowTestTestsFragmentCommand(factory.getTestService(), factory.getQuestionService()));
        commandsMap.put("locale", new LocaleCommand());
        commandsMap.put("showListOfSubjectsQuestionsFragment", new ShowListOfSubjectsQuestionsFragmentCommand(factory.getSubjectService()));
        commandsMap.put("showListOfQuestionsQuestionsFragment", new ShowListOfQuestionsQuestionsFragmentCommand(factory.getQuestionService(), factory.getSubjectService()));
        commandsMap.put("showQuestionQuestionsFragment", new ShowQuestionQuestionsFragmentCommand(factory.getQuestionService(), factory.getTestService()));
        commandsMap.put("createQuestionQuestionsFragment", new CreateQuestionQuestionsFragmentCommand());
        commandsMap.put("showListOfSubjectsToPassTestFragment", new ShowListOfSubjectsToPassTestFragmentCommand(factory.getSubjectService()));
        commandsMap.put("showListOfTestsToPassTestFragment", new ShowListOfTestsToPassTestFragmentCommand(factory.getTestService(), factory.getSubjectService()));
        commandsMap.put("showTestToPassTestFragment", new ShowTestToPassTestFragmentCommand(factory.getTestService(), factory.getQuestionService()));
        commandsMap.put("passTestToPassTestFragment", new PassTestToPassTestFragmentCommand(factory.getPassedTestService(), factory.getPassedQuestionService(), factory.getRatingService()));
    }

    Command chooseCommand(HttpServletRequest request) {
        final String commandSignature = request.getParameter("command");
        if (!commandsMap.containsKey(commandSignature)) {
            throw new UnsupportedCommandException(commandSignature);
        }
        return commandsMap.get(commandSignature);
    }
}

