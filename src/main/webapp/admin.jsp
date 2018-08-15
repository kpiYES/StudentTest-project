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
</head>
<body>

<div class="rightForm">
    <form method="post" action="dispatcher" enctype="multipart/form-data">
        <p> Fill out this form, if yot want to add new question </p>
        <p> All fields are necessary! </p>
        <select name="subject">
            <option disabled selected>Choose subject</option>
            <option value="To dataChange">editor</option>
            <option value="To mainPage">user</option>
        </select>
        <p> Name: <input type="text" placeholder="Frank Darabont" name="name" size="30"></p>
        <p> Day of birth: <input type="text" placeholder="1965-03-25" name="day_of_birth" size="30"></p>
        <p> Image: <input type="file"  name="image" size="30"></p>
        <input type="submit" name="command" value="Create director">
    </form>
</div>

</body>
</html>
