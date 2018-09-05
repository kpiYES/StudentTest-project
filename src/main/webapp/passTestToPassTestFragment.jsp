<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 30.08.2018
  Time: 3:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="questions">
    <div>
        <form method="post" action="dispatcher" id="form">
            <c:set var="currentQuestionIndex" value="${sessionScope.currentQuestionIndex}" scope="request"/>
            <c:set var="questionsList" value="${requestScope.questionsList}" scope="request"/>

            <p><b><c:out value="${questionsList.get(currentQuestionIndex).query}"/></b></p>
            <c:choose>
                <c:when test="${questionsList.get(currentQuestionIndex).answer1 eq sessionScope.answerMap.get(questionsList.get(currentQuestionIndex).id)}">
                    <p><input name="usersAnswer" type="radio" value="${questionsList.get(currentQuestionIndex).answer1}"
                              checked>
                        <c:out value="${questionsList.get(currentQuestionIndex).answer1}"/></p>
                </c:when>
                <c:otherwise>
                    <p><input name="usersAnswer" type="radio"
                              value="${questionsList.get(currentQuestionIndex).answer1}">
                        <c:out value="${questionsList.get(currentQuestionIndex).answer1}"/></p>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${questionsList.get(currentQuestionIndex).answer2 eq sessionScope.answerMap.get(questionsList.get(currentQuestionIndex).id)}">
                    <p><input name="usersAnswer" type="radio" value="${questionsList.get(currentQuestionIndex).answer2}"
                              checked>
                        <c:out value="${questionsList.get(currentQuestionIndex).answer2}"/></p>
                </c:when>
                <c:otherwise>
                    <p><input name="usersAnswer" type="radio"
                              value="${questionsList.get(currentQuestionIndex).answer2}">
                        <c:out value="${questionsList.get(currentQuestionIndex).answer2}"/></p>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${questionsList.get(currentQuestionIndex).answer3 eq sessionScope.answerMap.get(questionsList.get(currentQuestionIndex).id)}">
                    <p><input name="usersAnswer" type="radio" value="${questionsList.get(currentQuestionIndex).answer3}"
                              checked>
                        <c:out value="${questionsList.get(currentQuestionIndex).answer3}"/></p>
                </c:when>
                <c:otherwise>
                    <p><input name="usersAnswer" type="radio"
                              value="${questionsList.get(currentQuestionIndex).answer3}">
                        <c:out value="${questionsList.get(currentQuestionIndex).answer3}"/></p>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${questionsList.get(currentQuestionIndex).answer4 eq sessionScope.answerMap.get(questionsList.get(currentQuestionIndex).id)}">
                    <p><input name="usersAnswer" type="radio" value="${questionsList.get(currentQuestionIndex).answer4}"
                              checked>
                        <c:out value="${questionsList.get(currentQuestionIndex).answer4}"/></p>
                </c:when>
                <c:otherwise>
                    <p><input name="usersAnswer" type="radio"
                              value="${questionsList.get(currentQuestionIndex).answer4}">
                        <c:out value="${questionsList.get(currentQuestionIndex).answer4}"/></p>
                </c:otherwise>
            </c:choose>
            <input type="hidden" name="command" value="passTestToPassTestFragment">
        </form>
        <button name="action" value="finishPassingTest" form="form">Finish</button>

    </div>
    <div>
        <table border="1" cellpadding="5" cellspacing="5">
            <tr>
                <c:if test="${currentQuestionIndex != 0}">
                    <button name="currentQuestionIndex" value="${currentQuestionIndex-1}" form="form">Previous</button>
                </c:if>
                <c:forEach begin="1" end="${questionsList.size()}" var="i">
                    <c:choose>
                        <c:when test="${currentQuestionIndex eq i-1}">
                            <td>${i}</td>
                        </c:when>
                        <c:otherwise>
                            <button name="currentQuestionIndex" value="${i-1}" form="form">${i}</button>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${currentQuestionIndex lt questionsList.size()-1}">
                    <button name="currentQuestionIndex" value="${currentQuestionIndex+1}" form="form">Next</button>
                </c:if>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
