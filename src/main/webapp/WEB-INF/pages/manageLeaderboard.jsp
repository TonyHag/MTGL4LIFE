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
    <jsp:include page="include.jsp" />

    <title>Create Leaderboard</title>
</head>
<body>
<jsp:include page="nav.jsp">
    <jsp:param name="user" value="${user}"></jsp:param>
</jsp:include>

<div class="container text-left" role="main">

    <h1>Manage Leaderboard</h1>

    <h2>${leaderboard.name}  </h2>
    <p>${leaderboard.description}</p>

    <div>
        <form action="/leaderboard/manage/${leaderboard.id}/invite" method="post">
        <input type ="text" name="invitePlayer" /><input type="submit" value="Invite" class="btn btn-sm btn-primary"><p>${leaderboard.inviteErrorMessage}</p>
        </form>
    </div>


    <h3>Players</h3>
    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Wins</th>
                <th>Losses</th>
                <th>Total</th>
                <th>Win %</th>
            </tr>
            </thead>

            <c:forEach items="${leaderboard.playerStats}" var="playerStat">
                <tr>
                    <td> ${playerStat.username}     </td>
                    <td> ${playerStat.wins}         </td>
                    <td> ${playerStat.losses}       </td>
                    <td> ${playerStat.total}        </td>
                    <td><fmt:formatNumber type="number" maxFractionDigits="1" value="${playerStat.winPercentage}"/></td>
                    <td>
                        <c:if test="${playerStat.userId != leaderboard.ownerId}" >
                            <form action="/leaderboard/manage/${leaderboard.id}/removePlayer"><input type ="hidden" name="removePlayer" value="${playerStat.username}"/> <input type="submit" value="Remove" class="btn btn-sm btn-warning" ></form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

</div>






</body>
</html>
