<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: eirikskogland
  Date: 22.12.14
  Time: 00:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>


<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <p class="navbar-brand">Menu</p>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Declare Winner<span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <c:forEach items="${game.players}" var="player">
                            <li>
                                <form action="/game/declareWinner" method="post" class="navbar-form">
                                    <input type="hidden" name="gameId" value="${game.id}" />
                                    <input type="hidden" name="winner" value="${player.username}" />
                                    <input type="submit" class="btn-default" value="${player.username}">
                                </form>
                            </li>
                        </c:forEach>
                    </ul>
                </li>

                <li>
                    <a id="btn_reset">Reset Counters</a>
                    <input  type="text" id="startingHp" size="2" placeholder="20"/>
                </li>

                <li>
                    <a id="btn_toggle_poison">Poison Counters</a>
                </li>

            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="/game/quitGame">Back to lobby</a>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
