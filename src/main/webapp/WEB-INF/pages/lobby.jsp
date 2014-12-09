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
    <jsp:include page="include.jsp" />

    <title>Lobby</title>
</head>
<body>
<jsp:include page="nav.jsp">
    <jsp:param name="user" value="${user}"></jsp:param>
</jsp:include>

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
        <tr><td> ${invitedPlayer} </td> <form action="/lobby/removePlayer"><input type ="hidden" name="removePlayer" value="${invitedPlayer}"/> <td> <input type="submit" value="Remove"></td></form> </tr>
    </c:forEach>
</table>

<br>

<table>
    <tr>
         <td>
             <a href="/lobby/startGame"><button>Start Game</button></a>
         </td>
         <td>
             ${lobby.startError}
         </td>
    </tr>
</table>

</body>
</html>
