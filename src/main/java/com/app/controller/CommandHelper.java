package com.app.controller;

import com.app.controller.commands.*;
import com.app.controller.commands.student.PassTestToPassTestFragmentCommand;
import com.app.controller.commands.student.ShowListOfSubjectsToPassTestFragmentCommand;
import com.app.controller.commands.student.ShowListOfTestsToPassTestFragmentCommand;
import com.app.controller.commands.student.ShowTestToPassTestFragmentCommand;
import com.app.exceptions.UnsupportedCommandException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandHelper {

    private Map<String, Command> commandsMap = new HashMap<>();

    CommandHelper() {
        commandsMap.put("login", new LoginCommand());
        commandsMap.put("registr", new RegistrCommand());
        commandsMap.put("createPassedTest", new CreatePassedTestCommand());
        commandsMap.put("createQuestion", new CreateQuestionCommand());
        commandsMap.put("deleteQuestion", new DeleteQuestionCommand());
        commandsMap.put("createTest", new CreateTestCommand());
        commandsMap.put("deleteTest", new DeleteTestCommand());
        commandsMap.put("getTestForPassing", new GetTestForPassingCommand());
        commandsMap.put("getPassedTests", new GetPassedTestsCommand());
        commandsMap.put("toShowListOfUsersFragment", new ToShowListOfUsersFragmentCommand());
        commandsMap.put("toShowUserFragment", new ToShowUserFragmentCommand());
        commandsMap.put("updateUserRole", new UpdateUserRoleCommand());
        commandsMap.put("deleteUser", new DeleteUserCommand());
        commandsMap.put("showListOfTestsTestsFragment", new ShowListOfTestsTestsFragmentCommand());
        commandsMap.put("showListOfSubjectsTestsFragment", new ShowListOfSubjectsTestsFragmentCommand());
        commandsMap.put("createTestTestsFragment", new CreateTestTestsFragmentCommand());
        commandsMap.put("showTestTestsFragment", new ShowTestTestsFragmentCommand());
        commandsMap.put("locale", new LocaleCommand());
        commandsMap.put("showListOfSubjectsQuestionsFragment", new ShowListOfSubjectsQuestionsFragmentCommand());
        commandsMap.put("showListOfQuestionsQuestionsFragment", new ShowListOfQuestionsQuestionsFragmentCommand());
        commandsMap.put("showQuestionQuestionsFragment", new ShowQuestionQuestionsFragmentCommand());
        commandsMap.put("deleteQuestion", new DeleteQuestionCommand());
        commandsMap.put("createQuestionQuestionsFragment", new CreateQuestionQuestionsFragmentCommand());
        commandsMap.put("showListOfSubjectsToPassTestFragment", new ShowListOfSubjectsToPassTestFragmentCommand());
        commandsMap.put("showListOfTestsToPassTestFragment", new ShowListOfTestsToPassTestFragmentCommand());
        commandsMap.put("showTestToPassTestFragment", new ShowTestToPassTestFragmentCommand());
        commandsMap.put("passTestToPassTestFragment", new PassTestToPassTestFragmentCommand());
    }

    Command chooseCommand(HttpServletRequest request) {

        final String commandSignature = request.getParameter("command");
        if (!commandsMap.containsKey(commandSignature)) {
            throw new UnsupportedCommandException(commandSignature);
        }
        return commandsMap.get(commandSignature);
    }
}

