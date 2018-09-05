<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 26.08.2018
  Time: 1:39
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
<div>
    <div class="vertical-menu">
        <p class="list-title"><c:out value="${sessionScope.subject.name}"/></p>

        <ul class="scroll-list">
            <c:forEach var="question" items="${sessionScope.questionSet}">
                <li>
                    <a href="dispatcher?command=showQuestionQuestionsFragment&questionId=${question.id}"><c:out
                            value="${question.query}"/></a>
                </li>
            </c:forEach>
        </ul>
    </div>

    <div>
        <form action="dispatcher" method="get">
            <button name="command" value="createQuestionQuestionsFragment">Create new question</button>
        </form>
    </div>
</div>


<c:if test="${requestScope.errorMsg!=null}">
    <c:out value="${requestScope.errorMsg}"/>
</c:if>


</body>
</html>
