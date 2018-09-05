<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 28.08.2018
  Time: 10:52
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
        <p class="list-title">Subjects</p>

        <ul>
            <c:forEach var="subject" items="${sessionScope.subjectSet}">
                <li>
                    <a href="dispatcher?command=showListOfTestsToPassTestFragment&subjectId=${subject.id}"><c:out
                            value="${subject.name}"/></a>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
<c:if test="${requestScope.subPageFragment!=null}">
    <c:import url="${requestScope.subPageFragment}"/>
</c:if>
</body>
</html>
