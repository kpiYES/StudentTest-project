<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 18.08.2018
  Time: 5:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <link href="css/main.css" rel="stylesheet" type="text/css">
    <fmt:setLocale value="${loc}"/>
    <fmt:setBundle basename="localization"/>
</head>
<body>
<div class="user_form">
    <form method="post" action="dispatcher">
        <p> <fmt:message key="admin.menu.usersFragment.firstname"/> <input type="text" name="firstName" size="30" value="${sessionScope.userDTO.firstName}"
                              readonly/></p>
        <p> <fmt:message key="admin.menu.usersFragment.lastname"/> <input type="text" name="lastName" size="30" value="${sessionScope.userDTO.lastName}" readonly/>
        </p>
        <p> <fmt:message key="admin.menu.usersFragment.email"/> <input type="text" name="mail" size="30" value="${sessionScope.userDTO.mail}" readonly/></p>
        <select name="userRole" contenteditable="true">
            <option disabled selected name="currentRole"><c:out value="${sessionScope.userDTO.role.name}"/></option>
            <c:forEach var="role" items="${sessionScope.roleSet}">
                <option value="${role.name}"><c:out value="${role.name}"/></option>
            </c:forEach>
        </select>
        <input type="hidden" name="command" value="updateUserRole">
        <input type="submit" value="admin.menu.usersFragment.update">
    </form>
    <form method="post" action="dispatcher">
        <input type="hidden" name="command" value="showListOfSubjectsUsersFragment">
        <input type="submit" value="Show users passed tests">
    </form>
</div>
<c:if test="${requestScope.message!=null}">
    <div class="message">
        <c:out value="${requestScope.message}"/>
    </div>
</c:if>
</body>
</html>
