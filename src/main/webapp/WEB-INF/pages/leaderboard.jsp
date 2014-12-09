<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: eirikskogland
  Date: 08.12.14
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="include.jsp" />

    <title>Leaderboard: ${leaderboard.name}</title>
</head>
<body>
<jsp:include page="nav.jsp">
    <jsp:param name="user" value="${user}"></jsp:param>
</jsp:include>
<h1>Leaderboard: ${leaderboard.name}</h1>

<p>${leaderboard.description}</p>

<table>
    <tr><th>Players </th></tr>
    <tr>
        <td>Name</td>
        <td>Wins</td>
        <td>Losses</td>
        <td>Total</td>
        <td>Win %</td>
    </tr>

    <c:forEach items="${leaderboard.playerStats}" var="playerStat">
        <tr><td> ${playerStat.username}     </td>
            <td> ${playerStat.wins}         </td>
            <td> ${playerStat.losses}       </td>
            <td> ${playerStat.total}        </td>
            <td><fmt:formatNumber type="number" maxFractionDigits="1" value="${stats.winPercentage}"/></td>         </tr>
    </c:forEach>
</table>

</body>
</html>
