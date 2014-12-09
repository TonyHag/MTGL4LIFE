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

<div class="container" role="main">

    <h1>${leaderboard.name}</h1>

    <p>${leaderboard.description}</p>

    <h3>Players</h3>

    <div class="table-responsive">
        <table class="table ">
            <thead>
            <tr>
                <th>Name</th>
                <th>Wins</th>
                <th>Losses</th>
                <th>Total</th>
                <th>Win %</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${leaderboard.playerStats}" var="playerStat">
                <tr>
                    <td> ${playerStat.username}     </td>
                    <td> ${playerStat.wins}         </td>
                    <td> ${playerStat.losses}       </td>
                    <td> ${playerStat.total}        </td>
                    <td><fmt:formatNumber type="number" maxFractionDigits="1" value="${stats.winPercentage}"/></td>
                </tr>
            </c:forEach>

            </tbody>

        </table>
    </div>



</div>


</body>
</html>
