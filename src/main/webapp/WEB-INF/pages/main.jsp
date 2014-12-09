<%--
  Created by IntelliJ IDEA.
  User: eirikskogland
  Date: 03.12.14
  Time: 12:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="include.jsp" />

    <title>Main Menu</title>
</head>
<body>
<jsp:include page="nav.jsp">
    <jsp:param name="user" value="${user}"></jsp:param>
</jsp:include>
    <h1>Welcome ${user}</h1>
    <table>

        <tr><td><a href="/lobby">Start match</a></td></tr>
        <tr><td><a href="/stats/${user}">Stats</a></td></tr>
        <tr><td><a href="/notifications">Notifications</a></td></tr>
        <tr><td><a href="/main/logout">Log out</a></td></tr>
    </table>

</body>
</html>
