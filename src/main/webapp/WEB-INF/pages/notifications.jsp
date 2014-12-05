<%--
  Created by IntelliJ IDEA.
  User: eirikskogland
  Date: 04.12.14
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Notifications</title>
</head>
<body>

<h1>Your notifications</h1>

<table>
    <c:forEach items="${notifications}" var="notification">
        <tr><td>Winner: ${notification.winner}</td> <td><form action="/notifications/accept/${notification.id}"><input type="submit" value="Accept"></form></td></tr>
    </c:forEach>
</table>

</body>
</html>
