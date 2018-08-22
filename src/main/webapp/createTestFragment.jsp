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

<div>
    <div>
        <form method="post" action="dispatcher" name="form" id="form">
            <p> Test name: <input type="text" value="${sessionScope.newTestName}" name="newTestName" size="30"></p>

            <c:forEach var="question" items="${requestScope.questionSet}">

            <ul>
                    <%--<c:choose>--%>
                    <%--<c:when test="${sessionScope.chosenQuestionsSet.size()>0}">--%>

                    <%--<c:forEach var="chosenQuestionsSet" items="${sessionScope.chosenQuestionsSet}">--%>


                <c:out value="${sessionScope.chosenQuestionsSet}"/>
                <c:out value="${question.id}"/>
                        <c:out value="${sessionScope.chosenQuestionsSet.contains(question.id)}"/>
                <c:choose>
                    <c:when test="${sessionScope.chosenQuestionsSet.contains(question.id)}">
                        <c:out value="2"/>
                        <li>
                            <input type="checkbox" id="question" name="question"
                                   value="${question.id}" checked/>
                            <label for="${question.id}">${question.query}</label>
                            <p><c:out value="${question.answer1}"/></p>
                            <p><c:out value="${question.answer2}"/></p>
                            <p> <c:out value="${question.answer3}"/><p>
                            <p><c:out value="${question.answer4}"/><p>
                            <p> <c:out value="${question.correctAnswer}"/><p>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <c:out value="otherwise2"/>
                        <li>
                            <input type="checkbox" id="question" name="question"
                                   value="${question.id}"/>
                            <label for="${question.id}">${question.query}</label>
                            <p><c:out value="${question.answer1}"/></p>
                            <p><c:out value="${question.answer2}"/></p>
                            <p> <c:out value="${question.answer3}"/><p>
                            <p><c:out value="${question.answer4}"/><p>
                            <p> <c:out value="${question.correctAnswer}"/><p>
                        </li>
                    </c:otherwise>
                </c:choose>
                </c:forEach>
            </ul>
            <input type="hidden" name="command" value="toCreateTestFragment">
        </form>
    </div>
    <button name="action" value="createTest" form="form">Finish test creating</button>


    <c:if test="${requestScope.currentPage != 1}">
        <button name="page" value="${requestScope.currentPage-1}" form="form">Previous</button>
    </c:if>

</div>
<table border="1" cellpadding="5" cellspacing="5">
    <tr>
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


</body>
</html>
