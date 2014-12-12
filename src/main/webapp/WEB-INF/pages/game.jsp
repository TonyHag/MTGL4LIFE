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
</jsp:include>


    <div class="container" role="main">

        <div class="row">
            <div class="col-xs-4">
                <form action="/game/declareWinner" method="post">
                    <input type="hidden" name="gameId" value="${game.id}" />
                    <select name="winner">
                        <c:forEach items="${game.players}" var="player"> <option value="${player.username}">${player.username}</option></c:forEach>
                    </select>
                    <input type="submit" value="Declare Winner" class="btn-sm btn-primary">
                </form>
            </div>


            <div class="col-xs-4">
                <input type="text" id="startingHp" size="4" placeholder="20"/>
                <button class="btn btn-sm btn-primary" id="btn_reset">Reset Counters</button>
            </div>

            <div class="col-xs-4">
                <button class="btn btn-sm btn-primary" id="btn_toggle_poison">Poison Counters</button>
            </div>
        </div>



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

                            <input type="text" id="changeHP${loop.index}" size="4" placeholder="1" value="1"/>

                            <button class="btn-success btn-sm" id="addHp${loop.index}">+</button>
                        </div>
                    </div>

                    <div class="row" id="poisonCounterRow${loop.index}">
                        <div class="col-xs-12 text-center">

                            <table>
                                <tr>
                                    <td>
                                        <button class="btn-success btn-xs"  id="subtractPoison${loop.index}">-</button>

                                    </td>
                                    <td style="padding: 5px">
                                        <p class="playerPoison" id="poison${loop.index}">${player.poison}</p>

                                    </td>
                                    <td>
                                        <button class="btn-success btn-xs" id="addPoison${loop.index}">+</button>

                                    </td>
                                </tr>
                            </table>



                        </div>
                    </div>


                </div>
                <c:if test="${(loop.index-1)%2 == 0}"> </div> </c:if>

                </c:forEach>

    </div>

<table>



</table>


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



    });


</script>

</body>
</html>
