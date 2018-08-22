package com.app.controller;

import com.app.exceptions.UnsupportedCommandException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandHelper {

    private Map<String, Command> commandsMap = new HashMap<>();

    CommandHelper() {
        commandsMap.put("login", new LoginCommand());
        commandsMap.put("toRegistrPage", new ToRegistrPageCommand());
        commandsMap.put("registr", new RegistrCommand());
        commandsMap.put("createPassedTest", new CreatePassedTestCommand());
        commandsMap.put("createQuestion", new CreateQuestionCommand());
        commandsMap.put("updateQuestion", new UpdateQuestionCommand());
        commandsMap.put("deleteQuestion", new DeleteQuestionCommand());
        commandsMap.put("createTest", new CreateTestCommand());
        commandsMap.put("updateTest", new UpdateTestCommand());
        commandsMap.put("deleteTest", new DeleteTestCommand());
        commandsMap.put("getTestForPassing", new GetTestForPassingCommand());
        commandsMap.put("getPassedTests", new GetPassedTestsCommand());
        commandsMap.put("toCreateQuestionPage", new ToCreateQuestionPageCommand());
        commandsMap.put("toShowListOfUsersFragment", new ToShowListOfUsersFragmentCommand());
        commandsMap.put("toShowUserFragment", new ToShowUserFragmentCommand());
        commandsMap.put("updateUserRole", new UpdateUserRoleCommand());
        commandsMap.put("deleteUser", new DeleteUserCommand());
        commandsMap.put("toShowListOfTestsFragment", new ToShowListOfTestsFragmentCommand());
        commandsMap.put("toShowListOfSubjectsFragment", new ToShowListOfSubjectsFragmentCommand());
        commandsMap.put("toCreateTestFragment", new ToCreateTestFragmentCommand());
        commandsMap.put("toShowTestFragment", new ToShowTestFragmentCommand());

    }

    Command chooseCommand(HttpServletRequest request) {

        final String commandSignature = request.getParameter("command");
        if (!commandsMap.containsKey(commandSignature)) {
            throw new UnsupportedCommandException(commandSignature);
        }
        return commandsMap.get(commandSignature);
    }
}

