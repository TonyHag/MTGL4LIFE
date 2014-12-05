<%--
  Created by IntelliJ IDEA.
  User: eirikskogland
  Date: 02.12.14
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Register</title>
</head>
<body>

<h1>Register</h1>

<form action="/register" method="post">
    <table>
        <tr> <td>Username: </td>        <td><input type="text" name="username" value="${validUsername}"></td>   <td>${usernameError}</td></tr>
        <tr> <td>Password: </td>        <td><input type="password" name="password1"></td>                       <td>${password1Error}</td></tr>
        <tr> <td>Re-type password: </td><td><input type="password" name="password2"></td>                       <td>${password2Error}</td></tr>
        <tr> <td>email: </td>           <td><input type="email" name="email" value="${validEmail}"></td>        <td>${emailError}</td></tr>
        <tr> <td><input type="submit" value="Register"> </td> </tr>
    </table>

</form>

</body>
</html>
