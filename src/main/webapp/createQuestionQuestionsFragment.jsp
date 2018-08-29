<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 16.08.2018
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div>
    <form method="post" action="dispatcher">
        <p>Subject: <c:out value="${sessionScope.subject.name}"/></p>
        <p>Query<Br>
            <textarea name="query" cols="40" rows="5" required></textarea></p>
        <p> Answer 1: <input type="text" name="answer1" size="40" required></p>
        <p> Answer 2: <input type="text" name="answer2" size="40" required></p>
        <p> Answer 3: <input type="text" name="answer3" size="40" required></p>
        <p> Answer 4:<input type="text" name="answer4" size="40"></p>
        <p> Correct answer:<input type="text" name="correctAnswer" size="40" required></p>
        <input type="hidden" name="command" value="createQuestion" >
        <input type="submit" value="Create question">
    </form>
</div>

</body>
</html>
