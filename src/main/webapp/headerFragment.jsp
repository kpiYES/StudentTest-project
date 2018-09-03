<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 16.08.2018
  Time: 21:44
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

<div class="header">
    <div class="header_hello">
        <h2>Welcome <ut:myTag firstName="${sessionScope.currentUser.firstName}"
                      lastName="${sessionScope.currentUser.lastName}"/></h2>
    </div>
    <div class="header_menu">
        <ul>
            <li>
                <a href="dispatcher?command=logOut"><fmt:message key="header.logout"/></a>
            </li>
            <li>
                <a href="dispatcher?command=locale&locale=ru"><fmt:message key="header.ru"/></a>
            </li>
            <li>
                <a href="dispatcher?command=locale&locale=en"><fmt:message key="header.en"/></a>
            </li>
        </ul>
    </div>

</div>
</body>
</html>
