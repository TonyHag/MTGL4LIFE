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
<div class="container" role="main">
    <h1>Welcome ${user}</h1>

    <div class="jumbotron">
        <h1>MTG Supreme</h1> <img src="http://i59.tinypic.com/32zi2pw.png" alt="Ingame photo">
        <p class="lead">Vise stats her? </p>
        <p><a class="btn btn-lg btn-success" href="#" role="button">Start a game</a></p>
    </div>

    <div class="row marketing">
        <div class="col-md-6">
            <h4>Start a game</h4>
            <p>Invite your friends and start a game.
                Use the provided life counter and declare a winner to track your wins and losses.
                When the game is over, all participants will be sent a game confirmation notification to
                confirm the game. When everyone has accepted, stats will be updated.
            </p>

            <h4>Track your own and your friends stats</h4>
            <p>Stats like wins and losses will be tracked.
                You can see your personal stats and your friends stats in the stats page.</p>

            <h4>Notifications</h4>
            <p>Check your notifications for game confirmations and leaderboard invites.</p>

        </div>

        <div class="col-md-6">

            <h4>Leaderboards</h4>
            <p>Create your own leaderboard and invite your friends.
                If all the players in a game is members of the same leaderboard,
                both their total stats and leaderboard stats will be updated.
            </p>

            <h4>Manage your leaderboards</h4>
            <p>When you have created a leaderboard, you can invite new players or remove existing players from your leaderboard.</p>

            <h4>Logout</h4>
            <p>Log out to log out.</p>
        </div>
    </div>


</div>



</body>
</html>
