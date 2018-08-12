<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 31.07.2018
  Time: 8:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>$Index$</title>
</head>
<body>
<div>
    <h1> WELCOME! </h1>
</div>

<div>
    <p>
        We glad to see you!
    </p>
</div>

<div class="form">
    <form class="login" method="post" action="dispatcher">
        <input type="hidden" name="command" value="login">
        <input type="text" placeholder="email" name="email" size="30">
        <input type="password" placeholder="********" name="password" size="30">
        <input type="submit" value="Submit">
    </form>
</div>

<c:if test="${ requestScope.errorMsg!=null}">
    <c:import url="errorFragment.jsp"/>
</c:if>

</body>
</html>
