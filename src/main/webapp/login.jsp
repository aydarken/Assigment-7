<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="style.css">
    <title>Log In</title>
</head>
<body>
<nav>
    <a href="index.jsp">Main page</a>
    <a href="registration.jsp">Register</a>
    <a href="logout">Log out</a>
</nav>
<form action="login" method="POST">
    <p сlass="message"><%=(request.getAttribute("message") == null) ? ""
            : request.getAttribute("message")%></p>
    <h2>Log In</h2>
    <label>Username: </label><br>
    <input type="text" name="username"><br><br>
    <label>Password: </label><br>
    <input type="password" name="password"><br><br>
    <button>Log In</button>
</form>
</body>
</html>