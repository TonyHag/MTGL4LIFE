<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: eirikskogland
  Date: 08.12.14
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Leaderboard</title>
</head>
<body>
<jsp:include page="nav.jsp">
    <jsp:param name="user" value="${user}"></jsp:param>
</jsp:include>
<h1>Manage Leaderboard</h1>

<table>

    <tr> <th>${leaderboard.name}        </th>    </tr>
    <tr> <td>${leaderboard.description} </td>    </tr>

</table>

<form action="/leaderboard/manage/${leaderboard.id}/invite" method="post">
    <table>
        <tr><td><input type ="text" name="invitePlayer" /></td> <td> <input type="submit" value="Invite"></td> <td>${leaderboard.inviteErrorMessage}</td></tr>
    </table>
</form>

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
            <td><fmt:formatNumber type="number" maxFractionDigits="1" value="${stats.winPercentage}"/></td>
            <form action="/leaderboard/manage/${leaderboard.id}/removePlayer"><input type ="hidden" name="removePlayer" value="${playerStat.username}"/> <td> <input type="submit" value="Remove"></td></form> </tr>
    </c:forEach>
</table>



</body>
</html>
