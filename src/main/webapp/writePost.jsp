<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Post</title>
</head>
<body>

<%@ include file="header.jsp" %>
<form action="/writePost" method="POST">
    <p> <%=(request.getAttribute("errMessage") == null) ? "": request.getAttribute("errMessage")%></p>
    <h2>Write post</h2>
    <label>Title:</label><br>
    <input type="text" name="title"><br><br>
    <label>Content: </label><br>
    <textarea name="content"></textarea><br><br>
    <button>Publish</button>
</form>

</body>
</html>