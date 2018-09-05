package com.app.controller;

import com.app.controller.commands.*;
import com.app.controller.commands.admin.*;
import com.app.controller.commands.general.LocaleCommand;
import com.app.controller.commands.general.LogInCommand;
import com.app.controller.commands.general.LogOutCommand;
import com.app.controller.commands.general.RegistrationCommand;
import com.app.controller.commands.student.*;
import com.app.dao.connection.ConnectionSource;
import com.app.exceptions.UnsupportedCommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CommandHelper {

    private Map<String, Command> commandsMap = new HashMap<>();

    CommandHelper() {
        commandsMap.put("login", new LogInCommand());
        commandsMap.put("logOut", new LogOutCommand());
        commandsMap.put("toRegistrPage", new ToRegistrPageCommand());
        commandsMap.put("registration", new RegistrationCommand());
        commandsMap.put("createQuestion", new CreateQuestionCommand());
        commandsMap.put("deleteQuestion", new DeleteQuestionCommand());
        commandsMap.put("deleteTest", new DeleteTestCommand());
        commandsMap.put("toShowListOfUsersFragment", new ToShowListOfUsersFragmentCommand());
        commandsMap.put("toShowUserFragment", new ToShowUserFragmentCommand());
        commandsMap.put("updateUserRole", new UpdateUserRoleCommand());
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
        commandsMap.put("toStudentPage", new ToStudentPageCommand());
        commandsMap.put("showPassedTest", new ShowPassedTestCommand());
        commandsMap.put("showListOfPassedTestsFragment", new ShowListOfPassedTestsFragmentCommand());
        commandsMap.put("showListOfSubjectsPassedTestsFragment", new ShowListOfSubjectsPassedTestsFragmentCommand());
        commandsMap.put("showListOfSubjectsUsersFragment", new ShowListOfSubjectsUsersFragmentCommand());
        commandsMap.put("showListOfPassedTestsUsersFragment", new ShowListOfPassedTestsUsersFragmentCommand());
        commandsMap.put("showPassedTestUsersFragment", new ShowPassedTestUsersFragmentCommand());
    }

    Command chooseCommand(HttpServletRequest request, HttpServletResponse response) {
        final String commandSignature = request.getParameter("command");
        if (!commandsMap.containsKey(commandSignature)) {
            throw new UnsupportedCommandException(commandSignature);
        }
        return commandsMap.get(commandSignature);
    }

    String startCommand(HttpServletRequest request, HttpServletResponse response) {
        Command command = chooseCommand(request, response);
        String page;
        try {
            ConnectionSource.bindConnection();
            page = command.execute(request, response);
        } finally {
            ConnectionSource.unbindConnection();
        }
        return page;
    }
}

