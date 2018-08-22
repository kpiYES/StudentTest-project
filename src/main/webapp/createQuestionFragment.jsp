<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 16.08.2018
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div>
    <form method="post" action="dispatcher">
        <p> Name of director: <input type="text" placeholder="Karl Ponsey" name="name_of_director" size="30"></p>
        <p> Title: <input type="text" placeholder="Titanic" name="title" size="30"></p>
        <p> Runtime: <input type="text" placeholder="123" name="runtime" size="30"></p>
        <p> Description: <input type="file" name="description" size="30"></p>
        <p> Image: <input type="file" name="image" size="30"></p>
        <p> Genres:<input type="text" placeholder="comedy, western, crime" name="genres" size="30"></p>
        <input type="submit" name="command" value="Create movie">
    </form>
</div>

</body>
</html>
