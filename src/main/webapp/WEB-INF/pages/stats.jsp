<%--
  Created by IntelliJ IDEA.
  User: eirikskogland
  Date: 05.12.14
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="include.jsp" />

    <title>Stats</title>
</head>
<body>
<jsp:include page="nav.jsp">
    <jsp:param name="user" value="${user}"></jsp:param>
</jsp:include>

<div class="container text-left" role="main">

    <c:choose>
        <c:when test="${stats != null}" >
            <h1>Statistics for ${stats.username}</h1>

            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th>Wins</th>
                        <th>Losses</th>
                        <th>Total</th>
                        <th>Win%</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${stats.wins}</td>
                        <td>${stats.losses}</td>
                        <td>${stats.total}</td>
                        <td><fmt:formatNumber type="number" maxFractionDigits="1" value="${stats.winPercentage}"/></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </c:when>
        <c:otherwise>
            <h1>Player not found</h1>
        </c:otherwise>
    </c:choose>

    <form action="/stats/search" method="post">
        <input type="text" name="searchForPlayer" type="search" placeholder="Find stats for a player">
        <input type="submit" value="Search">
    </form>

</div>


</body>
</html>
