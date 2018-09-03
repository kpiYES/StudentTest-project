<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 02.09.2018
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <div class="vertical-menu">
        <p class="list-title">Subjects</p>

        <ul>
            <c:forEach var="subject" items="${sessionScope.subjectSet}">
                <li>
                    <a href="dispatcher?command=showListOfPassedTestsUsersFragment&subjectId=${subject.id}"><c:out
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
