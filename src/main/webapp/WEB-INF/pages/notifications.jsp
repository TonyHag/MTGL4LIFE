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
    <jsp:include page="include.jsp" />

    <title>Notifications</title>
</head>

<body>
<jsp:include page="nav.jsp">
    <jsp:param name="user" value="${user}"></jsp:param>
</jsp:include>

<div class="container text-left">
    <h1>Your notifications</h1>
    <p>Here you will find game confirmation requests and leaderboard invitations.</p>

    <h3>Game Confirmations</h3>
    <div class="table-responsive">
        <table class="table">

            <c:forEach items="${notifications}" var="notification">
                <c:if test="${notification.type == 'gameConfirmation'}">
                    <tr>
                        <td>${notification.message}</td>
                        <td><form action="/gameConfirmation/accept/${notification.gameId}"><input type="hidden" name="notificationId" value="${notification.id}"/> <input type="submit" value="Accept" class="btn btn-sm btn-primary"></form></td>
                        <td><form action="/gameConfirmation/reject/${notification.gameId}"><input type="hidden" name="notificationId" value="${notification.id}"/> <input type="submit" value="Reject" class="btn btn-sm btn-danger"></form></td>
                    </tr>
                </c:if>
            </c:forEach>

        </table>


    </div>
    <br>


    <h3>Leaderboard invitations </h3>

    <div class="table-responsive">
        <table class="table">

            <c:forEach items="${notifications}" var="notification">
                <c:if test="${notification.type == 'leaderboardInvitation'}">
                    <tr>
                        <td>${notification.message}</td>
                        <td><form action="/leaderboard/accept/${notification.leaderboardId}" method="post"><input type="hidden" name="notificationId" value="${notification.id}"/> <input type="submit" value="Accept" class="btn btn-sm btn-primary"></form></td>
                        <td><form action="/leaderboard/reject/${notification.leaderboardId}" method="post"><input type="hidden" name="notificationId" value="${notification.id}"/> <input type="submit" value="Reject" class="btn btn-sm btn-danger"></form></td>
                    </tr>
                </c:if>
            </c:forEach>

        </table>
    </div>

</div>


</body>
</html>
