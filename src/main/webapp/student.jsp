<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 06.08.2018
  Time: 4:30
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>$Student$</title>
    <link href="css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
$Student$
</body>
<c:out value="${sessionScope.currentUserDTO.firstName}"/>
<a href="dispatcher?command=getPassedTestsCommand">Get my passed tests </a>
</html>
