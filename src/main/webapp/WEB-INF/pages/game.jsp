<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: eirikskogland
  Date: 03.12.14
  Time: 18:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="include.jsp" />
    <script src="/resources/js/game.js" type="text/javascript" ></script>

    <link href="/resources/css/game.css" rel="stylesheet">
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <script src="/resources/js/bootstrap.js" type="text/javascript"></script>
    <script src="/resources/js/jQuery2-1-1.js" type="text/javascript"></script>

    <title>Game</title>
</head>

<!--
<script type="text/javascript" src="../../resources/js/jQuery2-1-1.js">  </script>
<script type="text/javascript" src="../../resources/js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" href="../../resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="../../resources/css/mtgcounter.css">
    -->

<body>
<jsp:include page="nav.jsp">
    <jsp:param name="user" value="${user}"></jsp:param>
    <jsp:param name="numberOfNotifications" value="${numberOfNotifications}"></jsp:param>

</jsp:include>





<div class="container text-left" role="main">

<c:choose>
    <c:when test="${game.gameMode == 'ffa' || game.gameMode == '1v1'}">

        <!-- Game menu -->
        <jsp:include page="gamenav.jsp">
            <jsp:param name="game" value="${game}"/>
        </jsp:include>

        <c:forEach items="${game.players}" var="player" varStatus="loop">
            <c:if test="${(loop.index-1)%2 == 0}"> <div class="row"> </c:if>
            <div class="col-xs-4 playerInfo">
                <div class="row">
                    <div class="col-xs-12">
                        <p class="playerName"> ${player.username} </p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12" >
                        <p class="playerHp" id="hp${loop.index}">${player.hp}</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 text-center">
                        <button class="btn-danger btn-sm"  id="subtractHp${loop.index}">-</button>
                        <input type="text" id="changeHP${loop.index}" size="2" placeholder="1" value="1"/>
                        <button class="btn-success btn-sm" id="addHp${loop.index}">+</button>
                    </div>
                </div>
                <div class="row">
                    <div id="poisonCounterRow${loop.index}" class="col-xs-12 text-center">
                        <div class="col-xs-1">
                            <button class="btn-success btn-xs poisonbutton" id="subtractPoison${loop.index}">-</button>
                        </div>
                        <div class="playerPoison col-xs-1" id="poison${loop.index}">
                                ${player.poison}
                        </div>
                        <div class="col-xs-1">
                            <button class="btn-success btn-xs" id="addPoison${loop.index}">+</button>
                        </div>
                    </div>
                </div>
            </div>
            <c:if test="${(loop.index-1)%2 == 0}"> </div> </c:if>

        </c:forEach>

    </c:when>

    <c:when test="${game.gameMode == 'thg'}">
                <div class="row">
            <div class="col-xs-4">
                <form action="/game/declareWinner" method="post">
                    <input type="hidden" name="gameId" value="${game.id}" />
                    <select name="winner">
                      <option value="team1">
                          <c:forEach items="${game.team1}" var="player" varStatus="loop">
                          ${player.username}<c:if test="${!loop.last}">,</c:if>
                          </c:forEach>
                      </option>
                      <option value="team2">
                          <c:forEach items="${game.team2}" var="player" varStatus="loop">
                              ${player.username}<c:if test="${!loop.last}">,</c:if>
                          </c:forEach>
                      </option>
                    </select>
                    <input type="submit" value="Declare Winner" class="btn-sm btn-primary">
                </form>
            </div>
            <div class="col-xs-4">

                <button class="btn btn-sm btn-primary" id="btn_reset">Reset Counters</button>

            </div>
                    <div class="col-xs-4">
                        <input type="text" id="startingHp" size="4" placeholder="20" style="margin: 0 30% 0 30%;"/>
                    </div>
            <div class="col-xs-4">
                <button class="btn btn-sm btn-primary" id="btn_toggle_poison">Poison Counters</button>
            </div>
                    <div class="col-xs-4">
                        <a href="/game/quitGame?gameId=${game.id}"><button class="btn btn-sm btn-primary">Back to lobby</button></a>
                    </div>

        </div>


        <!-- Team 1 -->
        <div class="col-xs-4 playerInfo">
            <div class="row">
                <div class="col-xs-12">
                    <p class="playerName">
                        <c:forEach items="${game.team1}" var="player" varStatus="loop">
                            ${player.username}  <c:if test="${!loop.last}">,</c:if>
                        </c:forEach>
                    </p>
                </div>
            </div>

                         <div class="row">
                <div class="col-xs-12" >
                    <p class="playerHp" id="hp1">20</p>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12 text-center">
                    <button class="btn-danger btn-sm"  id="subtractHp1">-</button>
                    <input type="text" id="changeHP1" size="2" placeholder="1" value="1"/>
                    <button class="btn-success btn-sm" id="addHp1">+</button>
                </div>
            </div>
            <div class="row">
                <div id="poisonCounterRow1" class="col-xs-12 text-center">
                    <div class="col-xs-1">
                        <button class="btn-success btn-xs"  id="subtractPoison1">-</button>
                    </div>
                    <div class="playerPoison col-xs-1" id="poison1">
                            0
                    </div>
                    <div class="col-xs-1">
                        <button class="btn-success btn-xs" id="addPoison1">+</button>
                    </div>
                </div>
            </div>
        </div>


        <!-- Team 2 -->
        <div class="col-xs-4 playerInfo">
            <div class="row">
                <div class="col-xs-12">
                    <p class="playerName">
                        <c:forEach items="${game.team2}" var="player" varStatus="loop">
                            ${player.username}  <c:if test="${!loop.last}">,</c:if>
                        </c:forEach>
                    </p>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-12" >
                    <p class="playerHp" id="hp2">20</p>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12 text-center">
                    <button class="btn-danger btn-sm"  id="subtractHp2">-</button>
                    <input type="text" id="changeHP2" size="2" placeholder="1" value="1"/>
                    <button class="btn-success btn-sm" id="addHp2">+</button>
                </div>
            </div>
            <div class="row">
                <div id="poisonCounterRow2" class="col-xs-12 text-center">
                    <div class="col-xs-1 poison">
                        <button class="btn-success btn-xs"  id="subtractPoison2">-</button>
                    </div>
                    <div class="playerPoison col-xs-1" id="poison2">
                        0
                    </div>
                    <div class="col-xs-1 poison">
                        <button class="btn-success btn-xs" id="addPoison2">+</button>
                    </div>
                </div>
            </div>
        </div>



    </c:when>

    <c:otherwise>
        <h1>Something went wrong with the gametypes</h1>
    </c:otherwise>

