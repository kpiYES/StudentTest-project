<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 20.08.2018
  Time: 12:50
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
<div class="vertical-menu">
    <p class="list-title">Tests</p>


    <ul class="scroll-list">
        <c:forEach var="test" items="${sessionScope.testSet}">
            <li>
                <a href="dispatcher?command=showTestTestsFragment&testId=${test.id}"><c:out
                        value="${test.name}"/></a>
            </li>
        </c:forEach>
    </ul>

</div>
<div>
    <form action="dispatcher" method="get">
        <button name="command" value="createTestTestsFragment">Create new test</button>
    </form>
</div>

<c:if test="${requestScope.subSubPageFragment!=null}">
    <c:import url="${requestScope.subSubPageFragment}"/>
</c:if>

</body>
</html>
