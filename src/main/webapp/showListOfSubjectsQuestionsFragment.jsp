<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 26.08.2018
  Time: 1:16
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

<c:if test="${requestScope.subPageFragment!=null}">
    <c:import url="${requestScope.subPageFragment}"/>
</c:if>

<div class="vertical-menu">
    <ul>
        <p class="active">Subjects</p>
        <c:forEach var="subject" items="${sessionScope.subjectSet}">
            <li>
                <a href="dispatcher?command=showListOfQuestionsQuestionsFragment&subjectName=${subject.name}"><c:out
                        value="${subject.name}"/></a>
            </li>
        </c:forEach>
    </ul>
</div>

</body>
</html>