package com.app.controller.commands;

import com.app.model.Question;
import com.app.model.Subject;
import com.app.service.QuestionService;
import com.app.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CreateTestTestsFragmentCommand implements Command {

    private QuestionService questionService = ServiceFactory.getQuestionService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int page = 1;
        int recordsPerPage = 3;
        Set<Long> chosenQuestionsSet = new HashSet<>();

        if ((request.getSession().getAttribute("chosenQuestionsSet")) != null) {
            chosenQuestionsSet.addAll((Set<Long>) request.getSession().getAttribute("chosenQuestionsSet"));
        }

        if ((request.getSession().getAttribute("questionSet")) != null) {
            for (Question question : ((Set<Question>) request.getSession().getAttribute("questionSet"))) {
                if (chosenQuestionsSet.contains(question.getId())) {
                    if (request.getParameterMap().containsKey("question")) {
                        if (!Arrays.asList(request.getParameterValues("question")).contains(question.getId().toString())) {
                            chosenQuestionsSet.remove(question.getId());
                        }
                    } else {
                        chosenQuestionsSet.remove(question.getId());
                    }
                }
            }
        }

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        if (request.getParameterMap().containsKey("question")) {
            String[] chosenQuestions = request.getParameterValues("question");
            for (String id : chosenQuestions) {
                chosenQuestionsSet.add(Long.parseLong(id));
            }
        }

        if (request.getParameterMap().containsKey("newTestName")) {
            request.getSession().setAttribute("newTestName", request.getParameter("newTestName"));
        }

        Set<Question> questionSetWithPagination = questionService.findAllBySubjectIdWithPagination(((Subject) request.getSession().getAttribute("subject")).getId(), recordsPerPage, (page - 1) * recordsPerPage);
        Set<Question> questionSet = questionService.findAllBySubjectId(((Subject) request.getSession().getAttribute("subject")).getId());
        long numberOfRecords = questionSet.size();
        long numberOfPages = (long) Math.ceil(numberOfRecords * 1.0 / recordsPerPage);
        request.getSession().setAttribute("chosenQuestionsSet", chosenQuestionsSet);
        request.getSession().setAttribute("questionSet", questionSetWithPagination);
        request.setAttribute("currentPage", page);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("pageFragment", "createTestFragment2.jsp");

        return "admin.jsp";
    }
}
