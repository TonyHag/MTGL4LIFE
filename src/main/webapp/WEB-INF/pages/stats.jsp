<%--
  Created by IntelliJ IDEA.
  User: eirikskogland
  Date: 05.12.14
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Stats</title>
</head>
<body>

<h1>Statistics for ${stats.username}</h1>

<table>
    <tr> <th>Wins</th> <th>Losses</th>    <th>Win%</th>        <th>Total</th> </tr>
    <tr> <td>${stats.wins}</td> <td>${stats.losses}</td> <td>${stats.winPercentage}</td>   <td>${stats.total}</td> </tr>

</table>


</body>
</html>
