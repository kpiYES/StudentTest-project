<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 18.08.2018
  Time: 2:55
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
    <ul>
        <p class="active">Users</p>
        <c:forEach var="userDTO" items="${sessionScope.userDTOSet}">
            <li>
                <a href="dispatcher?command=toShowUserFragment&userMail=${userDTO.mail}"><c:out
                        value="${userDTO.mail}"/></a>
            </li>
        </c:forEach>
    </ul>
</div>

<c:if test="${requestScope.subPageFragment!=null}">
    <c:import url="${requestScope.subPageFragment}"/>
</c:if>
</body>
</html>
