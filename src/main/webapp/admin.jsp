<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 11.08.2018
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ut" uri="/WEB-INF/tagLib.tld" %>

<html>
<head>
    <title>Title</title>
    <link href="css/main.css" rel="stylesheet" type="text/css">
    <fmt:setLocale value="${loc}"/>
    <fmt:setBundle basename="localization"/>
</head>
<body>
<c:import url="headerFragment.jsp"/>
<div>
    <div class="admin_right_menu">
        <div class="admin_item_menu">
            <a href="dispatcher?command=toShowListOfUsersFragment"><fmt:message key="admin.menu.users"/></a>
        </div>
        <div class="admin_item_menu">
            <a href="dispatcher?command=showListOfSubjectsTestsFragment"><fmt:message key="admin.menu.tests"/></a>
        </div>
        <div class="admin_item_menu">
            <a href="dispatcher?command=showListOfSubjectsQuestionsFragment"><fmt:message
                    key="admin.menu.questions"/></a>
        </div>
    </div>
    <div class="vertical-menus-block">
        <c:if test="${requestScope.pageFragment!=null}">
            <c:import url="${requestScope.pageFragment}"/>
        </c:if>
        <c:if test="${requestScope.msg!=null}">
            <div class="message">
                <c:out value="${requestScope.msg}"/>
            </div>
        </c:if>
    </div>
    <c:if test="${requestScope.subSubPageFragment!=null}">
        <c:import url="${requestScope.subSubPageFragment}"/>
    </c:if>



</div>



</body>
</html>
