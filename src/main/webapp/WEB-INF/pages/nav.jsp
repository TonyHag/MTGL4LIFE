<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: eirikskogland
  Date: 07.12.14
  Time: 00:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--<div>
    <table>
        <tr>
            <th>
                <a href="/main">
                    <button type="button" class="btn btn-primary btn-lg">HOME
                        <!--<i class="glyphicon glyphicon-home"></i>--
                    </button>
                </a>
            </th>
            <th>
                <a href="/lobby">
                    <button type="button" class="btn btn-default btn-lg">START MATCH
                        <!--<span class="glyphicon glyphicon-play" aria-hidden="true"></span>--
                    </button>
                </a>
            </th>

            <th>
                <a href="/notifications">
                    <button type="button" class="btn btn-default btn-lg">NOTIFICATIONS
                       <!-- <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>--
                    </button>
                </a>
            </th>

            <th>
                <a href="/stats/${user}">
                    <button type="button" class="btn btn-default btn-lg">STATS
                       <!-- <span class="glyphicon glyphicon-stats" aria-hidden="true"></span>--
                    </button>
                </a>
            </th>
            <th>
                <a href="/leaderboard" >
                    <button type="button" class="btn btn-default btn-lg">LEADERBOARD
                        <!--<span class="glyphicon glyphicon-tower" aria-hidden="true"></span>--
                    </button>
                </a>
            </th>
            <th>
                <a href="#">
                    <button type="button" class="btn btn-default btn-lg"><h3>ABOUT</h3>
                       <!-- <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>--
                    </button>
                </a>
            </th>
            <th>
                <a href="#">
                    <button type="button" class="btn btn-default btn-lg"><p>SETTINGS</p>
                        <!--<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>--
                    </button>
                </a>
            </th>

            <th>
                <a href="/main/logout">
                    <button type="button" class="btn btn-default btn-lg"><h4>LOG OUT</h4>
                        <!--<span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>--
                    </button>
                </a>
            </th>



        </tr>
    </table>
</div>-->
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
            <a class="navbar-brand" href="/main">MTGSupreme</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="/lobby">Start match</a></li>
                <li><a href="/stats/${user}">Stats</a></li>
                <li><a href="/leaderboard">Leaderboards</a></li>
                <li><a href="/notifications">Notifications<c:if test="${numberOfNotifications > 0}"> <span class="badge">${numberOfNotifications}</span> </c:if></a></li>

            </ul>

            <ul class="nav navbar-nav navbar-right">

                <li><a href="/main/logout">Log Out</a></li>

            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
