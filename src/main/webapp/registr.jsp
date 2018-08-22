<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 20.08.2018
  Time: 1:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<div>
    <form method="post" action="dispatcher">

        <input type="hidden" name="command" value="registr">
        <p> First Name: <input type="text" name="firstName" size="30" required></p>
        <p> Last Name: <input type="text"  name="lastName" size="30" required></p>
        <p> EMail: <input type="email" name="email" size="30" required ></p>
        <p> Password:<input type="password" name="password" size="30" required minlength="8"></p>
        <p> Confirm Password:<input type="password" name="confirmPassword" size="30" required minlength="8"></p>
        <input type="submit" value="Registration">
    </form>
</div>
    <%--<table>--%>
        <%--<tr>--%>
            <%--<th><small>--%>
                <%--<input type="submit" name="save" value="Сохранить">--%>
            <%--</small>--%>
            <%--<th><small>--%>
                <%--<input type="submit" name="cancel" value="Выйти">--%>
            <%--</small>--%>
    <%--</table>--%>


</body>
</html>
