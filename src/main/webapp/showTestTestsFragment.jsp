<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 01.09.2018
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Title</title>
    <link href="css/main.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="result">
    <h3>
        <p><c:out value="${sessionScope.test.name}"/></p>
    </h3>
    <c:forEach var="question" items="${sessionScope.test.questionSet}">
        <div>
            <table>
                <p><c:out value="${question.query}"/></p>
                <c:choose>
                    <c:when test="${question.answer1 eq question.correctAnswer}">
                        <tr>
                            <td>
                                <p><input type="checkbox" checked disabled><c:out value="${question.answer1}"/></p>
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td>
                                <p><input type="checkbox" disabled><c:out value="${question.answer1}"/></p>
                            </td>
                        </tr>
                    </c:otherwise>
                </c:choose>
                <tr>
                    <c:choose>
                    <c:when test="${question.answer2 eq question.correctAnswer}">
                <tr>
                    <td>
                        <p><input type="checkbox" checked disabled><c:out value="${question.answer2}"/></p>
                    </td>
                </tr>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td>
                            <p><input type="checkbox" disabled><c:out value="${question.answer2}"/></p>
                        </td>
                    </tr>
                </c:otherwise>
                </c:choose>
                <tr>
                    <c:choose>
                    <c:when test="${question.answer3 eq question.correctAnswer}">
                <tr>
                    <td>
                        <p><input type="checkbox" checked disabled><c:out value="${question.answer3}"/></p>
                    </td>
                </tr>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td>
                            <p><input type="checkbox" disabled><c:out value="${question.answer3}"/></p>
                        </td>
                    </tr>
                </c:otherwise>
                </c:choose>
                <tr>
                    <c:choose>
                    <c:when test="${question.answer4 eq question.correctAnswer}">
                <tr>
                    <td>
                        <p><input type="checkbox" checked disabled><c:out value="${question.answer4}"/></p>
                    </td>
                </tr>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td>
                            <p><input type="checkbox" disabled><c:out value="${question.answer4}"/></p>
                        </td>
                    </tr>
                </c:otherwise>
                </c:choose>
            </table>
        </div>
    </c:forEach>
    <form>
        <input type="hidden" name="command" value="deleteTest">
        <input type="submit" value="Delete test">
    </form>
    <form>
        <input type="hidden" name="command" value="showListOfTestsTestsFragment">
        <button name="subjectId" value="${sessionScope.test.subject.id}">Back</button>
    </form>
</div>


</body>
</html>
