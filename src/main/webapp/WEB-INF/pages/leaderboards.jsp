<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: eirikskogland
  Date: 08.12.14
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Leaderboards</title>
</head>
<body>
<jsp:include page="nav.jsp">
    <jsp:param name="user" value="${user}"></jsp:param>
</jsp:include>

<h1> Leaderboards </h1>

<table>
    <tr>
        <td><a href="/leaderboard/create">Create leaderboard</a></td>
    </tr>
</table>

<h3>Your leaderboards</h3>

<table>
    <c:forEach items="${leaderboardInfos}" var="info">
        <tr>
            <td><a href="/leaderboard/${info.id}">${info.name}</a> <c:if test="${info.owner == true}"> <a href="/leaderboard/manage/${info.id}"><button>Manage</button></a>  </c:if></td>
        </tr>

    </c:forEach>

</table>



</body>
</html>
