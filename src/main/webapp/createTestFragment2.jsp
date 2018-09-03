<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 20.08.2018
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="result">
<div>
    <div>
        <form method="post" action="dispatcher" name="form" id="form">
            <p> Test name: <input type="text" value="${sessionScope.newTestName}" name="newTestName" size="30"></p>

            <c:forEach var="question" items="${sessionScope.questionSet}">

                <c:choose>

                    <c:when test="${sessionScope.chosenQuestionsSet.contains(question.id)}">

                        <p> <input type="checkbox" id="question" name="question"
                                   value="${question.id}" checked/><c:out value="${question.query}"/></p>
                    </c:when>
                    <c:otherwise>

                       <p> <input type="checkbox" id="question" name="question"
                               value="${question.id}"/><c:out value="${question.query}"/></p>
                    </c:otherwise>
                            </c:choose>

                <c:choose>
                <c:when test="${question.answer1 eq question.correctAnswer}">
                <tr><td>
                    <p><input type="radio" checked disabled><c:out value="${question.answer1}"/></p>
                </td></tr>
                </c:when>
                <c:otherwise>
                    <p><input type="radio" disabled><c:out value="${question.answer1}"/></p>
                </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${question.answer2 eq question.correctAnswer}">
                        <tr><td>
                            <p><input type="radio" checked disabled><c:out value="${question.answer2}"/></p>
                        </td></tr>
                    </c:when>
                    <c:otherwise>
                        <p><input type="radio" disabled><c:out value="${question.answer2}"/></p>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${question.answer3 eq question.correctAnswer}">
                        <tr><td>
                            <p><input type="radio" checked disabled><c:out value="${question.answer3}"/></p>
                        </td></tr>
                    </c:when>
                    <c:otherwise>
                        <p><input type="radio" disabled><c:out value="${question.answer3}"/></p>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${question.answer4 eq question.correctAnswer}">
                        <tr><td>
                            <p><input type="radio" checked disabled><c:out value="${question.answer4}"/></p>
                        </td></tr>
                    </c:when>
                    <c:otherwise>
                        <p><input type="radio" disabled><c:out value="${question.answer4}"/></p>
                    </c:otherwise>
                </c:choose>
            </table>
                </c:forEach>

            <input type="hidden" name="command" value="createTestTestsFragment">
        </form>
    </div>
    <button name="action" value="finishCreatingTestTestsFragment" form="form">Finish test creating</button>

</div>
<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <c:if test="${requestScope.currentPage != 1}">
            <button name="page" value="${requestScope.currentPage-1}" form="form">Previous</button>
        </c:if>
        <c:forEach begin="1" end="${requestScope.numberOfPages}" var="i">
            <c:choose>
                <c:when test="${requestScope.currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <button name="page" value="${i}" form="form">${i}</button>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${requestScope.currentPage lt requestScope.numberOfPages}">
            <button name="page" value="${requestScope.currentPage + 1}" form="form">Next</button>
        </c:if>
    </tr>
</table>

</div>
</body>
</html>
