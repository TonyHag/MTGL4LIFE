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
    <jsp:param name="numberOfNotifications" value="${numberOfNotifications}"></jsp:param>

</jsp:include>

<div class="container text-left">
     <div>
         <h1>Lobby</h1>
     </div>

    <br>
    <div> <h4>Game mode:</h4>
        <c:choose>
            <c:when test="${lobby.gameMode == 'ffa'}">
                <h2>Free For All </h2>
            </c:when>
            <c:when test="${lobby.gameMode == 'thg'}">
                <h2>Two Headed Giant </h2>
            </c:when>
            <c:when test="${lobby.gameMode == '1v1'}">
                <h2>1v1</h2>
            </c:when>

        </c:choose></div>

    <form action="/lobby/changeGameMode" method="post">
        <input type="hidden" name="lobbyId" value="${lobby.id}" />
        <select name="gameMode">
            <option value="1v1">1v1</option>
            <option value="ffa">FFA</option>
            <option value="thg">Two Headed Giant</option>
        </select>
            <input type="submit" value="Change" class="btn-primary btn-sm">
    </form>





    <h2>Invite players</h2>

    <form action="/lobby/invite" method="post">
        <table>
            <tr><td><input type ="text" name="invitePlayer" /></td> <td> <input type="submit" value="Invite"></td> <td>${inviteError}</td></tr>
        </table>
    </form>

    <br>

    <c:choose>
        <c:when test="${lobby.gameMode == 'ffa'}">
            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <th>Players: </th>
                    </thead>
                    <tbody>
                        <c:forEach items="${lobby.players}" var="invitedPlayer">
                            <tr>
                                <td>
                                        ${invitedPlayer.username}
                                </td>

                                <td>
                                    <c:choose>
                                        <c:when test="${lobby.hostId == invitedPlayer.userId}">
                                            <!--<b>Host</b>-->
                                        </c:when>
                                        <c:otherwise>
                                            <form action="/lobby/removePlayer">
                                                <input type ="hidden" name="removePlayer" value="${invitedPlayer.username}"/>
                                                <input type="submit" value="Remove" class="btn btn-sm btn-warning">
                                            </form>
                                        </c:otherwise>
                                    </c:choose>

                                </td>



                            </tr>
                        </c:forEach>

                    </tbody>




                </table>

            </div>


            <br>

            <table>
                <tr>
                    <td>
                        <a href="/lobby/startGame"><button class="btn btn-success">Start Game</button></a>
                    </td>
                    <td>
                            ${startError}
                    </td>
                </tr>
            </table>


        </c:when>

        <c:when test="${lobby.gameMode == 'thg'}">

        <div class="table-responsive">
            <table class="table">
                <thead>
                <tr><th>Players: </th> <th>Team</th> <th>Change team</th></tr>

                </thead>

                <tbody>
                <c:forEach items="${lobby.players}" var="invitedPlayer">
                    <tr>
                        <td>
                                ${invitedPlayer.username}
                        </td>

                        <td>
                                ${invitedPlayer.team}
                        </td>

                        <td>
                            <form class="pull-left" action="/lobby/selectTeam" method="post">
                                <input type ="hidden" name="userId" value="${invitedPlayer.userId}"/>
                                <input type ="hidden" name="team" value="1"/>
                                <input type="submit" value="1" class="btn-primary btn-xs">
                            </form>

                            <form action="/lobby/selectTeam" method="post">
                                <input type ="hidden" name="userId" value="${invitedPlayer.userId}"/>
                                <input type ="hidden" name="team" value="2"/>
                                <input type="submit" value="2" class="btn-primary btn-xs">
                            </form>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${lobby.hostId == invitedPlayer.userId}">
                                    <!--<b>Host</b>-->
                                </c:when>
                                <c:otherwise>
                                    <form action="/lobby/removePlayer">
                                        <input type ="hidden" name="removePlayer" value="${invitedPlayer.username}"/>
                                        <input type="submit" value="Remove" class="btn btn-sm btn-warning">
                                    </form>
                                </c:otherwise>
                            </c:choose>

                        </td>



                    </tr>
                </c:forEach>
                </tbody>



            </table>

        </div>


            <br>

            <table>
                <tr>
                    <td>
                        <a href="/lobby/startGame"><button class="btn btn-success">Start Game</button></a>
                    </td>
                    <td>
                            ${startError}
                    </td>
                </tr>
            </table>

        </c:when>

        <c:when test="${lobby.gameMode == '1v1'}">
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <th>Players: </th>
                    </thead>
                    <tbody>
                    <c:forEach items="${lobby.players}" var="invitedPlayer">
                        <tr>
                            <td>
                                    ${invitedPlayer.username}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${lobby.hostId == invitedPlayer.userId}">
                                        <!--<b>Host</b>-->
                                    </c:when>
                                    <c:otherwise>
                                        <form action="/lobby/removePlayer">
                                            <input type ="hidden" name="removePlayer" value="${invitedPlayer.username}"/>
                                            <input type="submit" value="Remove" class="btn btn-sm btn-warning">
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <br>

            <table>
                <tr>
                    <td>
                        <a href="/lobby/startGame"><button class="btn btn-success">Start Game</button></a>
                    </td>
                    <td>
                            ${startError}
                    </td>
                </tr>
            </table>
        </c:when>

        <c:otherwise>
            <h3>Something went wrong with the gamemodes...</h3>
        </c:otherwise>

    </c:choose>

</div>


</body>
</html>
