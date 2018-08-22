<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 11.08.2018
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="css/main.css" rel="stylesheet" type="text/css">
</head>
<body>



<div class="admin_menu">
    <a href="dispatcher?command=toShowListOfUsersFragment">Users</a>
</div>
<div class="admin_menu">
    <a href="dispatcher?command=toShowListOfSubjectsFragment">Tests</a>
</div>
<div class="admin_menu">
    <a href="dispatcher?command=toShowQuestionsFragment">Questions</a>
</div>
<c:if test="${requestScope.pageFragment!=null}">
    <c:import url="${requestScope.pageFragment}"/>
</c:if>






</body>
</html>
