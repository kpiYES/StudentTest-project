<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 20.08.2018
  Time: 1:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Registration</title>
    <link href="css/main.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="registration-form">
    <h4>Fill out the registration form.</h4>
    <form method="post" action="dispatcher">
        <input type="hidden" name="command" value="registration">
        <div class="field-form">
            <label class="label-form" for="firstName">First Name</label>
            <input type="text" name="firstName" id="firstName" size="30" required>
        </div>
        <div class="field-form">
            <label class="label-form" for="lastName">Last Name</label>
            <input type="text" name="lastName" id="lastName" size="30" required>
        </div>
        <div class="field-form">
            <label class="label-form" for="email">Email</label>
            <input type="email" name="email" id="email" size="30" required>
        </div>
        <div class="field-form">
            <label class="label-form" for="password">Password</label>
            <input type="password" name="password" id="password" size="30" required minlength="8">
        </div>
        <div class="field-form">
            <label class="label-form" for="confirmPassword">Confirm password</label>
            <input type="password" name="confirmPassword" id="confirmPassword" size="30" required minlength="8">
        </div>
        <div class="field-form">
            <input type="submit" id="submit" value="Submit">
        </div>
    </form>
    <c:if test="${requestScope.errorMsg!=null}">
        <c:out value="${requestScope.errorMsg}"/>
    </c:if>
</div>

</body>
</html>
