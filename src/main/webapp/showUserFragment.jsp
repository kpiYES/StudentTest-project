<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 18.08.2018
  Time: 5:19
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
<div class="user_form">
<form method="post" action="dispatcher">
    <p> FirstName: <input type="text" name="firstName" size="30" value="${sessionScope.userDTO.firstName}" readonly/></p>
    <p> LastName: <input type="text" name="lastName" size="30" value="${sessionScope.userDTO.lastName}" readonly/></p>
    <p> eMail: <input type="text" name="mail" size="30" value="${sessionScope.userDTO.mail}" readonly/></p>
    <select name="userRole" contenteditable="true">
        <option disabled selected name="currentRole"><c:out value="${sessionScope.userDTO.role.name}"/></option>
        <c:forEach var="role" items="${sessionScope.roleSet}">
            <option  value="${role.name}"><c:out value="${role.name}"/></option>
        </c:forEach>
    </select>
    <input type="hidden" name="command" value="updateUserRole">
    <input type="submit" value="Update user role">
</form>
<form method="post" action="dispatcher">
    <input type="hidden" name="command" value="deleteUser">
    <input type="submit" value="Delete user">
</form>
</div>
<c:if test="${requestScope.Massage!=null}">
<c:out value="${requestScope.Massage}"/>
</c:if>
</body>
</html>
