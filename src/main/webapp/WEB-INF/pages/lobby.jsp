<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: eirikskogland
  Date: 03.12.14
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lobby</title>
</head>
<body>

<h1>Invite players</h1>

<form action="/lobby/invite" method="post">
    <table>
        <tr><td><input type ="text" name="invitePlayer" /></td> <td> <input type="submit" value="Invite"></td> <td>${lobby.inviteError}</td></tr>
    </table>
</form>

<br>

<table>
    <tr><th>Players in lobby</th></tr>
    <tr><td>${lobby.hostUsername}</td> <td>host</td></tr>
    <c:forEach items="${lobby.invitedPlayerUsernames}" var="invitedPlayer">
        <tr><td> ${invitedPlayer} </td></tr>
    </c:forEach>
</table>

<br>

<a href="/lobby/startGame"><button>Start Game</button></a>

</body>
</html>
