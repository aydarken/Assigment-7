<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="style.css">
    <title>Registration</title>
</head>
<body>
<nav>
    <a href="index.jsp">Main page</a>
    <a href="login.jsp">LogIn</a>
    <a href="logout">Log out</a>
</nav>

<form action="registration" method="POST">
    <%=(request.getAttribute("errMessage") == null) ? ""
            : request.getAttribute("errMessage")%>
    <h2>Registration</h2>
    <label>Email: </label><br>
    <input type="email" name="email"><br><br>
    <label>Username: </label><br>
    <input type="text" name="username"><br><br>
    <label>Password: </label><br>
    <input type="password" name="password"><br><br>
    <button>Register</button>
</form>
</body>
</html>