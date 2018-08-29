<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 22.08.2018
  Time: 4:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="questions">
    <form method="post" action="dispatcher" name="form" id="form">
        <p> Test name <c:out value="${sessionScope.test.name}"/></p>

        <c:forEach var="question" items="${requestScope.questionSet}">
            <table>
                <tr>
                    <p><c:out value="${question.query}"/></p>
                    <td><c:out value="${question.answer1}"/></td>
                    <td><c:out value="${question.answer2}"/></td>
                    <td><c:out value="${question.answer3}"/></td>
                    <td><c:out value="${question.answer4}"/></td>
                    <td><c:out value="${question.correctAnswer}"/></td>
                </tr>
            </table>
        </c:forEach>
        <input type="hidden" name="command" value="showTestTestsFragment">
    </form>

    <form>
        <input type="hidden" name="command" value="deleteTest">
        <input type="submit" value="Delete test">
    </form>

    <c:if test="${requestScope.currentPage != 1}">
        <button name="page" value="${requestScope.currentPage-1}" form="form">Previous</button>
    </c:if>

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
</div>
</body>

</html>
