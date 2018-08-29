<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 31.07.2018
  Time: 8:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>$Index$</title>
    <link href="css/main.css" rel="stylesheet" type="text/css">
</head>
<body>


<fmt:setLocale value="en"/>
<fmt:setBundle basename="localization"/>
<div>
    <h1> WELCOME! </h1>
</div>

<div>
    <form class="login" method="post" action="dispatcher">
        <input type="hidden" name="command" value="login">
        <input type="email" placeholder="<fmt:message key="login.label.email"/>" name="email" size="30" required>
        <input type="password" placeholder="********" name="password" size="30" required>
        <input type="submit" value="Sing in">
    </form>
</div>
<div>
    <form action="dispatcher" method="get">
        <button name="command" value="toRegistrPage">Registration</button>
    </form>
</div>
<c:if test="${requestScope.errorMsg!=null}">
    <c:out value="${requestScope.errorMsg}"/>
</c:if>

</body>
</html>
