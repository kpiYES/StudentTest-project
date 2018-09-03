<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 06.08.2018
  Time: 4:30
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>$Student$</title>
    <link href="css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
<c:import url="headerFragment.jsp"/>
<c:set var="currentURL" value="${pageContext.request.requestURL}"/>
<c:out value="${currentURL}"/>
<div>
    <div class="admin_right_menu">
        <div class="admin_item_menu">
            <a href="dispatcher?command=showListOfSubjectsToPassTestFragment">To pass a test </a>
        </div>
        <div class="admin_item_menu">
            <a href="dispatcher?command=showListOfSubjectsPassedTestsFragment">My passed tests </a>
        </div>
    </div>
<div class="vertical-menus-block">
    <c:if test="${requestScope.pageFragment!=null}">
        <c:import url="${requestScope.pageFragment}"/>
    </c:if>
</div>
    <c:if test="${requestScope.subSubPageFragment!=null}">
    <c:import url="${requestScope.subSubPageFragment}"/>
    </c:if>
    </div>


</body>
</html>
