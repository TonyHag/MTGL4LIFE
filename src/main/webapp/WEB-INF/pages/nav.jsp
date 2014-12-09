
<%--
  Created by IntelliJ IDEA.
  User: eirikskogland
  Date: 07.12.14
  Time: 00:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <table>
        <tr>
            <th>
                <a href="/main">
                    <button type="button" class="btn btn-default btn-lg">
                        <span class="glyphicon glyphicon-home" aria-hidden="true"></span> HOME
                    </button>
                </a>
            </th>
            <th>
                <a href="/lobby">
                    <button type="button" class="btn btn-default btn-lg">
                        <span class="glyphicon glyphicon-play" aria-hidden="true"></span> START MATCH
                    </button>
                </a>
            </th>

            <th>
                <a href="/notifications">
                    <button type="button" class="btn btn-default btn-lg">
                        <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span> NOTIFICATIONS
                    </button>
                </a>
            </th>

            <th>
                <a href="/stats/${user}">
                    <button type="button" class="btn btn-default btn-lg">
                        <span class="glyphicon glyphicon-stats" aria-hidden="true"></span> STATS
                    </button>
                </a>
            </th>
            <th>
                <a href="/leaderboard" >
                    <button type="button" class="btn btn-default btn-lg">
                        <span class="glyphicon glyphicon-tower" aria-hidden="true"></span> LEADERBOARDS
                    </button>
                </a>
            </th>
            <th>
                <a href="#">
                    <button type="button" class="btn btn-default btn-lg">
                        <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> ABOUT
                    </button>
                </a>
            </th>
            <th>
                <a href="#">
                    <button type="button" class="btn btn-default btn-lg">
                        <span class="glyphicon glyphicon-cog" aria-hidden="true"></span> SETTINGS
                    </button>
                </a>
            </th>

            <th>
                <a href="/main/logout">
                    <button type="button" class="btn btn-default btn-lg">
                        <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> LOGOUT
                    </button>
                </a>
            </th>



        </tr>
    </table>
</div>