</c:choose>

</div>




<script type="text/javascript">


    var ID = 0;
    var players = new Array();
    var rowNum = 0;


    function Player() {
        this.hp = 20;
        this.id = ID;
        this.poison = 0;

        ID++;
    }

    Player.prototype.addHp = function(hp) {
        this.hp+=hp;
    }

    Player.prototype.removeHp = function(hp) {
        this.hp-=hp;
    }

    Player.prototype.addPoison = function() {
        this.poison+=1;
    }

    Player.prototype.removePoison = function() {
        this.poison-=1;
    }


    var appendPlayer = function(newPlayer) {


        // Oppdatering av hp
        var updateHp = function() {
            $("#hp" + newPlayer.id).html(players[newPlayer.id].hp);
        }

        var updatePosion = function() {
            $("#poison" + newPlayer.id).html(players[newPlayer.id].poison);
        }


        // Legg til funksjonalitet til knapper
        $("#subtractHp" + newPlayer.id).click(function() {
            players[newPlayer.id].removeHp($("#changeHP"+newPlayer.id).val());
            updateHp();
        });

        $("#addHp" + newPlayer.id).click(function() {
            var addValue = parseInt($("#changeHP"+newPlayer.id).val());
            players[newPlayer.id].addHp(addValue);
            updateHp();
        });

        // Legg til funksjonalitet til knapper
        $("#subtractPoison" + newPlayer.id).click(function() {
            players[newPlayer.id].removePoison();
            updatePosion();
        });

        $("#addPoison" + newPlayer.id).click(function() {
            players[newPlayer.id].addPoison();
            updatePosion();
        });

        $("#poisonCounterRow" + newPlayer.id).toggle();


    };

    $(function() {

            for(var i = 0; i < ${game.numberOfPlayers}; i++) {
                var newPlayer = new Player();
                players.push(newPlayer);
                appendPlayer(newPlayer);

            }

        $("#btn_reset").click(function () {

            var startingHp = $("#startingHp").val();

            var startingHp =  parseInt(startingHp);

            if(isNaN(startingHp)) {
                startingHp = 20;
            }

            for(var i = 0; i < players.length; i++) {
                players[i].hp = startingHp;
                $("#hp" + i).html(players[i].hp);

                players[i].poison = 0;
                $("#poison" + i).html(players[i].poison);


            }

        });

        $("#btn_toggle_poison").click(function () {
            for(var i = 0; i < players.length; i++) {
                $("#poisonCounterRow" + i).toggle();
            }
        });


        $("#btn_rolldice").click(function () {
            var dice = $("#diceSides").val();
            dice =  parseInt(dice);
            var diceResult = 0;
            if(isNaN(dice)) {
                dice = 6;
            }
            diceResult = Math.floor((Math.random() * dice) + 1);

            window.alert(parseInt(diceResult));
        });


    });



</script>

</body>
</html>
