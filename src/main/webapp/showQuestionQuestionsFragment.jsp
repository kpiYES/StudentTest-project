<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 26.08.2018
  Time: 2:10
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

<table>

    <tr>
        <td>
            <c:out value="${sessionScope.question.query}"/>
        </td>
    </tr>
    <tr>
        <td>
            <c:out value="${sessionScope.question.answer1}"/>
        </td>
        <td>
            <c:out value="${sessionScope.question.answer2}"/>
        </td>
    </tr>
    <tr>
        <td>
            <c:out value="${sessionScope.question.answer3}"/>
        </td>
        <td>
            <c:out value="${sessionScope.question.answer4}"/>
        </td>
    </tr>
    <tr>
        <td>
            <c:out value="${sessionScope.question.correctAnswer}"/>
        </td>
    </tr>
</table>

<c:if test="${not empty sessionScope.testSet}">
    <p><c:out value="Tests that contain this question:"/></p>
    <c:forEach var="test" items="${sessionScope.testSet}">
        <c:out value="${test.name}"/>
    </c:forEach>
    <form>
        <input type="submit" title="First you need to remove the tests that contain this question."
               disabled value="Delete question">
    </form>
</c:if>

<c:if test="${empty sessionScope.testSet}">
    <p><c:out value="No one test contains this question."/></p>

    <form>
        <input type="hidden" name="command" value="deleteQuestion">
        <input type="submit" value="Delete question">
    </form>
</c:if>



</body>
</html>
