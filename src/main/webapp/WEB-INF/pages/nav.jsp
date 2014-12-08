
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
                <a href="/main">Home</a>
            </th>
            <th>
                <a href="/lobby">Start Match</a>
            </th>

            <th>
                <a href="/notifications">Notifications</a>
            </th>

            <th>
                <a href="/stats/${user}">Stats</a>
            </th>
            <th>
                <a href="/leaderboard" >Leaderboards</a>
            </th>
            <th>
                <a href="#">About</a>
            </th>
            <th>
                <a href="#">Settings</a>
            </th>

            <th>
                <a href="/main/logout">Log out</a>
            </th>



        </tr>
    </table>
</div>
