<%--
  Created by IntelliJ IDEA.
  User: eirikskogland
  Date: 02.12.14
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <jsp:include page="include.jsp" />

    <title>Login</title>

   <script src="/resources/js/test.js" type="text/javascript" ></script>
</head>
<body>

<h1>Login</h1>

<form action="/login" method="post">

    <table>
        <p>${loginError}</p>
        <tr><td>Username: </td> <td><input type="text" name="username"/></td></tr>
        <tr><td>Password: </td> <td><input type="password" name="password" /></td></tr>
        <tr><td><input type="submit" value="Log in" /></td> <td>or <a href="/register">register here!</a></td> </tr>
    </table>

</form>
</body>
</html>
