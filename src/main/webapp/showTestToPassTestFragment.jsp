<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 28.08.2018
  Time: 11:22
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
<div class="test-description">
    <p> Test name: <c:out value="${sessionScope.test.name}"/></p>
    <p> Count of questions: <c:out value="${sessionScope.test.questionSet.size()}"/></p>

    <form>
        <input type="hidden" name="command" value="passTestToPassTestFragment">
        <input type="submit" value="Pass test">
    </form>
</div>
</body>
</html>
