<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 22.08.2018
  Time: 4:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
HELLOOOOOOOOOOOOOO
</body>


<c:forEach var="employee" items="${requestScope.questionSet}">
<tr>
    <td>${employee.employeeId}</td>
    <td>${employee.employeeName}</td>
    <td>${employee.salary}</td>
    <td>${employee.deptName}</td>
</tr>
</c:forEach>

</html>
