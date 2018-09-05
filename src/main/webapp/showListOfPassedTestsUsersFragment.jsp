<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 02.09.2018
  Time: 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="vertical-menu">
    <p class="list-title">Tests</p>
    <ul class="scroll-list">
        <c:forEach var="passedTest" items="${sessionScope.passedTestSet}">
            <li>
                <a href="dispatcher?command=showPassedTestUsersFragment&passedTestId=${passedTest.id}"><c:out
                        value="${passedTest.test.name}"/></a>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
