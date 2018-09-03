<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 31.08.2018
  Time: 3:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="result">

    <h3>
        <c:out value="${sessionScope.passedTest.test.name}"/>
    </h3>

    <p> Your result is <c:out value="${sessionScope.passedTest.mark}"/>% of correct answers</p>

    <c:forEach var="passedQuestion" items="${sessionScope.passedTest.passedQuestionSet}">

        <div>
            <table>
                <p><c:out value="${passedQuestion.question.query}"/></p>
                <c:choose>
                    <c:when test="${passedQuestion.question.answer1 eq passedQuestion.question.correctAnswer}">
                        <tr>
                            <td
                                    <c:if test="${passedQuestion.question.answer1 eq passedQuestion.userAnswer}">
                                        bgcolor="#90ee90"
                                    </c:if>
                            >
                                <p><input type="checkbox" checked disabled><c:out
                                        value="${passedQuestion.question.answer1}"/></p>
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td
                                    <c:if test="${passedQuestion.question.answer1 eq passedQuestion.userAnswer}">
                                        bgcolor="#ffb6c1"
                                    </c:if>
                            >
                                <p><input type="checkbox" disabled><c:out value="${passedQuestion.question.answer1}"/>
                                </p>
                            </td>
                        </tr>
                    </c:otherwise>
                </c:choose>
                <tr>
                    <c:choose>
                    <c:when test="${passedQuestion.question.answer2 eq passedQuestion.question.correctAnswer}">
                <tr>
                    <td
                            <c:if test="${passedQuestion.question.answer2 eq passedQuestion.userAnswer}">
                                bgcolor="#90ee90"
                            </c:if>
                    >
                        <p><input type="checkbox" checked disabled><c:out value="${passedQuestion.question.answer2}"/>
                        </p>
                    </td>
                </tr>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td
                                <c:if test="${passedQuestion.question.answer2 eq passedQuestion.userAnswer}">
                                    bgcolor="#ffb6c1"
                                </c:if>
                        >
                            <p><input type="checkbox" disabled><c:out value="${passedQuestion.question.answer2}"/></p>
                        </td>
                    </tr>
                </c:otherwise>
                </c:choose>
                </tr>
                <tr>
                    <c:choose>
                    <c:when test="${passedQuestion.question.answer3 eq passedQuestion.question.correctAnswer}">
                <tr>
                    <td
                            <c:if test="${passedQuestion.question.answer3 eq passedQuestion.userAnswer}">
                                bgcolor="#90ee90"
                            </c:if>
                    >
                        <p><input type="checkbox" checked disabled><c:out value="${passedQuestion.question.answer3}"/>
                        </p>
                    </td>
                </tr>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td
                                <c:if test="${passedQuestion.question.answer3 eq passedQuestion.userAnswer}">
                                    bgcolor="#ffb6c1"
                                </c:if>
                        >
                            <p><input type="checkbox" disabled><c:out value="${passedQuestion.question.answer3}"/></p>
                        </td>
                    </tr>
                </c:otherwise>
                </c:choose>
                </tr>
                <tr>
                    <c:choose>
                    <c:when test="${passedQuestion.question.answer4 eq passedQuestion.question.correctAnswer}">
                <tr>
                    <td
                            <c:if test="${passedQuestion.question.answer4 eq passedQuestion.userAnswer}">
                                bgcolor="#90ee90"
                            </c:if>
                    >
                        <p><input type="checkbox" checked disabled><c:out value="${passedQuestion.question.answer4}"/>
                        </p>
                    </td>
                </tr>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td
                                <c:if test="${passedQuestion.question.answer4 eq passedQuestion.userAnswer}">
                                    bgcolor="#ffb6c1"
                                </c:if>
                        >
                            <p><input type="checkbox" disabled><c:out value="${passedQuestion.question.answer4}"/></p>
                        </td>
                    </tr>
                </c:otherwise>
                </c:choose>
                </tr>
            </table>
        </div>

    </c:forEach>

<form method="get" action="dispatcher">
    <p>The result of the passed test was sent to the e-mail you specified.</p>
        <input type="hidden" name="command" value="toStudentPage">
    <input type="submit" value="OK">
</form>

</div>
</body>
</html>
